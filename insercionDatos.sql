--Zonas
insert into zonas values ('Cubierta A', 150.00, 42.2314, -8.9352);
insert into zonas values ('Cubierta B', 225.00, 42.2321, -8.9385);
insert into zonas values ('Descampado A', 1025.00, 42.2397, -8.9389);
insert into zonas values ('Descampado B', 750.00, 42.2226, -8.931);


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

-- Inserciones atracciones solo adultos
INSERT INTO atraccionssoadultos VALUES('Montaña Rusa del Terror', 18),
('Tren del Oeste', 16),
('Giroscopio del Futuro', 18),
('Torre de Caída Libre', 16);

-- Inserciones atracciones familiares
INSERT INTO atraccionsfamiliares VALUES('Barca Vikinga Familiar', 8),
('Carrusel de Mariposas', 5),
('Tobogán Acuático Familiar', 6),
('Laberinto del Minotauro', 8),
('Cine 7D', 5),
('Rueda de la Fortuna', 6),
('Laberinto de Espejos', 5);


-- Inserciones espectaculos
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


-- Inserciones sistemas de audio
INSERT INTO sistemasDeAudio VALUES
  ('00000', 'Megafonía', 'Sistema de megafonía para la Cubierta A', 'Cubierta A'),
  ('00001', 'Música ambiental', 'Sistema de música ambiental para el Descampado B', 'Descampado B');


-- Inserciones trabajadores parque
INSERT INTO traballadoresParque VALUES 
  ('11111111A', 'Juan Pérez', 'Calle Mayor', 12, 28001, 'Madrid', 1500, '912345678', '2022-01-01', '1980-01-01', 'Bachillerato', 'Montaña Rusa del Terror', NULL),
  ('22222222B', 'María Gómez', 'Avenida de la Playa', 25, 08001, 'Barcelona', 1800, '933456789', '2022-01-01', '1985-03-12', 'Ciclo Formativo de Grado Superior', NULL, 'Espectáculo de magia de Alex'),
  ('33333333C', 'Pedro Fernández', 'Calle del Sol', 7, 41001, 'Sevilla', 1200, '954567890', '2022-01-01', '1990-05-21', 'Grado en Turismo', 'Cine 7D', NULL);
  
  
-- Inserciones DJ
INSERT INTO dj VALUES
  ('44444444D', 'Ana García', 'Plaza Mayor', 1, 28005, 'Madrid', 2000, '915432109', '2022-01-01', '1988-07-15', 'Grado en Marketing', '00000'),
  ('55555555E', 'Lucía Ruiz', 'Calle de la Paz', 3, 46001, 'Valencia', 1600, '963210987', '2022-01-01', '1995-10-31', 'Ciclo Formativo de Grado Medio', '00001');
  
-- Inserciones hostaleiros
INSERT INTO hostaleiros VALUES
  ('66666666F', 'Jorge López', 'Calle de la Estrella', 21, 50001, 'Zaragoza', 1400, '976543210', '2022-01-01', '1993-12-25', 'Grado en Comunicación Audiovisual', 'La Parrilla Argentina'),
  ('77777777G', 'Sara Martínez', 'Calle del Mar', 5, 15001, 'A Coruña', 1300, '981234567', '2022-01-01', '1991-02-18', 'Grado en Administración de Empresas', 'El Rincón de la Paella');
  
  
--Inserciones musica
INSERT INTO musica (codigoCancion, nome, clasificacion, popularidade, artista, album) VALUES
('000000000', 'Shape of You', 'Pop', 95, 'Ed Sheeran', '÷'),
('000000001', 'Bohemian Rhapsody', 'Rock', 90, 'Queen', 'A Night at the Opera'),
('000000002', 'Fly Me to the Moon', 'Jazz', 80, 'Frank Sinatra', 'It Might As Well Be Swing'),
('000000003', 'Gangstas Paradise', 'Hip Hop', 85, 'Coolio', 'Gangstas Paradise'),
('000000004', 'Purple Rain', 'R&B', 90, 'Prince', 'Purple Rain'),
('000000005', 'Stairway to Heaven', 'Rock', 95, 'Led Zeppelin', 'Led Zeppelin IV'),
('000000006', 'Take the "A" Train', 'Jazz', 70, 'Duke Ellington', 'Never No Lament'),
('000000007', 'Lose Yourself', 'Hip Hop', 90, 'Eminem', '8 Mile'),
('000000008', 'Billie Jean', 'Pop', 85, 'Michael Jackson', 'Thriller'),
('000000009', 'Whats Going On', 'R&B', 80, 'Marvin Gaye', 'Whats Going On'),
('000000010', 'Happy', 'Pop', 75, 'Pharrell Williams', 'G I R L'),
('000000011', 'November Rain', 'Rock', 80, 'Guns N Roses', 'Use Your Illusion I'),
('000000012', 'My Funny Valentine', 'Jazz', 70, 'Chet Baker', 'Chet Baker Sings'),
('000000013', 'Nuthin But a "G" Thang', 'Hip Hop', 80, 'Dr. Dre', 'The Chronic'),
('000000014', 'Lets Stay Together', 'R&B', 75, 'Al Green', 'Lets Stay Together'),
('000000015', 'Smooth', 'Pop', 85, 'Santana feat. Rob Thomas', 'Supernatural'),
('000000016', 'Sweet Child O Mine', 'Rock', 90, 'Guns N Roses', 'Appetite for Destruction'),
('000000017', 'So What', 'Jazz', 75, 'Miles Davis', 'Kind of Blue'),
('000000018', 'Juicy', 'Hip Hop', 80, 'The Notorious B.I.G.', 'Ready to Die'),
('000000019', 'I Will Always Love You', 'R&B', 90, 'Whitney Houston', 'The Bodyguard Soundtrack');

-- Inserciones medios
INSERT INTO medios VALUES
('Barco1', 'acuatico', 10, 200, 50),
('Helicoptero1', 'aereo', 100, 4, 100);

-- Inserciones visitantes
INSERT INTO visitantes VALUES
('345678901', 'Carlos Rodriguez', 'Colombia', '345678901', '1985-08-23', 180, 'Barco1'),
('456789012', 'Laura Fernández', 'Argentina', '456789012', '1999-04-15', 170, 'Helicoptero1'),
('567890123', 'Sofía Fernández', 'Argentina', '567890123', '1997-11-07', 165, 'Helicoptero1'),
('678901234', 'Luisa Herrera', 'España', '678901234', '1988-07-02', 155, 'Barco1'),
('789012345', 'Alejandro Castro', 'España', '789012345', '1996-03-20', 185, 'Barco1');

-- Inserciones valoraciones
INSERT INTO valoracions(data, descricion, puntuacion, visitante) VALUES 
('2023-04-20', 'La experiencia en las atracciones fue increíble, lo pasé genial.', 5, '345678901'),
('2023-04-22', 'Las atracciones y espectáculos me encantaron, así como el trato de todos los trabajadores. Repetiría sin dudarlo', 5, '456789012'),
('2023-04-25', 'El espectáculo nocturno fue impresionante, sin embargo las colas para las atracciones eran muy largas.', 4, '789012345');
