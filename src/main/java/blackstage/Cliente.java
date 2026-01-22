package blackstage;

public class Cliente extends ClientePublico {
	
	private String password;
	private String direccion;
	
	public Cliente(String nombre, String apellidos, String direccion, String email, String password) {
		super(nombre, apellidos, email);
		this.password = password;
		this.direccion = direccion;
	}
	
	public Cliente(String id, String nombre, String apellidos, String direccion, String email, String password) {
		super(id, nombre, apellidos, email);
		this.password = password;
		this.direccion = direccion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
