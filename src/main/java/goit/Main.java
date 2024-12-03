package goit;

import goit.entity.Client;
import goit.entity.Planet;
import goit.entity.Ticket;
import goit.service.ClientCrudService;
import goit.service.HibernateUtil;
import goit.service.PlanetCrudService;
import goit.service.TicketCrudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
        PlanetCrudService planetCrudService = new PlanetCrudService(sessionFactory);
        ClientCrudService clientCrudService = new ClientCrudService();
        TicketCrudService ticketCrudService = new TicketCrudService();

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




        //new ticket

        Planet fromPlanet = planetCrudService.getPlanet("EARTH");
        Planet toPlanet = planetCrudService.getPlanet("MARS");

        if (fromPlanet == null || toPlanet == null) {
            System.out.println("One or both planets not found.");
            return;
        }

        // get existing client
        Client client = clientCrudService.findById(5L);
        if (client == null) {
            System.out.println("client  not found.");
            return;
        }

        // create a new ticket
        Ticket ticket = new Ticket();

        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);

        // save the ticket
        System.out.println("saving new ticket...");
        ticketCrudService.save(ticket);
        System.out.println("ticket saved with ID: " + ticket.getId());
    }
}

