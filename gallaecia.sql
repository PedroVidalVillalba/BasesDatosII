-- Ver tema de coordenadas. 
create table zonas(
  nome character varying(30),
  extension real,
  coordenadaX real,
  coordenadaY real,
  constraint zonaspk primary key(nome)
);

create table atraccionssoadultos(
  nome character varying(30),
  aforo integer not null,
  alturaMin integer not null,
  custoMantemento real,
  ubicacion character varying(30) not null,
  descricion character varying(500),
  idadeMin integer,
  constraint atraccionssoadultospk primary key (nome),
  zona character varying(30),
  constraint atraccionssoadultosfk1 foreign key (zona) references public.zonas(nome)
  on update cascade on delete set null
);

create table atraccionsfamiliares(
  nome character varying(30),
  aforo integer not null,
  alturaMin integer not null,
  custoMantemento real,
  ubicacion character varying(30) not null,
  descricion character varying(500),
  idadeRecomendada integer,
  constraint atraccionsfamiliarespk primary key (nome),
  zona character varying(30),
  constraint atraccionsfamiliaresfk1 foreign key (zona) references public.zonas(nome)
  on update cascade on delete set null
);

create table hostalaria(
  nomeEstablecemento character varying(30),
  ubicacion character varying(30) not null,
  aforo integer not null,
  horaInicio time,
  horaFin time,
  constraint hostalariapk primary key(nomeEstablecemento),
  zona character varying(30),
  constraint hostalariafk1 foreign key (zona) references public.zonas(nome)
  on update cascade on delete set null
);

create table espectaculos(
  nome character varying(30),
  horaInicio time,
  horaFin time,
  tematica character varying(15),
  descricion character varying(200),
  ubicacion character varying(30) not null,
  constraint espectaculospk primary key(nome),
  zona character varying(30),
  constraint espectaculosfk1 foreign key (zona) references public.zonas(nome)
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
  constraint traballadoresParquepk primary key(dni),
  constraint traballadoresParquefk1 foreign key (nomeAtraccion)
  references public.atraccions(nome)
  on update cascade on delete set null,
  constraint traballadoresParquefk2 foreign key (nomeEspectaculo)
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
  constraint hostaleirospk primary key(dni),
  constraint hostaleirosfk1 foreign key (nomeEstablecemento)
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
  constraint visitantespk primary key(dni)
);

create table ir(
  dataVisita date,
  visitante character(9),
  atraccion character varying(30),
  constraint irpk primary key(dataVisita,visitante,atraccion),
  constraint irfk1 foreign key (visitante)
  references public.visitantes(dni)
  on update cascade on delete cascade,
  constraint irfk2 foreign key (atraccion)
  references public.atraccions(nome)
  on update cascade on delete cascade
);

create table xantar(
  dataVisita date,
  visitante character(9),
  establecemento character varying(30),
  constraint xantarpk primary key(dataVisita,visitante,establecemento),
  constraint xantarfk1 foreign key (visitante)
  references public.visitantes(dni)
  on update cascade on delete cascade,
  constraint xantarfk2 foreign key (establecemento)
  references public.hostalaria(nomeEstablecemento)
  on update cascade on delete cascade
);

create table asistir(
  dataVisita date,
  visitante character(9),
  espectaculo character varying(30),
  constraint asistirpk primary key(dataVisita,visitante,espectaculo),
  constraint asistirfk1 foreign key (visitante)
  references public.visitantes(dni)
  on update cascade on delete cascade,
  constraint asistirfk2 foreign key (espectaculo)
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
  constraint musicapk primary key (codigoCancion)
);

-- Ver a relación con zonas.
create table sistemasDeAudio(
  identificador character (5),
  función character varying (20) not null,
  descripción character varying(150),
  ubicación character varying (30) not null,
  constraint sistemasDeAudiopk primary key (identificador),
  zona character varying(30),
  constraint atraccionsfk1 foreign key (zona) references public.zonas(nome)
  on update cascade on delete set null
);

create table reproducir(
  fechaReproduccion date,
  codigoCancion character(9),
  sistemaAudioIdentificador character (5),
  constraint reproducirpk primary key(fechaReproduccion,codigoCancion,
  sistemaAudioIdentificador),
  constraint reproducirfk1 foreign key (codigoCancion)
  references public.musica(codigoCancion)
  on update cascade on delete cascade,
  constraint reproducirfk2 foreign key (sistemaAudioIdentificador)
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
  constraint DJpk primary key(dni),
  constraint DJfk1 foreign key (identificadorSistema)
  references public.sistemasDeAudio(identificador)
  on update cascade on delete set null
);

create table medios(
  nomeMedio character varying(30),
  tipo character varying(30) not null,
  prezo real not null,
  capacidade integer not null,
  velocidade real not null,
  constraint mediospk primary key (nomeMedio)
);

create table visitantes(
  dni character(9) not null,
  nome character varying(60) not null,
  nacionalidade character varying(30),
  telefono character(9),
  dataNacemento date,
  altura integer not null,
  medioTransporte character varying(30),
  constraint visitantespk primary key(dni)
  constraint mediosfk1 foreign key (medioTransporte)
  references public.medios(nomeMedio)
  on update cascade on delete set null
);
