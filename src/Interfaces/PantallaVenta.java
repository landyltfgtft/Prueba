package Interfaces;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import Entidades.DetalleVentas;
import Entidades.Productos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PantallaVenta {

	private JFrame frmSistemaCecyPos;
	PantallaProductos pp = new PantallaProductos();
	private JButton btnAgregarProducto;
	private JButton btnBuscarProducto;
	private JTable table;

	/**
	 * Launch the application.
	 */
	DefaultTableModel model = new DefaultTableModel();
	public ArrayList<Productos> listaProductos = new ArrayList<Productos>();
	private JButton btnCobrar;
	Productos p = new Productos();

	String codigoBarras = "";
	private JTextField txtCodigoBarras;
	double total = 0.0;
	ArrayList<DetalleVentas> detalleVenta = new ArrayList<DetalleVentas>();
	private JLabel lblMxn;
	private JLabel lblMxn_1;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaVenta window = new PantallaVenta();
					window.frmSistemaCecyPos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaVenta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaCecyPos = new JFrame();
		frmSistemaCecyPos.setTitle("SISTEMA CECY POS2026");
		frmSistemaCecyPos.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaProductos.class.getResource("/img/cecy.png")));
		frmSistemaCecyPos.setBounds(100, 100, 651, 499);
		frmSistemaCecyPos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnProductos = new JButton("PRODUCTOS");
		btnProductos.setBounds(10, 15, 127, 50);
		btnProductos.setIcon(redimensionar(32, 32, "/img/productos.png"));
		btnProductos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProductos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProductos.setVerticalAlignment(SwingConstants.CENTER);
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pp.setVisible(true);
			}
		});
		frmSistemaCecyPos.getContentPane().setLayout(null);
		frmSistemaCecyPos.getContentPane().add(btnProductos);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(522, 10, 115, 60);
		btnSalir.setIcon(redimensionar(32, 32, "/img/salirt.png"));
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setVerticalAlignment(SwingConstants.CENTER);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE SALIR?", "SALIR",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "ADIOS");
					System.exit(0);
				}
			}
		});
		frmSistemaCecyPos.getContentPane().add(btnSalir);

		txtCodigoBarras = new JTextField();
		txtCodigoBarras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					agregarProducto();
				}
			}
		});
		txtCodigoBarras.setBounds(78, 87, 230, 36);
		frmSistemaCecyPos.getContentPane().add(txtCodigoBarras);
		txtCodigoBarras.setColumns(10);

		JLabel lblCodigoBarras = new JLabel("Codigo Barras");
		lblCodigoBarras.setBounds(10, 98, 64, 13);
		frmSistemaCecyPos.getContentPane().add(lblCodigoBarras);

		btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();

			}
		});
		btnAgregarProducto.setIcon(redimensionar(25, 25, "/img/add.png"));
		btnAgregarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		btnAgregarProducto.setVerticalTextPosition(SwingConstants.CENTER);
		btnAgregarProducto.setVerticalAlignment(SwingConstants.CENTER);
		btnAgregarProducto.setBounds(312, 86, 165, 36);
		frmSistemaCecyPos.getContentPane().add(btnAgregarProducto);

		JButton btnBuscarProducto = new JButton("Buscar Producto");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

		});
		btnBuscarProducto.setIcon(redimensionar(25, 25, "/img/buscar.png"));
		btnBuscarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscarProducto.setVerticalTextPosition(SwingConstants.CENTER);
		btnBuscarProducto.setVerticalAlignment(SwingConstants.CENTER);
		btnBuscarProducto.setBounds(469, 86, 158, 36);
		frmSistemaCecyPos.getContentPane().add(btnBuscarProducto);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 164, 617, 156);
		frmSistemaCecyPos.getContentPane().add(scrollPane);

		table = new JTable();
		model.addColumn("CODIGO");
		model.addColumn("NOMBRE");
		model.addColumn("PRECIO");
		model.addColumn("CANTIDAD");
		model.addColumn("IMPORTE");
		table.setModel(model);
		scrollPane.setViewportView(table);

		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(312, 330, 103, 50);
		frmSistemaCecyPos.getContentPane().add(lblTotal);

		lblMxn_1 = new JLabel("$ 0.00  MXN");
		lblMxn_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMxn_1.setBounds(467, 330, 79, 50);
		frmSistemaCecyPos.getContentPane().add(lblMxn_1);

		btnCobrar = new JButton("COBRAR");
		btnCobrar.setIcon(redimensionar(32, 32, "/img/cobrar.png"));
		btnCobrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCobrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCobrar.setVerticalAlignment(SwingConstants.CENTER);
		btnCobrar.setBounds(322, 390, 121, 62);
		frmSistemaCecyPos.getContentPane().add(btnCobrar);
		
		JLabel lblFechaHora = new JLabel("");
		lblFechaHora.setBounds(354, 15, 158, 12);
		frmSistemaCecyPos.getContentPane().add(lblFechaHora);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(143, 34, 44, 12);
		frmSistemaCecyPos.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(181, 31, 181, 46);
		frmSistemaCecyPos.getContentPane().add(textField);
		textField.setColumns(10);
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime ahora = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
				String fechaHoraFormateada = ahora.format(formatter);
				lblFechaHora.setText(fechaHoraFormateada);
			}
		});
		timer.start();
	}

	public ImageIcon redimensionar(int w, int h, String ruta) {
		ImageIcon icono = new ImageIcon(PantallaProductos.class.getResource(ruta));
		Image imagenOriginal = icono.getImage();
		Image imagenEscalada = imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
		return iconRedimensionado;
	}

	public void agregarProducto() {
		codigoBarras = txtCodigoBarras.getText();

		if (codigoBarras.length() == 0) {
			mensaje("Prductos sin Stock","ERROR");
			JOptionPane.showMessageDialog(null, "", "ERROR",
					JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecy.png"));
			return;
		}

		int id = -1;
		for (Productos p : pp.listaProductos) {
			if (p.getCodigoBarras().equals(codigoBarras)) {
				id = p.getIdProducto();
			}
		}
		System.out.println("ID: "+id);

		if (id == -1) {
			mensaje("Prductos sin Stock","ERROR");
			return;
		}
		

		if (!siEsta(id)) {
			if(buscarProducto(id).getStock()>0){
				detalleVenta.add(new DetalleVentas(id, 1));
			}else {
				mensaje("Prductos sin Stock","ERROR");
			}
			
		} else {
			incrementaCantidad(id);
		}

		while (model.getRowCount() > 0)
			model.removeRow(0);

		total = 0;
		for (DetalleVentas d : detalleVenta) {
			Productos p = buscarProducto(d.getIdProducto());
			System.out.println(p.toString());
			model.addRow(new Object[] { p.getCodigoBarras(), p.getNombre(), p.getPrecioVentas(), d.getCantidad(),
					(p.getPrecioVentas() * d.getCantidad()) });
			total += (p.getPrecioVentas() * d.getCantidad());
		}

		table.setModel(model);
		lblMxn_1.setText("" + aMoneda(total));
	}

	public Productos buscarProducto(int idProducto) {
		for (Productos p : pp.listaProductos) {
			if (p.getIdProducto() == idProducto) {
				return p;
			}
		}
		return null;
	}

	public void incrementaCantidad(int idProducto) {
		for(DetalleVentas d: detalleVenta) {
			if(idProducto == d.getIdProducto()) {
		if((d.getCantidad()+1)>buscarProducto(idProducto).getStock()) {
			mensaje("YA NO SE PUEDE AGREGAR MAS CANTIDAD","ERROR");
		}else {
			d.setCantidad(d.getCantidad()+1);
			
		}	
	}
	}
	}
	public boolean siEsta(int idProducto) {
		boolean si = false;
		for (DetalleVentas d : detalleVenta) {
			if (d.getIdProducto() == idProducto) {
				return true;
			}
		}
		return false;
	}

	public static String aMoneda(double cantidad) {
		Locale localeMexico = new Locale("es", "MX");
		NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeMexico);
		return formatoMoneda.format(cantidad);
	}
	public void mensaje(String msj, String titulo) {
		JOptionPane.showMessageDialog(null, msj,titulo,JOptionPane.QUESTION_MESSAGE,
				redimensionar(32, 32, "/img/cecy.png"));
	}
}
