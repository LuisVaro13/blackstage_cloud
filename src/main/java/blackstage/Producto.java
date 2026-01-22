package blackstage;

import com.google.gson.annotations.SerializedName;

public class Producto extends ProductoItem {
	
	@SerializedName(value = "id", alternate = "_id")
	private String id;
	private String categoria;
	private String descripcion;
	
	public Producto(String marca, String modelo, float precio, int stock, String imagen, String categoria,
			String descripcion) {
		super(marca, modelo, precio, stock, imagen);
		this.categoria = categoria;
		this.descripcion = descripcion;
	}

	public Producto(String id, String marca, String modelo, float precio, int stock, String imagen, String categoria,
			String descripcion) {
		super(marca, modelo, precio, stock, imagen);
		this.id = id;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
