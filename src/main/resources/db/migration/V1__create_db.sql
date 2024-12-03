CREATE TABLE IF NOT EXISTS client (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(200) NOT NULL CHECK (LENGTH(name) BETWEEN 3 AND 200)
    );

CREATE TABLE IF NOT EXISTS planet (
                                      id VARCHAR(10) PRIMARY KEY CHECK (id = UPPER(id)),
    name VARCHAR(500) NOT NULL CHECK (LENGTH(name) BETWEEN 1 AND 500)
    );

CREATE TABLE IF NOT EXISTS ticket (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      created_at TIMESTAMP NOT NULL,
                                      client_id INT NOT NULL,
                                      from_planet_id VARCHAR(10) NOT NULL,
    to_planet_id VARCHAR(10) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (from_planet_id) REFERENCES planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES planet(id)
    );
