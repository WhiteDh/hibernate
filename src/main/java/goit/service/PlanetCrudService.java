package goit.service;

import goit.entity.Planet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PlanetCrudService {
    private SessionFactory sessionFactory;

    public PlanetCrudService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // add
    public void addPlanet(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet); // persist() для збереження
            transaction.commit();
        }
    }

    // delete
    public void deletePlanet(String planetId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, planetId);
            if (planet != null) {
                session.remove(planet); // remove() для видалення
            }
            transaction.commit();
        }
    }

    // update
    public void updatePlanet(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(planet); // merge() для оновлення
            transaction.commit();
        }
    }

    // get by id
    public Planet getPlanet(String planetId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, planetId);
        }
    }
}
