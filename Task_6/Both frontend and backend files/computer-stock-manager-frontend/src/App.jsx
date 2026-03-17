import { useEffect, useMemo, useState } from 'react';
import './App.css';
import { computersApi } from './api';

const emptyForm = { serialNo: '', brand: '', rating: '', price: '' };

function normalizeNumber(value) {
  if (value === '' || value === null || value === undefined) return null;
  const n = Number(value);
  return Number.isFinite(n) ? n : null;
}

function App() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const [createForm, setCreateForm] = useState(emptyForm);
  const [editingSerialNo, setEditingSerialNo] = useState(null);
  const [editForm, setEditForm] = useState(emptyForm);

  const sortedItems = useMemo(() => {
    return [...items].sort((a, b) => (a.serialNo ?? 0) - (b.serialNo ?? 0));
  }, [items]);

  async function refresh() {
    setLoading(true);
    setError('');
    try {
      const data = await computersApi.list();
      setItems(Array.isArray(data) ? data : []);
    } catch (e) {
      setError(e?.message ?? 'Failed to load computers');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    refresh();
  }, []);

  async function onCreate(e) {
    e.preventDefault();
    setError('');

    const payload = {
      serialNo: normalizeNumber(createForm.serialNo),
      brand: createForm.brand.trim(),
      rating: normalizeNumber(createForm.rating),
      price: normalizeNumber(createForm.price),
    };

    try {
      await computersApi.create(payload);
      setCreateForm(emptyForm);
      await refresh();
    } catch (e2) {
      setError(e2?.message ?? 'Failed to create computer');
    }
  }

  function startEdit(item) {
    setEditingSerialNo(item.serialNo);
    setEditForm({
      serialNo: String(item.serialNo ?? ''),
      brand: item.brand ?? '',
      rating: item.rating ?? '',
      price: item.price ?? '',
    });
  }

  function cancelEdit() {
    setEditingSerialNo(null);
    setEditForm(emptyForm);
  }

  async function onUpdate(e) {
    e.preventDefault();
    if (editingSerialNo == null) return;
    setError('');

    const payload = {
      serialNo: normalizeNumber(editForm.serialNo),
      brand: String(editForm.brand ?? '').trim(),
      rating: normalizeNumber(editForm.rating),
      price: normalizeNumber(editForm.price),
    };

    try {
      await computersApi.update(editingSerialNo, payload);
      cancelEdit();
      await refresh();
    } catch (e2) {
      setError(e2?.message ?? 'Failed to update computer');
    }
  }

  async function onDelete(serialNo) {
    const ok = window.confirm(`Delete computer with serialNo ${serialNo}?`);
    if (!ok) return;

    setError('');
    try {
      await computersApi.remove(serialNo);
      await refresh();
    } catch (e2) {
      setError(e2?.message ?? 'Failed to delete computer');
    }
  }

  return (
    <div className="page">
      <header className="header">
        <div>
          <h1 className="title">Computer Stock Manager</h1>
          <p className="subtitle">Create, update, and delete computer entries.</p>
        </div>
        <button className="btn" onClick={refresh} disabled={loading}>
          {loading ? 'Refreshing…' : 'Refresh'}
        </button>
      </header>

      {error ? <div className="alert">{error}</div> : null}

      <section className="card">
        <h2 className="cardTitle">Add Computer</h2>
        <form className="form" onSubmit={onCreate}>
          <label className="field">
            <span>Serial No</span>
            <input
              value={createForm.serialNo}
              onChange={(e) =>
                setCreateForm((p) => ({ ...p, serialNo: e.target.value }))
              }
              placeholder="101"
              inputMode="numeric"
              required
            />
          </label>
          <label className="field">
            <span>Brand</span>
            <input
              value={createForm.brand}
              onChange={(e) => setCreateForm((p) => ({ ...p, brand: e.target.value }))}
              placeholder="HP"
              required
            />
          </label>
          <label className="field">
            <span>Rating</span>
            <input
              value={createForm.rating}
              onChange={(e) =>
                setCreateForm((p) => ({ ...p, rating: e.target.value }))
              }
              placeholder="4.5"
              inputMode="decimal"
            />
          </label>
          <label className="field">
            <span>Price</span>
            <input
              value={createForm.price}
              onChange={(e) =>
                setCreateForm((p) => ({ ...p, price: e.target.value }))
              }
              placeholder="65000"
              inputMode="numeric"
            />
          </label>
          <div className="actions">
            <button className="btn primary" type="submit">
              Create
            </button>
            <button className="btn ghost" type="button" onClick={() => setCreateForm(emptyForm)}>
              Clear
            </button>
          </div>
        </form>
      </section>

      <section className="card">
        <h2 className="cardTitle">Computers</h2>

        {editingSerialNo != null ? (
          <form className="editBar" onSubmit={onUpdate}>
            <div className="editGrid">
              <label className="field">
                <span>Serial No</span>
                <input value={editForm.serialNo} disabled />
              </label>
              <label className="field">
                <span>Brand</span>
                <input
                  value={editForm.brand}
                  onChange={(e) => setEditForm((p) => ({ ...p, brand: e.target.value }))}
                  required
                />
              </label>
              <label className="field">
                <span>Rating</span>
                <input
                  value={editForm.rating}
                  onChange={(e) => setEditForm((p) => ({ ...p, rating: e.target.value }))}
                  inputMode="decimal"
                />
              </label>
              <label className="field">
                <span>Price</span>
                <input
                  value={editForm.price}
                  onChange={(e) => setEditForm((p) => ({ ...p, price: e.target.value }))}
                  inputMode="numeric"
                />
              </label>
            </div>
            <div className="actions">
              <button className="btn primary" type="submit">
                Save
              </button>
              <button className="btn ghost" type="button" onClick={cancelEdit}>
                Cancel
              </button>
            </div>
          </form>
        ) : null}

        <div className="tableWrap">
          <table className="table">
            <thead>
              <tr>
                <th>Serial No</th>
                <th>Brand</th>
                <th>Rating</th>
                <th>Price</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {sortedItems.length === 0 ? (
                <tr>
                  <td colSpan={5} className="empty">
                    {loading ? 'Loading…' : 'No computers found.'}
                  </td>
                </tr>
              ) : (
                sortedItems.map((it) => (
                  <tr key={it.serialNo}>
                    <td className="mono">{it.serialNo}</td>
                    <td>{it.brand}</td>
                    <td>{it.rating ?? '-'}</td>
                    <td>{it.price ?? '-'}</td>
                    <td className="rowActions">
                      <button
                        className="btn small"
                        onClick={() => startEdit(it)}
                        disabled={editingSerialNo != null}
                      >
                        Edit
                      </button>
                      <button
                        className="btn small danger"
                        onClick={() => onDelete(it.serialNo)}
                        disabled={editingSerialNo != null}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </section>

      <footer className="footer">
        API: <span className="mono">http://localhost:8080/api/computers</span>
      </footer>
    </div>
  );
}

export default App;
