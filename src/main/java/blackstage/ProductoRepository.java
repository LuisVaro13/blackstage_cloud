package blackstage;

import java.util.ArrayList;

import kong.unirest.GenericType;
import kong.unirest.Unirest;

public class ProductoRepository {
	
	private static String REST_url = "https://blackstage-6623.restdb.io/rest/productos";
	private static String REST_APIKEY = "96741577a2267dc8ded31824c01f66b3a0e9b";
	
	public static ArrayList<Producto> buscarProductos(String criterio, String expresion) {
		ArrayList<Producto> productos = Unirest.get(REST_url)
				.header("x-apikey", REST_APIKEY)
				.queryString("q", "{\"" + criterio + "\":{\"$regex\":\"" + expresion + "\"}}")
				.asObject(new GenericType<ArrayList<Producto>>() {})
				.getBody();
		return productos;
	}
	
	public static ArrayList<Producto> mostrarProductos() {
		ArrayList<Producto> productos = Unirest.get(REST_url)
				.header("x-apikey", REST_APIKEY)
				.asObject(new GenericType<ArrayList<Producto>>() {})
				.getBody();
		return productos;
	}
	
	public static Producto buscarProducto(String id) {
		Producto producto = Unirest.get(REST_url + "/{productoID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("productoID", id)
				.asObject(Producto.class)
				.getBody();
		return producto;
	}

}
