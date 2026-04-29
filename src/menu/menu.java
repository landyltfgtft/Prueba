package menu;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	GestionProveedor gp = new GestionProveedor();
	DataProveedor dp = new DataProveedor();
	ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
	ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	private JComboBox cmbProveedor;
	DefaultComboBoxModel modelCombo = new DefaultComboBoxModel();
	DefaultTableModel tablaModel = new DefaultTableModel();
	private JTable table;
	int fila = -1;
	Producto p;
	private JSpinner spnPrecioPublico;
	private JSpinner spnStock;
	private JSpinner spnPrecioCompra;

	public static void main(String[] args) {
		menu frame = new menu();
		frame.setVisible(true);
	}

	public menu() {
		setTitle("MENU PROVEEDOR");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				cargarCombo();
				cargarProductos();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 476);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnGestionProveedor = new JButton("");
		btnGestionProveedor.setIcon(new ImageIcon("C:\\Users\\landy\\Downloads\\Agregar_resized.png"));
		btnGestionProveedor.setIcon(redimensionar(32, 32, "/img/add-removebg-preview.png"));
		btnGestionProveedor. setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGestionProveedor. setHorizontalTextPosition(SwingConstants.CENTER);
		btnGestionProveedor. setVerticalAlignment(SwingConstants.CENTER);
		btnGestionProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gp.frmGestionProveedor.setVisible(true);
			}
		});
		btnGestionProveedor.setBounds(272, 87, 54, 29);
		contentPane.add(btnGestionProveedor);

		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(10, 95, 70, 12);
		contentPane.add(lblProveedor);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 39, 44, 20);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(77, 35, 111, 29);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		spnPrecioCompra = new JSpinner();
		spnPrecioCompra.setModel(new SpinnerNumberModel(1.0, 1.0, 100.0, 0.1));
		spnPrecioCompra.setBounds(84, 121, 54, 31);
		contentPane.add(spnPrecioCompra);

		spnPrecioPublico = new JSpinner();
		spnPrecioPublico.setModel(new SpinnerNumberModel(1.0, 1.0, 100.0, 0.1));
		spnPrecioPublico.setBounds(84, 177, 54, 31);
		contentPane.add(spnPrecioPublico);

		cmbProveedor = new JComboBox();
		cmbProveedor.setBounds(77, 87, 190, 29);
		contentPane.add(cmbProveedor);

		spnStock = new JSpinner();
		spnStock.setModel(new SpinnerNumberModel(1, 1, 100, 1.0));
		spnStock.setBounds(84, 218, 54, 31);
		contentPane.add(spnStock);

		JLabel lblPrecio = new JLabel("Precio compra");
		lblPrecio.setBounds(10, 130, 79, 12);
		contentPane.add(lblPrecio);

		JLabel lblPrecioPublico = new JLabel("Precio Publico");
		lblPrecioPublico.setBounds(10, 189, 64, 12);
		contentPane.add(lblPrecioPublico);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 227, 44, 12);
		contentPane.add(lblStock);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(redimensionar(32, 32, "/img/add-removebg-preview.png"));
		btnAgregar. setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAgregar. setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregar. setVerticalAlignment(SwingConstants.CENTER);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
					}
					Producto pro = new Producto(0, txtNombre.getText(),
							lista.get(cmbProveedor.getSelectedIndex()).getIdProveedor(),
							Double.parseDouble(spnPrecioPublico.getValue().toString()),
							Double.parseDouble(spnPrecioCompra.getValue().toString()),
							Double.parseDouble(spnStock.getValue().toString()));
					if (pro.insertarProducto()) {
						cargarProductos();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR");
				}

			}

		});
		btnAgregar.setBounds(171, 272, 111, 67);
		contentPane.add(btnAgregar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(326, 10, 284, 353);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				p = listaProductos.get(fila);
				txtNombre.setText(p.getNombre());
				cmbProveedor.setSelectedItem(p.getNombreProveedor());
				spnPrecioPublico.setValue(p.getPrecioVenta());
				spnPrecioCompra.setValue(p.getPrecioCompra());
				spnStock.setValue(p.getStock());

			}
		});
		tablaModel.addColumn("Nombre");
		tablaModel.addColumn("Precio Publico");
		tablaModel.addColumn("Precio Compra");
		tablaModel.addColumn("Stock");
		tablaModel.addColumn("Proveedor");
		table.setModel(tablaModel);
		scrollPane.setViewportView(table);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(redimensionar(32, 32, "/img/delete-removebg-preview.png"));
		btnEliminar. setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar. setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar. setVerticalAlignment(SwingConstants.CENTER);

		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					if (fila != -1) {
						int op = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO?", "ELIMINAR REGISTRO",
								JOptionPane.YES_NO_OPTION);
						if (op == 0) {
							if (p.eliminarProducto()) {
								cargarProductos();
								limpiar();
								fila = -1;
								JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
							} else {
								JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
							}
						} else {
							JOptionPane.showMessageDialog(null, "FALTA SELECCIONAR REGISTRO");
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
				}
			}
		});
		btnEliminar.setBounds(42, 279, 96, 60);
		contentPane.add(btnEliminar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(redimensionar(32, 32, "/img/edit-removebg-preview.png"));
		btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnActualizar.setVerticalAlignment(SwingConstants.CENTER);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (fila != -1) {

						if (txtNombre.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
						}
						p.setNombre(txtNombre.getText());
						p.setIdProveedor(lista.get(cmbProveedor.getSelectedIndex()).getIdProveedor());
						p.setPrecioVenta(Double.parseDouble(spnPrecioPublico.getValue().toString()));
						p.setPrecioCompra(Double.parseDouble(spnPrecioCompra.getValue().toString()));
						p.setStock(Double.parseDouble(spnStock.getValue().toString()));

						if (p.actualizarProducto()) {
							cargarProductos();
							limpiar();
							fila = -1;
							JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE");
						} else {
							JOptionPane.showMessageDialog(null, "ERROR");
						}
					} else {
						JOptionPane.showMessageDialog(null, "FALTA SELECCIONAR REGISTRO");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnActualizar.setBounds(108, 364, 120, 65);
		contentPane.add(btnActualizar);

		cargarCombo();
		cargarProductos();
	}
	
	public ImageIcon redimensionar(int w, int h, String ruta) {
		ImageIcon icono=new ImageIcon(menu.class.getResource(ruta));
		Image imagenOriginal=icono.getImage();
		Image imagenEscalada=imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
	return iconRedimensionado;
	
	}

	public void cargarProductos() {
		listaProductos = dp.cargarProducto();
		while (tablaModel.getRowCount() > 0) {
			tablaModel.removeRow(0);
		}
		for (Producto p : listaProductos) {
			System.out.println(p.toString());
			Object o[] = new Object[5];
			o[0] = p.getNombre();
			o[1] = p.getPrecioVenta();
			o[2] = p.getPrecioCompra();
			o[3] = p.getStock();
			o[4] = p.getNombreProveedor();
			tablaModel.addRow(o);
		}
		table.setModel(tablaModel);
	}

	public void cargarCombo() {
		lista = dp.cargarProveedores();
		modelCombo.removeAllElements();
		for (Proveedor p : lista) {
			System.out.println(p.getNombreproveedor());
			modelCombo.addElement(p.getNombreproveedor());
			cmbProveedor.setModel(modelCombo);
		}
	}

	public void limpiar() {
		txtNombre.setText("");
		cmbProveedor.setSelectedIndex(0);
		spnPrecioPublico.setValue(1.0);
		spnPrecioCompra.setValue(1.0);
		spnStock.setValue(p.getStock());

	}
}
