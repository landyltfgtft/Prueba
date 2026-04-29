package menu;

import javax.swing.JTextField;

public class Producto {
	int idProducto, idProveedor;
	double precioCompra, precioVenta, Stock;
	String nombreProveedor;
	String nombre;
	DataProveedor dp=new DataProveedor();
	
	public Producto(int idProducto, String nombre , double precioCompra, double precioVenta, double stock, String nombreProveedor) {
		super();
		this.idProducto = idProducto;
		this.nombre=nombre;
		this.nombreProveedor=nombreProveedor;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		Stock = stock;
	}

	public Producto(int idProducto, String nombre, int idProveedor, double precioCompra, double precioVenta, double stock) {
		super();
		this.idProducto = idProducto;
		this.nombre=nombre;
		this.idProveedor = idProveedor;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		Stock = stock;
	}
	public Producto() {
		super();
	}
	
	
	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public double getStock() {
		return Stock;
	}
	public void setStock(double stock) {
		Stock = stock;
	
	}
	public boolean insertarProducto() {
		return dp.insertarProducto(this);
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", idProveedor=" + idProveedor + ", precioCompra=" + precioCompra
				+ ", precioVenta=" + precioVenta + ", Stock=" + Stock + ", nombreProveedor=" + nombreProveedor
				+ ", nombre=" + nombre + "]";
	}
	public boolean eliminarProducto() {
		return dp.eliminarProducto(this.getIdProducto());
	}

	public boolean actualizarProducto() {
		return dp.actualizarProducto(this);
		
	


		
	}
	
	
}
