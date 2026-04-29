package menu;

public class Proveedor {
    int idProveedor;
    String nombreproveedor, email, telefono;
    DataProveedor dp=new DataProveedor();

    public Proveedor() {
        super();
    }

    public Proveedor(int idProveedor, String nombreProveedor, String email, String telefono) {
        super();
        this.idProveedor = idProveedor;
        this.nombreproveedor = nombreProveedor;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombreproveedor() {
		return nombreproveedor;
	}

	public void setNombreproveedor(String nombreproveedor) {
		this.nombreproveedor = nombreproveedor;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public boolean insertarProveedor() {
		
		return dp.insertarProveedor(this);
	}

	public boolean eliminarProveedor() {
		
		return dp.eliminarProveedor(this.getIdProveedor());
	}

	public boolean actualizarProveedor() {
		
		return dp.actualizarProveedor(this);
	}

	
}