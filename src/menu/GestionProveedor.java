package menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class GestionProveedor {
    public JFrame frmGestionProveedor;
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTable tblProveedores;
    DefaultTableModel model=new DefaultTableModel();
    DataProveedor dp=new DataProveedor();
    ArrayList<Proveedor>listaProveedores=new ArrayList<Proveedor>();
    int fila=-1;
    Proveedor p;


    public GestionProveedor() {
        frmGestionProveedor = new JFrame();
        frmGestionProveedor.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowActivated(WindowEvent e) {
        		cargarCombo();
        	}
        });
        frmGestionProveedor.setTitle("GESTION PROVEEDOR");
        frmGestionProveedor.setBounds(100, 100, 506, 349);
        frmGestionProveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmGestionProveedor.setLocationRelativeTo(null);
        frmGestionProveedor.getContentPane().setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(27, 24, 44, 12);
        frmGestionProveedor.getContentPane().add(lblNombre);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(27, 76, 44, 12);
        frmGestionProveedor.getContentPane().add(lblEmail);
        
        JLabel lblTelefono = new JLabel("Telefono");
        lblTelefono.setBounds(27, 122, 44, 12);
        frmGestionProveedor.getContentPane().add(lblTelefono);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(105, 21, 96, 18);
        frmGestionProveedor.getContentPane().add(txtNombre);
        txtNombre.setColumns(10);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(105, 73, 96, 18);
        frmGestionProveedor.getContentPane().add(txtEmail);
        txtEmail.setColumns(10);
        
        txtTelefono = new JTextField();
        txtTelefono.setBounds(105, 119, 96, 18);
        frmGestionProveedor.getContentPane().add(txtTelefono);
        txtTelefono.setColumns(10);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setIcon(new ImageIcon("C:\\Users\\landy\\Downloads\\Agregar_resized.png"));
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(txtNombre.getText().length()==0 || txtEmail.getText().length()==0 || txtTelefono.getText().length()==0) {
            		JOptionPane.showMessageDialog(null,"CAMPOS VACIOS" );
        			}
        			Proveedor p=new Proveedor(0, txtNombre.getText(),txtEmail.getText(),txtTelefono.getText());
        			if(p.insertarProveedor()) {
        				limpiar();
        				refrescarTabla();
        				JOptionPane.showMessageDialog(null,"SE AGREGO CORRECTAMENTE PROVEEDOR" );
        			}else{
        				JOptionPane.showMessageDialog(null,"ERROR" );
        			}
        		} catch(Exception e2) {
        			JOptionPane.showMessageDialog(null,"ERROR" );
        	}
        	}
        		
        }); 
        btnAgregar.setBounds(11, 219, 112, 34);
        frmGestionProveedor.getContentPane().add(btnAgregar);
        
        JScrollPane sclProveedor = new JScrollPane();
        sclProveedor.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        	}
        });
        sclProveedor.setBounds(217, 24, 209, 229);
        frmGestionProveedor.getContentPane().add(sclProveedor);
        
        tblProveedores = new JTable();
        tblProveedores.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		fila=tblProveedores.getSelectedRow();
        		 p=listaProveedores.get(tblProveedores.getSelectedRow());
        		txtNombre.setText(p.getNombreproveedor());
        		txtEmail.setText(p.getEmail());
        		txtTelefono.setText(p.getTelefono());
        	}
        });
        model.addColumn("NOMBRE");
        model.addColumn("EMAIL");
        model.addColumn("TELEFONO");
        tblProveedores.setModel(model);
        sclProveedor.setViewportView(tblProveedores);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setIcon(new ImageIcon("C:\\Users\\landy\\Downloads\\Elimina_resized.png"));
        btnEliminar. setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar. setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar. setVerticalAlignment(SwingConstants.CENTER);
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(fila!=-1) {
            			int op=JOptionPane.showConfirmDialog(null,"ESTAS SEGURO?","ELIMINAR REGISTRO",JOptionPane.YES_NO_OPTION);
            			if(op==0) {
            				if (p.eliminarProveedor()) {
            					refrescarTabla();
            					limpiar();
            					fila=-1;
            					JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
            				}else {
            					JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
            				}
            			}else {
            				JOptionPane.showMessageDialog(null, "FALTA SELECCIONAR REGISTRO");
            			}
        			}
        		}catch (Exception e2) {
        			JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
        		}
        	}
        });
        btnEliminar.setBounds(11, 172, 112, 37);
        frmGestionProveedor.getContentPane().add(btnEliminar);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setIcon(new ImageIcon("C:\\Users\\landy\\Downloads\\Actualizar_resized.png"));
        btnActualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        			try {
            			if(txtNombre.getText().length()==0 || txtEmail.getText().length()==0 || txtTelefono.getText().length()==0) {
                		JOptionPane.showMessageDialog(null,"CAMPOS VACIOS" );
            			}
            			 p.setNombreproveedor(txtNombre.getText());
            			 p.setEmail(txtEmail.getText());
            			 p.setTelefono(txtTelefono.getText());
            			if(p.actualizarProveedor()) {
            				limpiar();
            				refrescarTabla();
            				JOptionPane.showMessageDialog(null,"SE ACTUALIZO CORRECTAMENTE PROVEEDOR" );
            			}else{
            				JOptionPane.showMessageDialog(null,"ERROR" );
            			}
            		} catch(Exception e2) {
            			JOptionPane.showMessageDialog(null,"ERROR" );
        		}
        	}
        });
        btnActualizar.setBounds(11, 263, 112, 39);
        frmGestionProveedor.getContentPane().add(btnActualizar);
        refrescarTabla();
    }

	protected void cargarCombo() {
		// TODO Auto-generated method stub
		
		
	}
	public void limpiar() {
		txtNombre.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		//frmGestionProveedor.setVisible(false);
	}
	public void refrescarTabla() {
		listaProveedores= dp.cargarProveedores();
		while(model.getRowCount()>0)model.removeRow(0);
		for(Proveedor p : listaProveedores) {
			model.addRow(new Object[] {p.getNombreproveedor(), p.getEmail(),p.getTelefono()});
		}
		
	}
}

