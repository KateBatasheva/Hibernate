import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CrudApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("crud/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void shutdown() {
        factory.close();
    }

    public static void createExample(String title, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products product = new Products(title, cost);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public static void readAndPrintExample(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products product = session.get(Products.class, 1L);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void updateExample(long id, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products products = session.get(Products.class, id);
            products.setCost(cost);
            session.getTransaction().commit();
        }
    }

    public static void deleteExample(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products products = session.get(Products.class, id);
            session.delete(products);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        try {
            init();
            // createExample();
            // readAndPrintExample();
            // updateExample();
            // deleteExample();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}
