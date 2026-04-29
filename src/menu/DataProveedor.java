package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProveedor {
	Connection cx;

	public Connection conectar() {
		try {
			cx = DriverManager.getConnection("jdbc:mysql://localhost/alumnos", "root", "");
			System.out.println("CONEXION EXITOSA");
		} catch (SQLException e) {
			System.out.println("ALGO SALIO MAL");

		}
		return cx;
	}

	public static void main(String[] cecytobebes) {
		DataProveedor dp = new DataProveedor();
		dp.conectar();
	}

	public ArrayList<Proveedor> cargarProveedores() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
		try {
			ps = conectar().prepareStatement("SELECT * FROM provedor");
			rs = ps.executeQuery();
			while (rs.next()) {
				Proveedor p = new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean insertarProveedor(Proveedor p) {
		PreparedStatement ps = null;
		try {
			ps = conectar().prepareStatement("INSERT INTO provedor VALUES(null,?,?,?)");
			ps.setString(1, p.getNombreproveedor());
			ps.setString(2, p.getEmail());
			ps.setString(3, p.getTelefono());
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			return false;
		}

	}

	public boolean insertarProducto(Producto p) {
		PreparedStatement ps = null;
		try {
			ps = conectar().prepareStatement("INSERT INTO producto VALUES(null,?,?,?,?,?)");
			ps.setDouble(1, p.getIdProveedor());
			ps.setString(2, p.getNombre());
			ps.setDouble(3, p.getPrecioVenta());
			ps.setDouble(4, p.getPrecioCompra());
			ps.setDouble(5, p.getStock());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Producto> cargarProducto() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Producto> lista = new ArrayList<Producto>();
		try {
			ps = conectar().prepareStatement("SELECT a.idproducto," + "a.nombre," + "a.preciop," + "a.precioc,"
					+ "a.stock," + "b.nombreproveedor "
					+ "FROM producto a INNER JOIN provedor b ON (a.idprovedor=b.idprovedor)");
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto p = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
						rs.getDouble(5), rs.getString(6));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean eliminarProveedor(int idProveedor) {
		PreparedStatement ps = null;
		try {
			ps = conectar().prepareStatement("DELETE FROM provedor WHERE idprovedor=?");
			ps.setInt(1, idProveedor);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean actualizarProveedor(Proveedor p) {
		PreparedStatement ps = null;
		try {
			ps = conectar().prepareStatement("UPDATE provedor SET nombreproveedor=?,email=?,telefono=? WHERE idprovedor=?");
			ps.setString(1, p.getNombreproveedor());
			ps.setString(2, p.getEmail());
			ps.setString(3, p.getTelefono());
			ps.setDouble(4, p.getIdProveedor());
			
			

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}

	public boolean eliminarProducto(int idProducto) {
		PreparedStatement ps = null;
		try {
			ps = conectar().prepareStatement("DELETE FROM producto WHERE idproducto=?");
			ps.setInt(1, idProducto);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
	}
}

	public boolean actualizarProducto(Producto p) {
		PreparedStatement ps = null;
		try {
			ps = conectar().prepareStatement("UPDATE producto SET nombre=?, idprovedor=?, preciop=?, precioc=?, stock=? WHERE idproducto=?");
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getIdProveedor());
			ps.setDouble(3, p.getPrecioVenta());
			ps.setDouble(4, p.getPrecioCompra());
			ps.setDouble(5, p.getStock());
			ps.setInt(6, p.getIdProducto());
			System.out.println(ps.toString());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
		return false;
	}
}
}