package blackstage;

import static spark.Spark*;

public class Main {

	public static void main(String[] args) {

		// Configuración puerto
		port(8080);

		// Configuración del CORS
		options("/*", (request, response) -> {
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Allow-Methods", "*");
			return "OK";
		});
		Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
		
		JsonTransformer jsonTransformer = new JsonTransformer();
		
		get("/productos", ProductoServices::getProductos, jsonTransformer);
		get("/productos/:productoID", ProductoServices::getProducto, jsonTransformer);
		
		get("/clientes", ClienteServices::loginCliente, jsonTransformer);
		post("/clientes", ClienteServices::nuevoCliente, jsonTransformer);
		put("/clientes/:clienteID", ClienteServices::modificarCliente, jsonTransformer);
		delete("/clientes/:clienteID", ClienteServices::eliminarCliente, jsonTransformer);
		
		post("/clientes/:clienteID/pedidos", PedidoServices::nuevoPedido, jsonTransformer);
		get("/clientes/:clienteID/pedidos", PedidoServices::mostrarPedidos, jsonTransformer);
		get("/clientes/:clienteID/pedidos/:pedidoID", PedidoServices::mostrarPedido, jsonTransformer);
		put("/clientes/:clienteID/pedidos/:pedidoID", PedidoServices::modificarPedido, jsonTransformer);
		delete("/clientes/:clienteID/pedidos/:pedidoID", PedidoServices::eliminarPedido, jsonTransformer);
		
		post("/clientes/:clienteID/pedidos/:pedidoID/items", ItemServices::nuevoItem, jsonTransformer);
		get("/clientes/:clienteID/pedidos/:pedidoID/items", ItemServices::mostrarItems, jsonTransformer);
		put("/clientes/:clienteID/pedidos/:pedidoID/items/:itemID", ItemServices::modificarCantidad, jsonTransformer);
		delete("/clientes/:clienteID/pedidos/:pedidoID/items/:itemID", ItemServices::eliminarItem, jsonTransformer);

	}

}

