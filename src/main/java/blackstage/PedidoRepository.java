package blackstage;

import java.util.ArrayList;
import java.util.Date;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
public class PedidoRepository {
	
	private static String REST_url = "https://blackstage-6623.restdb.io/rest/pedidos";
	private static String REST_APIKEY = "96741577a2267dc8ded31824c01f66b3a0e9b";
	
	public static Pedido postPedido(String clienteID, float precio, String estado) {
		Pedido pedido = new Pedido(clienteID, new Date(), precio, estado);
		Pedido pedido2 = Unirest.post(REST_url)
				.header("x-apikey", REST_APIKEY)
				.header("Content-Type", "application/json")
				.body(pedido)
				.asObject(Pedido.class)
				.getBody();
		return pedido2;
	}
	
	public static ArrayList<PedidoPublico> getPedidos(String clienteID){
		ArrayList<PedidoPublico> pedidos = Unirest.get(REST_url)
				.header("x-apikey", REST_APIKEY)
				.queryString("q", "{\"clienteID\":\"" + clienteID + "\"}")
				.queryString("h", "{\"$fields\":{\"_id\":1,\"fecha\":1,\"precio\":1,\"estado\":1}}")
				.asObject(new GenericType<ArrayList<PedidoPublico>>() {})
				.getBody();
		return pedidos;
	}
	
	public static Pedido getPedido(String id) {
		Pedido pedido = Unirest.get(REST_url + "/{pedidoID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("pedidoID", id)
				.asObject(Pedido.class)
				.getBody();
		return pedido;
	}
	
	public static PedidoPublico getPedidoPublico(String id) {
		PedidoPublico pedido = Unirest.get(REST_url + "/{pedidoID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("pedidoID", id)
				.queryString("h", "{\"$fields\":{\"_id\":1,\"fecha\":1,\"precio\":1,\"estado\":1}}")
				.asObject(Pedido.class)
				.getBody();
		return pedido;
	}
	
	public static PedidoPublico putPedido(String id, String clienteID, Date fecha, float precio, String estado) {
		Pedido pedido = new Pedido(clienteID, fecha, precio, estado);
		Pedido pedido2 = Unirest.put(REST_url + "/{pedidoID}")
				.header("x-apikey", REST_APIKEY)
				.header("Content-Type", "application/json")
				.routeParam("pedidoID", id)
				.body(pedido)
				.asObject(Pedido.class)
				.getBody();
		return getPedidoPublico(pedido2.getId());
	}
	
	public static int deletePedido(String id) {
		int pedidoDel = Unirest.delete(REST_url + "/{pedidoID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("pedidoID", id)
				.asObject(int.class)
				.getStatus();
		return pedidoDel;
	}
	
	public static int deletePedidosCliente(String clienteID) {
		int pedidoDel = Unirest.delete(REST_url + "/*")
				.header("x-apikey", REST_APIKEY)
				.queryString("q", "{\"clienteID\":\"" + clienteID + "\"}")
				.asObject(int.class)
				.getStatus();
		return pedidoDel;
	}

}
