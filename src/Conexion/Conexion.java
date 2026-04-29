package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	Connection cx=null;
	public Connection conectar() {
		try {
			cx=DriverManager.getConnection("jdbc:mysql://localhost/puntodeventa","root","");
			System.out.println("LIKE");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cx;
	}
public static void main(String[]args) {
	Conexion c=new Conexion();
	c.conectar();
}
}
