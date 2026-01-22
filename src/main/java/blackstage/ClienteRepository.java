package blackstage;

import java.util.ArrayList;

import kong.unirest.GenericType;
import kong.unirest.Unirest;

public class ClienteRepository {

	private static String REST_url = "https://blackstage-6623.restdb.io/rest/clientes";
	private static String REST_APIKEY = "96741577a2267dc8ded31824c01f66b3a0e9b";

	public static Cliente buscarCliente(String email) {
		ArrayList<Cliente> clientes = Unirest.get(REST_url)
				.header("x-apikey", REST_APIKEY)
				.queryString("q", "{\"email\":\"" + email + "\"}")
				.asObject(new GenericType<ArrayList<Cliente>>() {})
				.getBody();
		if (clientes.isEmpty())
			return null;
		else
			return clientes.getFirst();
	}

	public static Cliente getCliente(String id) {
		Cliente cliente = Unirest.get(REST_url + "/{clienteID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("clienteID", id)
				.asObject(Cliente.class)
				.getBody();
		return cliente;
	}

	public static ClientePublico getClientePublico(String id) {
		ClientePublico cliente = Unirest.get(REST_url + "/{clienteID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("clienteID", id)
				.queryString("h", "{\"$fields\":{\"_id\":1,\"nombre\":1,\"apellidos\":1,\"email\":1}}")
				.asObject(ClientePublico.class)
				.getBody();
		return cliente;
	}

	public static Cliente postCliente(String nombre, String apellidos, String direccion, String email, String password) {
		Cliente cliente = new Cliente(nombre, apellidos, direccion, email, password);
		Cliente cliente2 = Unirest.post(REST_url)
				.header("x-apikey", REST_APIKEY)
				.header("Content-Type", "application/json")
				.body(cliente)
				.asObject(Cliente.class)
				.getBody();
		return cliente2;
	}

	public static Cliente putCliente(String id, String nombre, String apellidos, String direccion, String email, String password) {
		Cliente cliente = new Cliente(nombre, apellidos, direccion, email, password);
		Cliente cliente2 = Unirest.put(REST_url + "/{clienteID}")
				.header("x-apikey", REST_APIKEY)
				.header("Content-Type", "application/json")
				.routeParam("clienteID", id)
				.body(cliente)
				.asObject(Cliente.class)
				.getBody();
		return cliente2;
	}
	
	public static int deleteCliente(String id) {
		int clienteDel = Unirest.delete(REST_url + "/{clienteID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("clienteID", id)
				.asObject(int.class)
				.getStatus();
		return clienteDel;
	}

}
