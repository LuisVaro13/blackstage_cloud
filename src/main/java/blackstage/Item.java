package blackstage;

import com.google.gson.annotations.SerializedName;

public class Item extends ItemPublico {

	@SerializedName(value = "id", alternate = "_id")
	private String id;
	private String clienteID;
	private String pedidoID;
	private String productoID;

	public Item(String marca, String modelo, float precio, int stock, String imagen, int cantidad, float precioTotal,
			String clienteID, String pedidoID, String productoID) {
		super(marca, modelo, precio, stock, imagen, cantidad, precioTotal);
		this.clienteID = clienteID;
		this.pedidoID = pedidoID;
		this.productoID = productoID;
	}

	public Item(String id, String marca, String modelo, float precio, int stock, String imagen, int cantidad,
			float precioTotal, String clienteID, String pedidoID, String productoID) {
		super(marca, modelo, precio, stock, imagen, cantidad, precioTotal);
		this.id = id;
		this.clienteID = clienteID;
		this.pedidoID = pedidoID;
		this.productoID = productoID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getClienteID() {
		return clienteID;
	}

	public void setClienteID(String clienteID) {
		this.clienteID = clienteID;
	}

	public String getPedidoID() {
		return pedidoID;
	}

	public void setPedidoID(String pedidoID) {
		this.pedidoID = pedidoID;
	}

	public String getProductoID() {
		return productoID;
	}

	public void setProductoID(String productoID) {
		this.productoID = productoID;
	}

}
