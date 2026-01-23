package blackstage;

import java.util.ArrayList;

import spark.Request;
import spark.Response;

public class PedidoServices {
	
	public static Object nuevoPedido(Request request, Response response) {
		String clienteID = request.params("clienteID");
		float precio = 0;
		String estado = "En curso";
		Pedido pedido = PedidoRepository.postPedido(clienteID, precio, estado);
		if (pedido != null && pedido.getId() != null) {
			response.type("application/json");
			response.status(201);
			return PedidoRepository.getPedidoPublico(pedido.getId());
		} else {
			response.status(404);
			return "Error creando pedido";
		}
	}
	
	public static Object mostrarPedidos(Request request, Response response) {
		String clienteID = request.params("clienteID");
		ArrayList<PedidoPublico> pedidos = PedidoRepository.getPedidos(clienteID);
		if (!pedidos.isEmpty()) {
			response.type("application/json");
			response.status(200);
			return pedidos;
		} else {
			response.status(404);
			return "No hay pedidos";
		}
	}
	
	public static Object mostrarPedido(Request request, Response response) {
		String id = request.params("pedidoID");
		PedidoPublico pedido = PedidoRepository.getPedidoPublico(id);
		if (pedido != null) {
			response.type("application/json");
			response.status(200);
			return pedido;
		} else {
			response.status(404);
			return "Error mostrando pedido";
		}
	}
	
	public static Object modificarPedido(Request request, Response response) {
		String id = request.params("pedidoID");
		Pedido pedido = PedidoRepository.getPedido(id);
		String estado = request.queryParamOrDefault("estado", "");
		if (estado.equals(""))
			estado = pedido.getEstado();
		float precio = 0;
		ArrayList<ItemPublico> items = ItemRepository.getItems(id);
		for (ItemPublico i : items) {
			precio += i.getPrecioTotal();
		}
		PedidoPublico pedidoMod = PedidoRepository.putPedido(id, pedido.getClienteID(), pedido.getFecha(), precio, estado);
		if (pedidoMod != null) {
			response.type("application/json");
			response.status(200);
			return pedidoMod;
		} else {
			response.status(404);
			return "Error modificando el pedido";
		}
	}
	
	public static Object eliminarPedido(Request request, Response response) {
		String id = request.params("pedidoID");
		int itemsDel = ItemRepository.deleteItemsPedido(id);
		if (itemsDel == 200) {
			int pedidoDel = PedidoRepository.deletePedido(id);
			if (pedidoDel == 200) {
				response.type("application/json");
				response.status(pedidoDel);
				return "Pedido eliminado correctamente";
			} else {
				response.status(404);
				return "Error eliminando pedido";
			}
		} else {
			response.status(404);
			return "Error eliminando pedido";
		}
	}

}

