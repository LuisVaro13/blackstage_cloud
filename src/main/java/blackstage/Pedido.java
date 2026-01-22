package blackstage;

import java.util.Date;

public class Pedido extends PedidoPublico {
	
	private String clienteID;

	public Pedido(String clienteID, Date fecha, float precio, String estado) {
		super(fecha, precio, estado);
		this.clienteID = clienteID;
	}

	public Pedido(String id, String clienteID, Date fecha, float precio, String estado) {
		super(id, fecha, precio, estado);
		this.clienteID = clienteID;
	}

	public String getClienteID() {
		return clienteID;
	}

	public void setClienteID(String clienteID) {
		this.clienteID = clienteID;
	}
	
}
