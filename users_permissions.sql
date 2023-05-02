/* Usuario de administrador */
create user admin with password 'admin';
grant all privileges on all tables in schema public to admin;

/* Usuario invitado */
create user guest with password null;

grant select on table
users, espectaculos, hostalaria, atraccions, valoracions, zonas, visitantes, medios, xantar, asistir, ir, traballadoresparque, hostaleiros, dj
to guest;

grant delete on table
xantar, asistir, ir
to guest;

grant insert on table
xantar, asistir, ir, valoracions, visitantes, users
to guest;

grant usage on valoracions_identificador_seq to guest;
grant execute on function check_person_exists to guest;
