INSERT INTO studio (name, active) VALUES ('Rockstar Games', TRUE);
INSERT INTO studio (name, active) VALUES ('Paradox Interactive', TRUE);
INSERT INTO studio (name, active) VALUES ('Ubisoft', TRUE);
INSERT INTO studio (name, active) VALUES ('Estudio cerrado', FALSE);
INSERT INTO studio (name, active) VALUES ('Estudio muerto', FALSE);

INSERT INTO category (name, active)
VALUES
    ('Acción', TRUE),
    ('Estrategia', TRUE),
    ('Aventura', TRUE),
    ('RPG', FALSE);

INSERT INTO videogame (title,
                       description,
                       release_date,
                       image_url,
                       price,
                       on_sale,
                       amount,
                       active,
                       studio_id,
                       category_id)
VALUES
    ('Grand Theft Auto V', 'Mundo libre, sandbox, autos', DATE '2013-02-23',
     'asdjklasjdk', 30.0, FALSE,40, TRUE, 1, 1),
    ('Hearts of Iron IV', 'Estrategia, controla países, segunda guerra mundial', DATE '2016-05-30',
     'iuaskdajsdk', 10.0, TRUE,5, TRUE, 2, 2),
    ('Far Cry 3', 'Acción-aventura, selva', DATE '2012-12-25',
     'zkajsdlkasd', 60.0, FALSE,10, TRUE, 3, 3);