package com.RD_Technology.Internship;


import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ComputerRepository {

    Boolean isNull(Object o){
        if(o == null){
            return false;
        }
        else return true;
    }

    void create(Object device){
        Configuration cg = new Configuration();
        cg.addAnnotatedClass(ComputerDevice.class);
        cg.configure();

        SessionFactory sess = cg.buildSessionFactory();
        Session session = sess.openSession();

        Transaction tx = session.beginTransaction();

        session.persist(device);

        tx.commit();
        session.close();
        sess.close();
        System.out.println("After successful insertion : ");
        getter();
    }

    void getter(){
        Configuration cg = new Configuration();
        cg.addAnnotatedClass(ComputerDevice.class);
        cg.configure();

        SessionFactory sess = cg.buildSessionFactory();
        Session session = sess.openSession();

        ComputerDevice p = session.find(ComputerDevice.class, 101);

        System.out.println(isNull(p)
                ? "Id: " + p.serialNo + "\nBrand: " + p.brand + "\nRating: " + p.rating + "\nPrice: " + p.price
                : "No data found");

        session.close();
        sess.close();
    }
    void update(){
        Configuration cg = new Configuration();
        cg.addAnnotatedClass(ComputerDevice.class);
        cg.configure();

        SessionFactory sess = cg.buildSessionFactory();
        Session session = sess.openSession();
        Transaction t = session.beginTransaction();

        ComputerDevice u = new ComputerDevice(101, "HP", 4.8, 68000);

        session.merge(u);

        System.out.println("After successful update :");

        t.commit();
        session.close();
        sess.close();
        getter();
    }

    void updateRating(){
        Configuration cg = new Configuration();
        cg.addAnnotatedClass(ComputerDevice.class);
        cg.configure();

        SessionFactory sess = cg.buildSessionFactory();
        Session session = sess.openSession();
        Transaction t = session.beginTransaction();

        ComputerDevice o = session.find(ComputerDevice.class, 101);
        o.rating = 4.9;

        System.out.println("After updated using find :");

        t.commit();
        session.close();
        sess.close();
        getter();
    }
    void remove(){
        Configuration cg = new Configuration();
        cg.addAnnotatedClass(ComputerDevice.class);
        cg.configure();
        SessionFactory sf = cg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction t = session.beginTransaction();
        ComputerDevice o = session.find(ComputerDevice.class, 101);

        session.remove(o);

        t.commit();
        session.close();
        sf.close();
        getter();

    }
}
