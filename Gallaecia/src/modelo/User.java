package modelo;

import baseDatos.Password;
import baseDatos.UserType;

public class User {
	private Integer id;
	private String dni;
	private String nome;
	private String username;
	private Password password;
	private UserType userType;

	public User(Integer id, String dni, String nome, String username, String pass, boolean isAdmin) {
		this.id = id;
		this.dni = dni;
		this.nome = nome;
		this.username = username;
		this.password = new Password(username, pass);
		this.userType = isAdmin ? UserType.Admin : UserType.Guest;
	}

	public String getDni() {
		return this.dni;
	}

	public String getNome() {
		return this.nome;
	}

	public String getUsername() {
		return this.username;
	}

	public Password getPassword() {
		return this.password;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public boolean isAdmin() {
		return this.userType == UserType.Admin;
	}
}
