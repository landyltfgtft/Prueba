package Libros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaLibro {

	private JFrame frmCrudBooks;
	private JTextField txtIdbook;
	private JTextField txtTitle;
	private JTextField txtEditorial;
	private JTextField txtGender;
	private JTextField txtReleaseYear;
	private JButton btnInsertar;
	private JButton btnCargar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	DefaultTableModel tablaModel=new DefaultTableModel();
	int fila = -1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaLibro window = new PantallaLibro();
					window.frmCrudBooks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaLibro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrudBooks = new JFrame();
		frmCrudBooks.setTitle("CRUD BOOKS");
		frmCrudBooks.setBounds(100, 100, 416, 292);
		frmCrudBooks.setLocationRelativeTo(null);
		frmCrudBooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudBooks.getContentPane().setLayout(null);
		
		JLabel lblIdbook = new JLabel("Id Book");
		lblIdbook.setBounds(10, 20, 105, 27);
		frmCrudBooks.getContentPane().add(lblIdbook);
		
		txtIdbook = new JTextField();
		txtIdbook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtIdbook.getText().length()>=11) {
					e.consume();
				}
			}
		});
		txtIdbook.setBounds(113, 24, 205, 18);
		frmCrudBooks.getContentPane().add(txtIdbook);
		txtIdbook.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 100, 90, 12);
		frmCrudBooks.getContentPane().add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtTitle.getText().length()>=100) {
					e.consume();
				}
			}
		});
		txtTitle.setBounds(113, 97, 205, 18);
		frmCrudBooks.getContentPane().add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(10, 133, 90, 12);
		frmCrudBooks.getContentPane().add(lblEditorial);
		
		txtEditorial = new JTextField();
		txtEditorial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtEditorial.getText().length()>=100) {
					e.consume();
				}
			}
		});
		txtEditorial.setBounds(113, 130, 205, 18);
		frmCrudBooks.getContentPane().add(txtEditorial);
		txtEditorial.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 174, 90, 12);
		frmCrudBooks.getContentPane().add(lblGender);
		
		txtGender = new JTextField();
		txtGender.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtGender.getText().length()>=100) {
					e.consume();
				}
			}
		});
		txtGender.setBounds(110, 171, 208, 18);
		frmCrudBooks.getContentPane().add(txtGender);
		txtGender.setColumns(10);
		
		JLabel lblReleaseYear = new JLabel("Release Year");
		lblReleaseYear.setBounds(10, 70, 90, 12);
		frmCrudBooks.getContentPane().add(lblReleaseYear);
		
		txtReleaseYear = new JTextField();
		txtReleaseYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtReleaseYear.getText().length()>=11) {
					e.consume();
				}
			}
		});
		txtReleaseYear.setBounds(113, 67, 208, 18);
		frmCrudBooks.getContentPane().add(txtReleaseYear);
		txtReleaseYear.setColumns(10);
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Libro libro= new Libro(
							Integer.parseInt(txtIdbook.getText()),
							Integer.parseInt(txtReleaseYear.getText()),
							txtTitle.getText(),
							txtEditorial.getText(),
							txtGender.getText()
							);
					if(libro.insertarLibro()) {
						JOptionPane.showMessageDialog(null,"SE INSERTO CORRECTAMENTE");
						limpiar();
					}else {
						JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR");
				}
				}
		});
		btnInsertar.setBounds(10, 210, 84, 20);
		frmCrudBooks.getContentPane().add(btnInsertar);
		
		btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idbook=Integer.parseInt(JOptionPane.showInputDialog("INGRESA ID A CARGAR"));
					Libro h=new Libro();
					h.setIdbook(idbook);
					if(h.cargarLibro()) {
						txtIdbook.setText(""+h.getIdbook());
						txtReleaseYear.setText(""+h.getReleaseYear());
						txtTitle.setText(h.getTitle());
						txtEditorial.setText(h.getEditorial());
						txtGender.setText(h.getGender());
						JOptionPane.showMessageDialog(null,"SE CARGO CORRECTAMENTE");
				}else {
					JOptionPane.showMessageDialog(null, "ERROR AL CARGAR");
				}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR AL CARGAR");
				}
			}
		});
		btnCargar.setBounds(103, 210, 84, 20);
		frmCrudBooks.getContentPane().add(btnCargar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idbook=Integer.parseInt(JOptionPane.showInputDialog("INGRESA ID A ELIMINAR"));
					Libro h=new Libro();
					h.setIdbook(idbook);
					if(h.eliminarLibro()) {
						JOptionPane.showMessageDialog(null,"SE ELIMINO CORRECTAMENTE");
				}else {
					JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
				}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
				}
			}
		});
		btnEliminar.setBounds(197, 210, 84, 20);
		frmCrudBooks.getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Libro libro= new Libro(
							Integer.parseInt(txtIdbook.getText()),
							Integer.parseInt(txtReleaseYear.getText()),
							txtTitle.getText(),
							txtEditorial.getText(),
							txtGender.getText()
							);
					if(libro.actualizarLibro()) {
						JOptionPane.showMessageDialog(null,"SE ACTUALIZO CORRECTAMENTE");
						limpiar();
					}else {
						JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR");
				}
			}
		});
		btnActualizar.setBounds(291, 210, 84, 20);
		frmCrudBooks.getContentPane().add(btnActualizar);
		tablaModel.addColumn("ID BOOK");
		tablaModel.addColumn("RELEASE YEAR");
		tablaModel.addColumn("TITLE");
		tablaModel.addColumn("EDITORIAL");
		tablaModel.addColumn("GENDER");
	}
	public void limpiar() {
		txtIdbook.setText("");
		txtReleaseYear.setText("");
		txtTitle.setText("");
		txtEditorial.setText("");
		txtGender.setText("");
	}
}
