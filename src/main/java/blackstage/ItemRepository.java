package blackstage;

import java.util.ArrayList;

import kong.unirest.GenericType;
import kong.unirest.Unirest;

public class ItemRepository {

	private static String REST_url = "https://blackstage-6623.restdb.io/rest/items";
	private static String REST_APIKEY = "96741577a2267dc8ded31824c01f66b3a0e9b";

	public static Item postItem(String marca, String modelo, float precio, int stock, String imagen, int cantidad,
			float precioTotal, String clienteID, String pedidoID, String productoID) {
		Item item = new Item(marca, modelo, precio, stock, imagen, cantidad, precioTotal, clienteID, pedidoID, productoID);
		Item item2 = Unirest.post(REST_url)
				.header("x-apikey", REST_APIKEY)
				.header("Content-Type", "application/json")
				.body(item)
				.asObject(Item.class)
				.getBody();
		return item2;
	}

	public static ArrayList<ItemPublico> getItems(String pedidoID) {
		ArrayList<ItemPublico> items = Unirest.get(REST_url)
				.header("x-apikey", REST_APIKEY)
				.queryString("q", "{\"pedidoID\":\"" + pedidoID + "\"}")
				.queryString("h", "{\"$fields\":{\"marca\":1,\"modelo\":1,\"precio\":1,\"stock\":1,\"imagen\":1,\"cantidad\":1,\"precioTotal\":1}}")
				.asObject(new GenericType<ArrayList<ItemPublico>>() {})
				.getBody();
		return items;
	}

	public static Item getItem(String id) {
		Item item = Unirest.get(REST_url + "/{itemID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("itemID", id)
				.asObject(Item.class)
				.getBody();
		return item;
	}
	
	public static ItemPublico getItemPublico(String id) {
		ItemPublico item = Unirest.get(REST_url + "/{itemID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("itemID", id)
				.queryString("h", "{\"$fields\":{\"marca\":1,\"modelo\":1,\"precio\":1,\"stock\":1,\"imagen\":1,\"cantidad\":1,\"precioTotal\":1}}")
				.asObject(Item.class)
				.getBody();
		return item;
	}

	public static ItemPublico putItem(Item item, int cantidad) {
		float precioTotal = cantidad * item.getPrecio();
		Item item2 = new Item(item.getMarca(), item.getModelo(), item.getPrecio(), item.getStock(), item.getImagen(),
				cantidad, precioTotal, item.getClienteID(), item.getPedidoID(), item.getProductoID());
		Item item3 = Unirest.put(REST_url + "/{itemID}")
				.header("x-apikey", REST_APIKEY)
				.header("Content-Type", "application/json")
				.routeParam("itemID", item.getId())
				.body(item2)
				.asObject(Item.class)
				.getBody();
		return getItemPublico(item3.getId());
	}
	
	public static int deleteItem(String id) {
		int itemDel = Unirest.delete(REST_url + "/{itemID}")
				.header("x-apikey", REST_APIKEY)
				.routeParam("itemID", id)
				.asObject(int.class)
				.getStatus();
		return itemDel;
	}
	
	public static int deleteItemsPedido(String pedidoID) {
		int itemsDel = Unirest.delete(REST_url + "/*")
				.header("x-apikey", REST_APIKEY)
				.queryString("q", "{\"pedidoID\":\"" + pedidoID + "\"}")
				.asObject(int.class)
				.getStatus();
		return itemsDel;
	}
	
	public static int deleteItemsCliente(String clienteID) {
		int itemsDel = Unirest.delete(REST_url + "/*")
				.header("x-apikey", REST_APIKEY)
				.queryString("q", "{\"clienteID\":\"" + clienteID + "\"}")
				.asObject(int.class)
				.getStatus();
		return itemsDel;
	}

}
