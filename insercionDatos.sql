--Zonas
insert into zonas values ('Cubierta A', 150.00, 25.00, 150.00);
insert into zonas values ('Cubierta B', 225.00, 130.00, -15.00);
insert into zonas values ('Descampado A', 1025.00, 480.00, -400.00);
insert into zonas values ('Descampado B', 750.00, 300.00, 100.00);


--Hostalaria
-- Inserciones en la tabla hostalaria

-- Inserción 1
INSERT INTO hostalaria VALUES ('La Parrilla Argentina', 100, '12:00:00', '22:00:00', 'Cubierta A');

-- Inserción 2
INSERT INTO hostalaria VALUES ('El Rincón de la Paella', 80, '11:30:00', '21:30:00', 'Cubierta B');

-- Inserción 3
INSERT INTO hostalaria VALUES ('Sushi Fusión', 60, '18:00:00', '23:00:00', 'Descampado A');

-- Inserción 4
INSERT INTO hostalaria VALUES ('La Trattoria Italiana', 50, '13:00:00', '22:00:00', 'Cubierta A');

-- Inserción 5
INSERT INTO hostalaria VALUES ('El Templo del Curry', 40, '17:30:00', '21:30:00', 'Cubierta B');

-- Inserción 6
INSERT INTO hostalaria VALUES ('Marisquería La Gula', 30, '12:00:00', '23:00:00', 'Descampado A');

-- Inserción 7
INSERT INTO hostalaria VALUES ('La Taberna Mexicana', 70, '18:30:00', '23:30:00', 'Cubierta A');

-- Inserción 8
INSERT INTO hostalaria VALUES ('El Asador Vasco', 60, '13:30:00', '22:30:00', 'Cubierta B');

-- Inserción 9
INSERT INTO hostalaria VALUES ('Ramen House', 40, '12:00:00', '21:00:00', 'Descampado A');

-- Inserción 10
INSERT INTO hostalaria VALUES ('La Brasserie Francesa', 50, '19:00:00', '23:30:00', 'Cubierta A');

-- Inserción 11
INSERT INTO hostalaria VALUES ('La Taberna Árabe', 30, '14:00:00', '22:00:00', 'Cubierta B');

-- Inserción 12
INSERT INTO hostalaria VALUES ('El Steakhouse Americano', 40, '18:30:00', '23:30:00', 'Descampado A');

-- Inserción 13
INSERT INTO hostalaria VALUES ('La Pizzería Napolitana', 60, '12:00:00', '22:00:00', 'Cubierta A');

-- Inserción 14
INSERT INTO hostalaria VALUES ('El Bistro Francés', 50, '11:30:00', '21:30:00', 'Cubierta B');

-- Inserción 15
INSERT INTO hostalaria VALUES ('La Cervecería Alemana', 70, '17:00:00', '23:00:00', 'Descampado A');

-- Inserción 16
INSERT INTO hostalaria VALUES ('El Restaurante Tailandés', 40, '13:00:00', '22:00:00', 'Cubierta A');

--Atraccións
-- Inserciones en la tabla atraccions

-- Inserción 1
INSERT INTO atraccions VALUES ('Montaña Rusa del Terror', 50, 120, 2500.99, '¡Siente el terror en esta montaña rusa solo para adultos!', 'Cubierta A');

-- Inserción 2
INSERT INTO atraccions VALUES ('Barca Vikinga Familiar', 80, 90, 1500.50, '¡Disfruta de un paseo en barca vikinga para toda la familia!', 'Cubierta B');

-- Inserción 3
INSERT INTO atraccions VALUES ('Tren del Oeste', 40, 110, 1800.75, '¡Vive una aventura en el salvaje oeste en este tren solo para adultos!', 'Descampado A');

-- Inserción 4
INSERT INTO atraccions VALUES ('Carrusel de Mariposas', 30, 80, 1000.25, '¡Diviértete en este carrusel colorido perfecto para toda la familia!', 'Cubierta A');

-- Inserción 5
INSERT INTO atraccions VALUES ('Giroscopio del Futuro', 20, 140, 3000.99, '¡Experimenta la gravedad cero en esta atracción solo para adultos!', 'Cubierta B');

-- Inserción 6
INSERT INTO atraccions VALUES ('Tobogán Acuático Familiar', 60, 100, 2000.50, '¡Deslízate por este tobogán acuático refrescante para toda la familia!', 'Descampado A');

-- Inserción 7
INSERT INTO atraccions VALUES ('Torre de Caída Libre', 25, 140, 2500.75, '¡Siente la adrenalina en esta torre de caída libre solo para adultos!', 'Cubierta A');

-- Inserción 8
INSERT INTO atraccions VALUES ('Laberinto del Minotauro', 15, 120, 1500.25, '¡Enfréntate al Minotauro en este laberinto desafiante para toda la familia!', 'Cubierta B');

-- Inserción 9
INSERT INTO atraccions VALUES ('Cine 7D', 40, 100, 1800.99, '¡Vive una experiencia cinematográfica en 7 dimensiones para toda la familia!', 'Descampado A');

-- Inserción 10
INSERT INTO atraccions VALUES ('Rueda de la Fortuna', 35, 110, 2200.50, '¡Disfruta de las vistas panorámicas en esta rueda de la fortuna para adultos y niños!', 'Cubierta A');

-- Inserción 11
INSERT INTO atraccions VALUES ('Laberinto de Espejos', 20, 90, 1200.75, '¡Entra en un mundo de espejos y confusión en este laberinto familiar!', 'Cubierta B');

INSERT INTO espectaculos (nome, horaInicio, horaFin, tematica, descricion, zona) VALUES
('Espectáculo de magia de Alex', '16:00:00', '17:00:00', 'Magia', 'Alex es uno de los mejores magos del país', 'Cubierta A'),
('Concierto de The Eagles', '20:00:00', '23:00:00', 'Rock', 'El legendario grupo de rock tocará sus éxitos más grandes.', 'Descampado B'),
('Obra de teatro de Shakespeare', '18:00:00', '20:00:00', 'Drama', 'Una de las obras más famosas de Shakespeare se presenta en vivo.', 'Descampado A'),
('Espectáculo de Jerry Seinfeld', '19:30:00', '21:00:00', 'Comedia', 'El famoso comediante Jerry Seinfeld se presenta en vivo.', 'Cubierta A'),
('Concierto de Beethoven', '19:00:00', '21:00:00', 'Música Clásica', 'Una orquesta tocará algunas de las más famosas piezas de Beethoven.', 'Descampado B'),
('Espectáculo de ballet clásico', '17:00:00', '19:00:00', 'Danza', 'Un espectáculo impresionante de ballet clásico.', 'Descampado A'),
('Concierto de Ariana Grande', '21:00:00', '23:00:00', 'Pop', 'La sensación del pop Ariana Grande se presenta en vivo.', 'Cubierta B'),
('Espectáculo de circo', '15:00:00', '17:00:00', 'Circo', 'Un espectáculo de circo lleno de acrobacias y trucos.', 'Descampado B'),
('Concierto de Garth Brooks', '18:00:00', '20:00:00', 'Country', 'El rey de la música country, Garth Brooks, se presenta en vivo.', 'Descampado A'),
('Espectáculo de Kevin Hart', '20:30:00', '22:00:00', 'Comedia', 'El famoso comediante Kevin Hart se presenta en vivo.', 'Cubierta A'),
('Concierto de David Guetta', '22:00:00', '00:00:00', 'Electrónica', 'El famoso DJ David Guetta tocará en vivo su mejor música electrónica.', 'Descampado B'),
('Espectáculo de flamenco', '17:30:00', '19:00:00', 'Flamenco', 'Un espectáculo de flamenco lleno de pasión y ritmo.', 'Descampado A'),
('Concierto de Miles Davis', '19:00:00', '21:00:00', 'Jazz', 'Una banda tocará algunas de las más famosas piezas de Miles Davis.', 'Cubierta B');
