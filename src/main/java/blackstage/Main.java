package blackstage;

import  spark.Spark;

public class Main {

	public static void main(String[] args) {

		// Configuración puerto
		Spark.port(8080);

		// Configuración del CORS
		Spark.options("/*", (request, response) -> {
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Allow-Methods", "*");
			return "OK";
		});
		Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
		
		JsonTransformer jsonTransformer = new JsonTransformer();
		
		Spark.get("/productos", ProductoServices::getProductos, jsonTransformer);
		Spark.get("/productos/:productoID", ProductoServices::getProducto, jsonTransformer);
		
		Spark.get("/clientes", ClienteServices::loginCliente, jsonTransformer);
		Spark.post("/clientes", ClienteServices::nuevoCliente, jsonTransformer);
		Spark.put("/clientes/:clienteID", ClienteServices::modificarCliente, jsonTransformer);
		Spark.delete("/clientes/:clienteID", ClienteServices::eliminarCliente, jsonTransformer);
		
		Spark.post("/clientes/:clienteID/pedidos", PedidoServices::nuevoPedido, jsonTransformer);
		Spark.get("/clientes/:clienteID/pedidos", PedidoServices::mostrarPedidos, jsonTransformer);
		Spark.get("/clientes/:clienteID/pedidos/:pedidoID", PedidoServices::mostrarPedido, jsonTransformer);
		Spark.put("/clientes/:clienteID/pedidos/:pedidoID", PedidoServices::modificarPedido, jsonTransformer);
		Spark.delete("/clientes/:clienteID/pedidos/:pedidoID", PedidoServices::eliminarPedido, jsonTransformer);
		
		Spark.post("/clientes/:clienteID/pedidos/:pedidoID/items", ItemServices::nuevoItem, jsonTransformer);
		Spark.get("/clientes/:clienteID/pedidos/:pedidoID/items", ItemServices::mostrarItems, jsonTransformer);
		Spark.put("/clientes/:clienteID/pedidos/:pedidoID/items/:itemID", ItemServices::modificarCantidad, jsonTransformer);
		Spark.delete("/clientes/:clienteID/pedidos/:pedidoID/items/:itemID", ItemServices::eliminarItem, jsonTransformer);

	}

}



