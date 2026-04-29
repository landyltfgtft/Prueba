package Libros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import menu.Producto;

public class DataLibro {
	public Connection conectar() {
		Connection cx=null;
		try {
			cx=DriverManager.getConnection("jdbc:mysql://localhost/books","root","");
			System.out.println("CONEXION EXITOSA");
		} catch (SQLException e) {
			System.out.println("FALLO CONEXION");
		}
		return cx;
	}
	public static void main(String[] cecyto) {
		DataLibro dh= new DataLibro();
		dh.conectar();
	}
	public boolean insertarLibro( Libro h) {
		PreparedStatement ps=null;
		try {
			ps=conectar().prepareStatement("INSERT INTO books VALUES (?,?,?,?,?)");
			ps.setInt(1, h.getIdbook());
			ps.setInt(2, h.getReleaseYear());
			ps.setString(3, h.getTitle());
			ps.setString(4, h.getEditorial());
			ps.setString(5, h.getGender());
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			return false;
		}
	}
	public boolean cargarLibro(Libro h) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conectar().prepareStatement("SELECT*FROM books WHERE Idbook=?");
			ps.setInt(1, h.getIdbook());
			rs=ps.executeQuery();
			if(rs.next()) {
				h.setReleaseYear(rs.getInt(2));
				h.setTitle(rs.getString(3));
				h.setEditorial(rs.getString(4));
				h.setGender(rs.getString(5));
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	public boolean eliminarLibro(int idbook) {
		PreparedStatement ps=null;
		try {
			ps=conectar().prepareStatement("DELETE FROM books WHERE Idbook=?");
			ps.setInt(1, idbook);
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			return false;
		}

	}
	public boolean actualizarLibro(Libro h) {
		PreparedStatement ps=null;
		try {
			ps=conectar().prepareStatement("UPDATE books SET ReleaseYear=?,Title=?,Editorial=?,Gender=? WHERE Idbook=?");
			ps.setInt(5, h.getIdbook());
			ps.setInt(1, h.getReleaseYear());
			ps.setString(2, h.getTitle());
			ps.setString(3, h.getEditorial());
			ps.setString(4, h.getGender());
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
 