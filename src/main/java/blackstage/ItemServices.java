package blackstage;

import java.util.ArrayList;

import spark.Request;
import spark.Response;

public class ItemServices {

	public static Object nuevoItem(Request request, Response response) {
		String clienteID = request.params("clienteID");
		String pedidoID = request.params("pedidoID");
		String productoID = request.queryParams("productoID");
		int cantidad = Integer.valueOf(request.queryParams("cantidad"));
		Producto producto = ProductoRepository.buscarProducto(productoID);
		float precioTotal = cantidad * producto.getPrecio();
		if (cantidad > 0) {
			Item item = ItemRepository.postItem(producto.getMarca(), producto.getModelo(), producto.getPrecio(),
					producto.getStock(), producto.getImagen(), cantidad, precioTotal, clienteID, pedidoID, productoID);
			if (item != null && item.getId() != null) {
				ArrayList<ItemPublico> items = ItemRepository.getItems(pedidoID);
				float precioPedido = 0;
				for (ItemPublico i : items) {
					precioPedido += i.getPrecioTotal();
				}
				Pedido pedido = PedidoRepository.getPedido(pedidoID);
				PedidoPublico pedidoMod = PedidoRepository.putPedido(pedidoID, pedido.getClienteID(), pedido.getFecha(), precioPedido, pedido.getEstado());
				if (pedidoMod != null && pedidoMod.getId() != null) {
					response.type("application/json");
					response.status(201);
					return ItemRepository.getItemPublico(item.getId());
				} else {
					response.status(404);
					return "Error actualizando pedido";
				}
			} else {
				response.status(404);
				return "Error añadiendo producto al pedido";
			}
		} else {
			response.status(400);
			return "La cantidad no puede ser menor de 1";
		}
	}

	public static Object mostrarItems(Request request, Response response) {
		String pedidoID = request.params("pedidoID");
		ArrayList<ItemPublico> items = ItemRepository.getItems(pedidoID);
		if (!items.isEmpty()) {
			response.type("application/json");
			response.status(200);
			return items;
		} else {
			response.status(404);
			return "No hay items en el pedido nº " + pedidoID;
		}
	}
	
	public static Object modificarCantidad(Request request, Response response) {
		String pedidoID = request.params("pedidoID");
		String id = request.params("itemID");
		int cantidad = Integer.valueOf(request.queryParams("cantidad"));
		ItemPublico itemMod = ItemRepository.putItem(ItemRepository.getItem(id), cantidad);
		if (itemMod != null) {
			ArrayList<ItemPublico> items = ItemRepository.getItems(pedidoID);
			float precioPedido = 0;
			for (ItemPublico i : items) {
				precioPedido += i.getPrecioTotal();
			}
			Pedido pedido = PedidoRepository.getPedido(pedidoID);
			PedidoPublico pedidoMod = PedidoRepository.putPedido(pedidoID, pedido.getClienteID(), pedido.getFecha(), precioPedido, pedido.getEstado());
			if (pedidoMod != null && pedidoMod.getId() != null) {
				response.type("application/json");
				response.status(201);
				return ItemRepository.getItemPublico(id);
			} else {
				response.status(404);
				return "Error actualizando pedido";
			}
		} else {
			response.status(404);
			return "Error modificando la cantidad";
		}
	}
	
	public static Object eliminarItem(Request request, Response response) {
		String id = request.params("itemID");
		int itemDel = ItemRepository.deleteItem(id);
		if (itemDel == 200) {
			response.status(itemDel);
			return "Item eliminado correctamente";
		} else {
			response.status(404);
			return "Error eliminando item";
		}
	}

}
