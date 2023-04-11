-- Ver tema de coordenadas. 
create table zonas(
nome character varying(30),
extension real,
constraint zonas pk primary key(nome),
coordenadas point,  
-- Mirar esto do tipo de dato ben. 
);

create table atraccions(
nome character varying(30),
aforo integer not null,
alturaMin integer not null,
custoMantemento real,
ubicacion character varying(30) not null,
descricion character varying(500),
acuatica character(2),
soadultos character(2),
familiar character(2),
apertura integer,
peche integer,
idademin integer,
idadeRecomendada integer,
constraint atraccions pk primary key (nome),
zona character varying(30),
constraint atraccions fk 1 foreign key (zona) references public.zonas(nome)
on update cascade on delete set null
);

create table hostalaria(
nomeEstablecemento character varying(30),
ubicacion character varying(30) not null,
aforo integer not null,
horaInicio time,
horaFin time,
constraint hostalaria pk primary key(nomeEstablecemento),
zona character varying(30),
constraint hostalaria fk 1 foreign key (zona) references public.zonas(nome)
on update cascade on delete set null
);

create table espectaculos(
nome character varying(30),
horaInicio time,
horaFin time,
tematica character varying(15),
descricion character varying(200),
ubicacion character varying(30) not null,
constraint espectaculos pk primary key(nome),
zona character varying(30),
constraint espectaculos fk 1 foreign key (zona) references public.zonas(nome)
on update cascade on delete set null
);

create table traballadoresParque(
dni character(9),
nome character varying(60) not null,
rua character varying(40),
numero integer,
cp integer,
localidade character varying(30),
salario real not null,
telefono character(9),
dataInicio date not null,
dataNacemento date,
formacion character varying(100) not null,
nomeAtraccion character varying(30) ,
nomeEspectaculo character varying(30) ,
constraint traballadoresParque pk primary key(dni),
constraint traballadoresParque fk 1 foreign key (nomeAtraccion)
references public.atraccions(nome)
on update cascade on delete set null,
constraint traballadoresParque fk 2 foreign key (nomeEspectaculo)
references public.espectaculos(nome)
on update cascade on delete set null
);

create table hostaleiros(
dni character(9),
nome character varying(60) not null,
rua character varying(40),
numero integer,
cp integer,
localidade character varying(30),
salario real not null,
telefono character(9),
dataInicio date not null,
dataNacemento date,
formacion character varying(100) not null,
nomeEstablecemento character varying(30),
constraint hostaleiros pk primary key(dni),
constraint hostaleiros fk 1 foreign key (nomeEstablecemento)
references public.hostalaria(nomeEstablecemento)
on update cascade on delete set null
);

create table visitantes(
dni character(9),
nome character varying(60) not null,
nacionalidade character varying(30),
telefono character(9),
dataNacemento date,
altura integer not null,
constraint visitantes pk primary key(dni)
);

create table ir(
dataVisita date,
visitante character(9),
atraccion character varying(30),
constraint ir pk primary key(dataVisita,visitante,atraccion),
constraint ir fk 1 foreign key (visitante)
references public.visitantes(dni)
on update cascade on delete cascade,
constraint ir fk 2 foreign key (atraccion)
references public.atraccions(nome)
on update cascade on delete cascade
);

create table xantar(
dataVisita date,
visitante character(9),
establecemento character varying(30),
constraint xantar pk primary key(dataVisita,visitante,establecemento),
constraint xantar fk 1 foreign key (visitante)
references public.visitantes(dni)
on update cascade on delete cascade,
constraint xantar fk 2 foreign key (establecemento)
references public.hostalaria(nomeEstablecemento)
on update cascade on delete cascade
);

create table asistir(
dataVisita date,
visitante character(9),
espectaculo character varying(30),
constraint asistir pk primary key(dataVisita,visitante,espectaculo),
constraint asistir fk 1 foreign key (visitante)
references public.visitantes(dni)
on update cascade on delete cascade,
constraint asistir fk 2 foreign key (espectaculo)
references public.espectaculos(nome)
on update cascade on delete cascade
);

create table musica(
codigoCancion character (9),
nombre character varying (30) not null,
clasificación character varying (30) not null,
popularidad integer,
artista character varying(30) not null,
álbum character varying(30),
constraint musica pk primary key (codigoCancion)
);

-- Ver a relación con zonas.
create table sistemasDeAudio(
identificador character (5),
función character varying (20) not null,
descripción character varying(150),
ubicación character varying (30) not null,
constraint sistemasDeAudio pk primary key (identificador),
zona character varying(30),
constraint atraccions fk 1 foreign key (zona) references public.zonas(nome)
on update cascade on delete set null
);

create table reproducir(
fechaReproduccion date,
codigoCancion character(9),
sistemaAudioIdentificador character (5),
constraint reproducir pk primary key(fechaReproduccion,codigoCancion,
sistemaAudioIdentificador),
constraint reproducir fk 1 foreign key (codigoCancion)
references public.musica(codigoCancion)
on update cascade on delete cascade,
constraint reproducir fk 2 foreign key (sistemaAudioIdentificador)
references public.sistemasDeAudio(identificador)
on update cascade on delete cascade
);

create table DJ(
dni character(9),
nombre character varying(60) not null,
calle character varying(40),
numero integer,
cp integer,
localidad character varying(30),
salario real not null,
telefono character(9),
fechaInicio date not null,
fechaNacemento date,
formacion character varying(100) not null,
identificadorSistema character varying(30) ,
constraint DJ pk primary key(dni),
constraint DJ fk 1 foreign key (identificadorSistema)
references public.sistemasDeAudio(identificador)
on update cascade on delete set null
);

create table medios(
nomeMedio character varying(30),
tipo character varying(30) not null,
prezo real not null,
capacidade integer not null,
velocidade real not null,
constraint medios pk primary key (nomeMedio)
);

create table visitantes(
dni character(9) not null,
nome character varying(60) not null,
nacionalidade character varying(30),
telefono character(9),
dataNacemento date,
altura integer not null,
medioTransporte character varying(30),
constraint visitantes pk primary key(dni)
constraint medios fk 1 foreign key (medioTransporte)
references public.medios(nomeMedio)
on update cascade on delete set null
);
