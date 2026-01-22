package blackstage;

public class ItemPublico extends ProductoItem {
	
	private int cantidad;
	private float precioTotal;
	
	public ItemPublico(String marca, String modelo, float precio, int stock, String imagen, int cantidad, float precioTotal) {
		super(marca, modelo, precio, stock, imagen);
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

}
