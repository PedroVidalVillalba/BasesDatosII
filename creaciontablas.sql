create table zonas(
  nome character varying(30),
  extension real,
  coordenadaX real,
  coordenadaY real,
  constraint zonaspk primary key(nome)
);

create table atraccions(
nome character varying(30),
aforo integer not null,
alturaMin integer not null,
custoMantemento real,
descricion character varying(500),
zona character varying(30),
constraint atraccionsfk1 foreign key (zona) references public.zonas(nome),
constraint atraccionspk primary key (nome)
);

create table atraccionssoadultos(
  nome character varying(30),
  idadeMin integer,
  constraint atraccionssoadultosfk1 foreign key (nome) references public.atraccions(nome)
  on update cascade on delete set null
);

create table atraccionsfamiliares(
  nome character varying(30),
  idadeRecomendada integer,
  constraint atraccionsfamiliaresfk1 foreign key (nome) references public.atraccions(nome)
  on update cascade on delete set null
);

create table hostalaria(
  nomeEstablecemento character varying(30),
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
  constraint visitantespk primary key(dni),
  constraint mediosfk1 foreign key (medioTransporte)
  references public.medios(nomeMedio)
  on update cascade on delete set null
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
  data date,
  visitante character(9),
  establecemento character varying(30),
  constraint xantarpk primary key(data,visitante,establecemento),
  constraint xantarfk1 foreign key (visitante)
  references public.visitantes(dni)
  on update cascade on delete cascade,
  constraint xantarfk2 foreign key (establecemento)
  references public.hostalaria(nomeEstablecemento)
  on update cascade on delete cascade
);

create table asistir(
  data date,
  visitante character(9),
  espectaculo character varying(30),
  constraint asistirpk primary key(data,visitante,espectaculo),
  constraint asistirfk1 foreign key (visitante)
  references public.visitantes(dni)
  on update cascade on delete cascade,
  constraint asistirfk2 foreign key (espectaculo)
  references public.espectaculos(nome)
  on update cascade on delete cascade
);

create table musica(
  codigoCancion character (9),
  nome character varying (30) not null,
  clasificacion character varying (30) not null,
  popularidade integer,
  artista character varying(30) not null,
  album character varying(30),
  constraint musicapk primary key (codigoCancion)
);


create table sistemasDeAudio(
  identificador character (5),
  funcion character varying (20) not null,
  descricion character varying(150),
  constraint sistemasDeAudiopk primary key (identificador),
  zona character varying(30),
  constraint atraccionsfk1 foreign key (zona) references public.zonas(nome)
  on update cascade on delete set null
);

create table reproducir(
  data date,
  musica character(9),
  sistema character (5),
  constraint reproducirpk primary key(data,musica,sistema),
  constraint reproducirfk1 foreign key (musica)
  references public.musica(codigoCancion)
  on update cascade on delete cascade,
  constraint reproducirfk2 foreign key (sistema)
  references public.sistemasDeAudio(identificador)
  on update cascade on delete cascade
);

create table DJ(
  dni character(9),
  nome character varying(60) not null,
  rua character varying(40),
  numero integer,
  cp integer,
  localidade character varying(30),
  salario real not null,
  telefono character(9),
  fechaInicio date not null,
  fechaNacemento date,
  formacion character varying(100) not null,
  sistema character varying(30) ,
  constraint DJpk primary key(dni),
  constraint DJfk1 foreign key (sistema)
  references public.sistemasDeAudio(identificador)
  on update cascade on delete set null
);

create or replace function check_person_exists(dni char(9), nome varchar(60))
    returns boolean
    language sql
as $$
select exists (select 1 from visitantes as v where v.dni = $1 and v.nome = $2)
           or exists (select 1 from traballadoresparque as t where t.dni = $1 and t.nome = $2)
           or exists (select 1 from hostaleiros as h where h.dni = $1 and h.nome = $2)
           or exists (select 1 from dj as d where d.dni = $1 and d.nome = $2)
$$;

create table Users (
    id_user serial primary key,
    dni char(9),
    nome varchar(60),
    username varchar(60),
    password char(64),
    constraint user_exists check (check_person_exists(dni, nome))
);
