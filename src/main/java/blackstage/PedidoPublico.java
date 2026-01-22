package blackstage;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class PedidoPublico {
	
	@SerializedName(value = "id", alternate = "_id")
	private String id;
	private Date fecha;
	private float precio;
	private String estado;
	
	public PedidoPublico(Date fecha, float precio, String estado) {
		super();
		this.fecha = fecha;
		this.precio = precio;
		this.estado = estado;
	}

	public PedidoPublico(String id, Date fecha, float precio, String estado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.precio = precio;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
