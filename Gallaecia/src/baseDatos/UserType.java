package baseDatos;

/**
 * Clase que guarda los tipos de usuarios válidos en la base de datos
 * Admin: administrador de la base de datos. Tiene permiso para añadir, modificar y eliminar datos de las tablas
 * Guest: invitado. Puede consultar elementos básicos de la base de datos como las atracciones o restaurantes
 */
public enum UserType {
	Admin,
	Guest
}
