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

        Client client = clientCrudService.findById(5L);
        Planet fromPlanet = planetCrudService.getPlanet("MARS");
        Planet toPlanet = planetCrudService.getPlanet("JUPITER");

        Ticket ticket = new Ticket();
        Ticket ticket2 = ticketCrudService.findById(10L);
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);

        // save
        System.out.println("Saving new ticket...");
        ticketCrudService.save(ticket);
        System.out.println("Ticket saved with ID: " + ticket.getId());

        // get by id
        System.out.println("Fetching ticket with ID: " + ticket.getId());
        Ticket fetchedTicket = ticketCrudService.findById(ticket.getId());

        //ticket to delete
        Ticket fetchedTicket2 = ticketCrudService.findById(ticket2.getId());
        if (fetchedTicket != null) {
            System.out.println("Fetched Ticket: " + fetchedTicket.getId() + ", Client: " + fetchedTicket.getClient().getName());
        } else {
            System.out.println("Ticket not found.");
        }

        // update ticket (changing name of client)
        Client newClient = new Client();
        newClient.setName("Jane Doe");
        clientCrudService.save(newClient);

        System.out.println("Updating ticket...");
        fetchedTicket.setClient(newClient);
        ticketCrudService.update(fetchedTicket);
        System.out.println("Ticket updated with new Client: " + fetchedTicket.getClient().getName());

        // delete ticket
        System.out.println("Deleting ticket...");
        ticketCrudService.delete(fetchedTicket2);
        System.out.println("Ticket deleted.");

        // check for successful delete
        System.out.println("Fetching ticket after deletion...");
        Ticket deletedTicket = ticketCrudService.findById(fetchedTicket2.getId());
        if (deletedTicket == null) {
            System.out.println("Ticket successfully deleted.");
        } else {
            System.out.println("Ticket still exists.");
        }


    }
}

