package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Entidades.Productos;

public class DataProductos {
	Conexion c = new Conexion();

	public boolean insertarProducto(Productos p) {
		PreparedStatement ps = null;
		try {
			ps = c.conectar().prepareStatement("INSERT INTO productos VALUES(null, ?,?,?,?)");
			ps.setString(1, p.getCodigoBarras());
			ps.setString(2, p.getNombre());
			ps.setDouble(3, p.getPrecioVentas());
			ps.setInt(4, p.getStock());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	public ArrayList<Productos> cargarProductos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Productos> listaProductos = new ArrayList<Productos>();
		try {
			ps = c.conectar().prepareStatement("SELECT * FROM productos");
			rs = ps.executeQuery();
			while (rs.next()) {
				listaProductos.add(
						new Productos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
		}
		return listaProductos;
	}

	public boolean eliminarProducto(Productos p) {
		PreparedStatement ps = null;
		try {
			ps = c.conectar().prepareStatement("DELETE FROM productos WHERE id_producto=?");
			ps.setInt(1, p.getIdProducto());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean actualizarProducto(Productos p) {
		PreparedStatement ps = null;
		try {
			ps = c.conectar().prepareStatement("UPDATE productos SET codigo_barras=?,nombre=?,precio_venta=?,stock=? WHERE id_producto=?");
			ps.setString(1, p.getCodigoBarras());
			ps.setString(2, p.getNombre());
			ps.setDouble(3, p.getPrecioVentas());
			ps.setInt(4, p.getStock());
			ps.setInt(5, p.getIdProducto());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
		}
	}
		
		
}
