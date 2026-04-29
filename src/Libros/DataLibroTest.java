package Libros;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataLibroTest {

	@Test
	void testInsertarLibro() {
		Libro a=new Libro (19, 1, "h", "h", "j");
				assertTrue (a. insertarLibro());
	}

	@Test
	void testCargarLibro() {
		Libro a=new Libro (1, 0, "a", "a", "a");
				assertTrue (a. cargarLibro());
	}

	@Test
	void testEliminarLibro() {
		Libro a=new Libro (1, 1, "h", "h", "j");
				assertTrue (a. eliminarLibro());
	}

	@Test
	void testActualizarLibro() {
		Libro a=new Libro (3,4, "jkjkj", "df", "dkh");
				assertTrue (a. actualizarLibro());	}

}
