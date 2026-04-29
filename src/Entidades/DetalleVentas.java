package Entidades;

public class DetalleVentas {
	int idDetalle;
	int idVenta;
	int idProducto;
	int Cantidad;

	public int getIdVenta() {
		return idVenta;
	}

	public DetalleVentas() {

	}

	public DetalleVentas(int idProducto, int cantidad) {
		super();
		this.idProducto = idProducto;
		this.Cantidad = cantidad;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

}
