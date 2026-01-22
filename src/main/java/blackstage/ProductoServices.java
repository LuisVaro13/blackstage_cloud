package blackstage;

import java.util.ArrayList;

import spark.Request;
import spark.Response;

public class ProductoServices {

	public static Object getProductos(Request request, Response response) {
		String criterio = request.queryParamOrDefault("criterio", "");
		String expresion = request.queryParamOrDefault("expresion", "");
		if (criterio.equals("") || expresion.equals("")) {
			ArrayList<Producto> productos = ProductoRepository.mostrarProductos();
			if (!productos.isEmpty()) {
				response.type("application/json");
				response.status(200);
				return productos;
			} else {
				response.status(404);
				return "Error mostrando productos";
			}
		} else {
			ArrayList<Producto> productos = ProductoRepository.buscarProductos(criterio, expresion);
			if (!productos.isEmpty()) {
				response.type("application/json");
				response.status(200);
				return productos;
			} else {
				response.status(404);
				return "Ningún producto encontrado con el criterio de búsqueda";
			}
		}
	}
	
	public static Object getProducto(Request request, Response response) {
		String id = request.params("productoID");
		Producto producto = ProductoRepository.buscarProducto(id);
		if (producto != null) {
			response.type("application/json");
			response.status(200);
			return producto;
		} else {
			response.status(404);
			return "Error mostrando producto";
		}
	}

}
