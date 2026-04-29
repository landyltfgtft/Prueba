package Entidades;

import Data.DataProductos;

public class Productos {
	int idProducto;
	String codigoBarras;
	String Nombre;
	Double precioVentas;
	int Stock;
	DataProductos dp= new DataProductos();
	
	public Productos() {
	}
	public Productos(int idProducto, String codigoBarras, String nombre, Double precioVentas, int stock) {
		this.idProducto = idProducto;
		this.codigoBarras = codigoBarras;
		this.Nombre = nombre;
		this.precioVentas = precioVentas;
		this.Stock = stock;
	}
	public Productos(String codigoBarras, String nombre, Double precioVentas, int stock) {
		this.codigoBarras = codigoBarras;
		this.Nombre = nombre;
		this.precioVentas = precioVentas;
		this.Stock = stock;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Double getPrecioVentas() {
		return precioVentas;
	}
	public void setPrecioVentas(Double precioVentas) {
		this.precioVentas = precioVentas;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	@Override
	public String toString() {
		return "Productos [idProducto=" + idProducto + ", codigoBarras=" + codigoBarras + ", Nombre=" + Nombre
				+ ", precioVentas=" + precioVentas + ", Stock=" + Stock + "]";
	}
	public boolean insertarProducto() {
		return dp.insertarProducto(this);
	}
	public boolean eliminarProducto() {
		return dp.eliminarProducto(this);
	}
	public boolean actualizarProducto() {
		return dp.actualizarProducto(this);
	}

}

