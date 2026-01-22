package blackstage;

public class ProductoItem {
	
	private String marca;
	private String modelo;
	private float precio;
	private int stock;
	private String imagen;
	
	public ProductoItem(String marca, String modelo, float precio, int stock, String imagen) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.stock = stock;
		this.imagen = imagen;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
