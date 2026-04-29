package Interfaces;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Data.DataProductos;
import Entidades.Productos;
import Libros.Libro;
import menu.menu;

import javax.swing.event.ChangeEvent;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class PantallaProductos extends JFrame {
	public JFrame frmGestionProductos;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigoBarras;
	private JTextField txtNombre;
	private JSlider sldPrecioVenta;
	private JSlider sldStock;
	private JLabel lblValorPrecioVenta;
	private JLabel lblValorStock;
	private JScrollPane scrollPane_1;
	private JTable tblProductos;
	DefaultTableModel model = new DefaultTableModel();
	ArrayList<Productos> listaProductos = new ArrayList<Productos>();
	DataProductos dp = new DataProductos();
	Productos p = new Productos();
	int fila = -1;
	private JButton btnEliminar;
	private JButton btnActualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaProductos frame = new PantallaProductos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaProductos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\landy\\Downloads\\descarga (1).png"));
		setTitle("GESTION PRODUCTOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigoBarras = new JLabel("Codigo Barras");
		lblCodigoBarras.setBounds(10, 42, 84, 13);
		contentPane.add(lblCodigoBarras);

		JLabel lblTTitulo = new JLabel("GESTION PRODUCTOS");
		lblTTitulo.setForeground(new Color(0, 0, 0));
		lblTTitulo.setBounds(170, 10, 155, 19);
		contentPane.add(lblTTitulo);

		txtCodigoBarras = new JTextField();
		txtCodigoBarras.setBounds(125, 39, 96, 18);
		contentPane.add(txtCodigoBarras);
		txtCodigoBarras.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 82, 84, 12);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(125, 79, 96, 18);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setBounds(10, 126, 84, 12);
		contentPane.add(lblPrecioVenta);

		lblValorPrecioVenta = new JLabel("0.0");
		lblValorPrecioVenta.setBounds(231, 126, 44, 12);
		contentPane.add(lblValorPrecioVenta);

		lblValorStock = new JLabel("0.0");
		lblValorStock.setBounds(231, 148, 44, 12);
		contentPane.add(lblValorStock);

		sldPrecioVenta = new JSlider();
		sldPrecioVenta.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblValorPrecioVenta.setText("$ " + sldPrecioVenta.getValue());
			}
		});
		sldPrecioVenta.setBounds(81, 124, 140, 25);
		contentPane.add(sldPrecioVenta);

		sldStock = new JSlider();
		sldStock.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblValorStock.setText("$ " + sldStock.getValue());
			}
		});
		sldStock.setBounds(91, 148, 130, 25);
		contentPane.add(sldStock);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 170, 44, 12);
		contentPane.add(lblStock);

		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.setIcon(redimensionar(32, 32, "/img/delete.png"));
		btnAgregar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAgregar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregar.setVerticalAlignment(SwingConstants.CENTER);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtCodigoBarras.getText().length()==0||txtNombre.getText().length()==0) {
						JOptionPane. showMessageDialog(null, "CAMPOS VACIOS", "ERROR", JOptionPane. ERROR_MESSAGE);
						return;
						}
					Productos p = new Productos(txtCodigoBarras.getText(), txtNombre.getText(),
							(double) sldPrecioVenta.getValue(), sldStock.getValue());
					if (!p.insertarProducto()) {
						JOptionPane.showMessageDialog(null, "SE INSERTO CORRECTAMENTE");
					} else {
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR");
					
				}
			}
		});
		btnAgregar.setBounds(10, 185, 112, 55);
		contentPane.add(btnAgregar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(364, 148, 0, 0);
		contentPane.add(scrollPane);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(260, 42, 243, 210);
		contentPane.add(scrollPane_1);

		tblProductos = new JTable();
		tblProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblProductos.getSelectedRow();
				p = listaProductos.get(fila);
				txtCodigoBarras.setText(p.getCodigoBarras());
				txtNombre.setText(p.getNombre());
				sldPrecioVenta.setValue(Integer.parseInt("" + p.getPrecioVentas()));
				sldStock.setValue(p.getStock());
			}
		});
		model.addColumn("CODIGO DE BARRAS");
		model.addColumn("NOMBRE");
		model.addColumn("PRECIO");
		model.addColumn("STOCK");
		scrollPane_1.setViewportView(tblProductos);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setIcon(redimensionar(32, 32, "/img/delete.png"));
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setVerticalAlignment(SwingConstants.CENTER);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila != -1) {
						int opcion = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar producto??",
								"ELIMINAR PRODUCTO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
								redimensionar(32, 32, "/img/cecy.png"));
						if (opcion == 0) {
							if (p.eliminarProducto()) {
								cargarProductos();
							
								JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE", "EXITO!!!!!",
										JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecy.png"));
							} else {
								JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR", "ERROR",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "FALTA SELECCIONAR PRODUCTO", "ERROR",
									JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecy.png"));
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar.setBounds(137, 183, 84, 57);
		contentPane.add(btnEliminar);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setIcon(redimensionar(32, 32, "/img/edit.png"));
		btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnActualizar.setVerticalAlignment(SwingConstants.CENTER);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila != -1) {
						if(txtCodigoBarras.getText().length()==0||txtNombre.getText().length()==0) {
							JOptionPane. showMessageDialog(null, "CAMPOS VACIOS", "ERROR", JOptionPane. ERROR_MESSAGE);
							return;
							}
					p.setCodigoBarras(txtCodigoBarras.getText());
					p.setNombre(txtNombre.getText());
					p.setPrecioVentas((double)sldPrecioVenta.getValue());
					p.setStock(sldStock.getValue());
					if (!p.actualizarProducto()) {
						JOptionPane. showMessageDialog(null, "ERROR AL ACTUALIZAR", "ERROR", JOptionPane. ERROR_MESSAGE);
					
					} else {
						fila=-1;
					limpiar();
					cargarProductos();
					JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE", "EXITO!!!!",
							JOptionPane.QUESTION_MESSAGE, redimensionar (32, 32, "/img/cecy.png"));
					
					}
					}else {
						JOptionPane.showMessageDialog(null, "FALTA SELECCIONAR PRODUCTO", "ERROR",
								JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecy.png"));
					}
					} catch (Exception ex) {
						JOptionPane. showMessageDialog(null, "ERROR AL ACTUALIZAR");
						
					
					}
			}
		});
		btnActualizar.setBounds(81, 250, 112, 54);
		contentPane.add(btnActualizar);
		cargarProductos();

	}

	public void cargarProductos() {
		while (model.getRowCount() > 0)
			model.removeRow(0);
		listaProductos = dp.cargarProductos();
		for (Productos p : listaProductos) {
			model.addRow(new Object[] { p.getCodigoBarras(), p.getNombre(), p.getPrecioVentas(), p.getStock() });
		}
		tblProductos.setModel(model);
	}

	public void limpiar() {
		txtCodigoBarras.setText("");
		txtNombre.setText("");


	}

	public ImageIcon redimensionar(int w, int h, String ruta) {
		ImageIcon icono = new ImageIcon(PantallaProductos.class.getResource(ruta));
		Image imagenOriginal = icono.getImage();
		Image imagenEscalada = imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
		return iconRedimensionado;
	}
}
