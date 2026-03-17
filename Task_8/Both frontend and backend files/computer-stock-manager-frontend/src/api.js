const API_BASE =
  import.meta.env.VITE_API_BASE_URL?.replace(/\/$/, '') ?? 'http://localhost:8080';

async function request(path, options) {
  const res = await fetch(`${API_BASE}${path}`, {
    headers: { 'Content-Type': 'application/json', ...(options?.headers ?? {}) },
    ...options,
  });

  if (res.status === 204) return null;

  const text = await res.text();
  const data = text ? JSON.parse(text) : null;

  if (!res.ok) {
    const message = data?.message ?? `Request failed (${res.status})`;
    throw new Error(message);
  }

  return data;
}

export const computersApi = {
  list: () => request('/api/computers'),
  create: (device) =>
    request('/api/computers', { method: 'POST', body: JSON.stringify(device) }),
  get: (serialNo) => request(`/api/computers/${serialNo}`),
  update: (serialNo, device) =>
    request(`/api/computers/${serialNo}`, {
      method: 'PUT',
      body: JSON.stringify(device),
    }),
  remove: (serialNo) => request(`/api/computers/${serialNo}`, { method: 'DELETE' }),
};

