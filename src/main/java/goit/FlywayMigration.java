package goit;

import org.flywaydb.core.Flyway;

public class FlywayMigration {
    public static void main(String[] args) {
        // create a flyway instance
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:~/hibernate", "sa", "123")
                .load();



        flyway.repair();
        // performing migration
        flyway.migrate();

        System.out.println("migration successfully completed");
    }
}