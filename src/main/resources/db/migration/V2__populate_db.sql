INSERT INTO client (name) VALUES
                              ('Dmitro Itsko'),
                              ('Oksana Ivanova'),
                              ('Mykola Shevchenko'),
                              ('Svitlana Tkachenko'),
                              ('Dmytro Kovalenko'),
                              ('Olena Sokolova'),
                              ('Andriy Bondarenko'),
                              ('Natalia Hrytsenko'),
                              ('Viktor Moroz'),
                              ('Anastasia Koval');

INSERT INTO planet (id, name) VALUES
                                  ('EARTH', 'Earth'),
                                  ('MARS', 'Mars'),
                                  ('VENUS', 'Venus'),
                                  ('JUPITER', 'Jupiter'),
                                  ('SATURN', 'Saturn');

INSERT INTO ticket (client_id, from_planet_id, to_planet_id, created_at) VALUES
                                                                             (1, 'EARTH', 'MARS', CURRENT_TIMESTAMP),
                                                                             (2, 'MARS', 'VENUS', CURRENT_TIMESTAMP),
                                                                             (3, 'VENUS', 'EARTH', CURRENT_TIMESTAMP),
                                                                             (4, 'JUPITER', 'SATURN', CURRENT_TIMESTAMP),
                                                                             (5, 'SATURN', 'EARTH', CURRENT_TIMESTAMP),
                                                                             (6, 'EARTH', 'JUPITER', CURRENT_TIMESTAMP),
                                                                             (7, 'MARS', 'SATURN', CURRENT_TIMESTAMP),
                                                                             (8, 'VENUS', 'JUPITER', CURRENT_TIMESTAMP),
                                                                             (9, 'JUPITER', 'MARS', CURRENT_TIMESTAMP),
                                                                             (10, 'SATURN', 'VENUS', CURRENT_TIMESTAMP);
