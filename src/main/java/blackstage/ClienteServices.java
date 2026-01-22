package blackstage;

import spark.Request;
import spark.Response;

public class ClienteServices {
	
	public static Object loginCliente(Request request, Response response) {
		String email = request.queryParamOrDefault("email", "");
		String password = request.queryParamsSafe("password");
		Cliente cliente = ClienteRepository.buscarCliente(email);
		if (cliente != null) {
			if (!cliente.getPassword().equals(password)) {
				response.status(404);
				return "Contraseña incorrecta";
			} else {
				response.type("application/json");
				response.status(200);
				return ClienteRepository.getClientePublico(cliente.getId());
			}
		} else {
			response.status(404);
			return "El cliente no existe"; 
		}
	}
	
	public static Object nuevoCliente(Request request, Response response) {
		String nombre = request.queryParamOrDefault("nombre", "");
		String apellidos = request.queryParamOrDefault("apellidos", "");
		String direccion = request.queryParamOrDefault("direccion", "");
		String email = request.queryParamOrDefault("email", "");
		String password = request.queryParamOrDefault("password", "");
		if (nombre.equals("") || apellidos.equals("") || direccion.equals("") || email.equals("") || password.equals("")) {
			response.status(400);
			return "Todos los campos deben ser rellenados";
		} else if (ClienteRepository.buscarCliente(email) != null) {
			response.status(409);
			return "Ya existe un cliente con este email";
		} else {
			Cliente cliente = ClienteRepository.postCliente(nombre, apellidos, direccion, email, password);
			if (cliente != null && cliente.getId() != null) {
				response.type("application/json");
				response.status(201);
				return ClienteRepository.getClientePublico(cliente.getId());
			} else {
				response.status(404);
				return "Error creando cliente";
			}
		}
	}
	
	public static Object modificarCliente(Request request, Response response) {
		String id = request.params("clienteID");
		Cliente cliente = ClienteRepository.getCliente(id);
		String nombre = request.queryParamOrDefault("nombre", "");
		if (nombre.equals("")) 
				nombre = cliente.getNombre();
		String apellidos = request.queryParamOrDefault("apellidos", "");
		if (apellidos.equals(""))
				apellidos = cliente.getApellidos();
		String direccion = request.queryParamOrDefault("direccion", "");
		if (direccion.equals(""))
				direccion = cliente.getDireccion();
		String email = request.queryParamOrDefault("email", "");
		if (email.equals(""))
				email = cliente.getEmail();
		String password = request.queryParamOrDefault("password", "");
		if (password.equals(""))
			password = cliente.getPassword();
		Cliente cliente2 = ClienteRepository.buscarCliente(email);
		if (!cliente2.getId().equals(id)) {
			response.status(409);
			return "Ya existe un cliente con este email";
		} else {
			Cliente clienteMod = ClienteRepository.putCliente(id, nombre, apellidos, direccion, email, password);
			if (clienteMod != null) {
				response.type("application/json");
				response.status(200);
				return ClienteRepository.getClientePublico(clienteMod.getId());
			} else {
				response.status(404);
				return "Error modificando cliente";
			}
		}
	}
	
	public static Object eliminarCliente(Request request, Response response) {
		String id = request.params("clienteID");
		int itemsDel = ItemRepository.deleteItemsCliente(id);
		if (itemsDel == 200) {
			int pedidoDel = PedidoRepository.deletePedidosCliente(id);
			if (pedidoDel == 200) {
				int clienteDel = ClienteRepository.deleteCliente(id);
				if (clienteDel == 200) {
					response.status(clienteDel);
					return "Cliente eliminado correctamente";
				} else {
					response.status(404);
					return "Error eliminando cliente";
				}
			} else {
				response.status(404);
				return "Error eliminando cliente";
			}
		} else {
			response.status(404);
			return "Error eliminando cliente";
		}
	}

}
