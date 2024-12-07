package com.klef.jfsd.exam.hibernate_lab;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Insert a record
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Client client = new Client();
            client.setName("John Doe");
            client.setGender("Male");
            client.setAge(28);
            client.setLocation("New York");
            client.setEmail("johndoe@example.com");
            client.setMobileNumber("1234567890");
            session.save(client);
            tx.commit();
        }

        // Fetch and print all records
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Client";
            Query query = session.createQuery(hql, Client.class);
            List<Client> clients = query.list();
            for (Client client : clients) {
                System.out.println(client.getId() + " " + client.getName() + " " + client.getGender() + " " +
                                   client.getAge() + " " + client.getLocation() + " " + client.getEmail() + " " +
                                   client.getMobileNumber());
            }
        }

        sessionFactory.close();
    }
}
