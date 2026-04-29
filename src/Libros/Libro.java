package Libros;

public class Libro {
int Idbook, ReleaseYear;
String Title,Editorial,Gender;
DataLibro dh=new DataLibro();

public Libro(int idbook, int releaseYear, String title, String editorial, String gender) {
	this.Idbook = idbook;
	this.ReleaseYear = releaseYear;
	this.Title = title;
	this.Editorial = editorial;
	this.Gender = gender;
}
public Libro() {
	

	
}
public boolean cargarLibro() {
	return dh.cargarLibro(this);
}
public boolean eliminarLibro() {
	return dh.eliminarLibro(this.getIdbook());
}
public boolean actualizarLibro() {
	return dh.actualizarLibro(this);
}

public int getIdbook() {
	return Idbook;
}
public void setIdbook(int idbook) {
	Idbook = idbook;
}
public int getReleaseYear() {
	return ReleaseYear;
}
public void setReleaseYear(int releaseYear) {
	ReleaseYear = releaseYear;
}
public String getTitle() {
	return Title;
}
public void setTitle(String title) {
	Title = title;
}
public String getEditorial() {
	return Editorial;
}
public void setEditorial(String editorial) {
	Editorial = editorial;
}
public String getGender() {
	return Gender;
}
public void setGender(String gender) {
	Gender = gender;
}
public boolean insertarLibro() {
	return dh.insertarLibro(this);
}

}
