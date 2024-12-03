package goit;

import goit.entity.Client;
import goit.entity.Planet;
import goit.service.ClientCrudService;
import goit.service.HibernateUtil;
import goit.service.PlanetCrudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
        PlanetCrudService planetCrudService = new PlanetCrudService(sessionFactory);

        // add a new planet
        Planet newPlanet = new Planet();
        newPlanet.setId("PLUTON");           //id
        newPlanet.setName("Pluton");        //name
        System.out.println("Saving new planet...");
        planetCrudService.addPlanet(newPlanet);
        System.out.println("Planet saved: " + newPlanet.getName());

        // get planet from db
        System.out.println("Fetching planet with ID 'PLUTIN'...");
        Planet fetchedPlanet = planetCrudService.getPlanet("PLUTON");
        if (fetchedPlanet != null) {
            System.out.println("Fetched Planet: " + fetchedPlanet.getName());
        } else {
            System.out.println("Planet with ID 'PLUTON' not found.");
        }

        // update (change name)
        System.out.println("Updating planet 'PLUTON'...");
        if (fetchedPlanet != null) {
            fetchedPlanet.setName("cold planet");
            planetCrudService.updatePlanet(fetchedPlanet);
            System.out.println("Updated Planet: " + fetchedPlanet.getName());
        } else {
            System.out.println("Planet to update not found.");
        }

        // get updated planet
        System.out.println("Fetching updated planet with ID 'PLUTON'...");
        Planet updatedPlanet = planetCrudService.getPlanet("Pluton");
        if (updatedPlanet != null) {
            System.out.println("Updated Planet: " + updatedPlanet.getName());
        } else {
            System.out.println("Updated planet not found.");
        }
        System.out.println("Deleting planet with ID 'PLUTON'...");

        Planet planetToDelete = planetCrudService.getPlanet("PLUTON");
        if(planetToDelete != null) {
            planetCrudService.deletePlanet(planetToDelete.getId());


            System.out.println("Planet deleted.");
        }
        else {
            System.out.println("Planet to delete not found.");
        }




        // to sure that planet was deleted
        System.out.println("Fetching planet with ID 'PLUTON' after deletion...");
        Planet deletedPlanet = planetCrudService.getPlanet("PLUTON");
        if (deletedPlanet == null) {
            System.out.println("Planet with ID 'PLUTON' successfully deleted.");
        } else {
            System.out.println("Planet still exists: " + deletedPlanet.getName());
        }
    }
}

