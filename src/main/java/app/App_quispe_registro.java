package app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Autor;
import model.Libro;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App_quispe_registro extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtGenero;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox<String> cboAutor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_quispe_registro frame = new App_quispe_registro();
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
	public App_quispe_registro() {
		setTitle("REGISTRAR LIBROS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE LIBRO :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(96, 98, 152, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("REGISTRO DE LIBROS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(153, 10, 221, 26);
		contentPane.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(231, 69, 172, 19);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigoLibro = new JLabel("CODIGO LIBRO :");
		lblCodigoLibro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigoLibro.setBounds(96, 62, 152, 26);
		contentPane.add(lblCodigoLibro);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(231, 104, 172, 19);
		contentPane.add(txtNombre);
		
		JLabel lblAutor = new JLabel("AUTOR :");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutor.setBounds(153, 134, 81, 26);
		contentPane.add(lblAutor);
		
		cboAutor = new JComboBox();
		cboAutor.setBounds(231, 139, 172, 21);
		contentPane.add(cboAutor);
		
		JLabel lblGenero = new JLabel("GENERO :");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenero.setBounds(139, 173, 81, 26);
		contentPane.add(lblGenero);
		
		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBounds(231, 170, 172, 19);
		contentPane.add(txtGenero);
		
		JLabel lblStock = new JLabel("STOCK :");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStock.setBounds(153, 209, 81, 26);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(231, 213, 172, 19);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("PRECIO :");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(139, 245, 81, 26);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(231, 251, 172, 19);
		contentPane.add(txtPrecio);
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setBounds(231, 304, 112, 26);
		contentPane.add(btnRegistrar);
		
		llenacombo();
	}
	
	void llenacombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em =fabrica.createEntityManager();
		TypedQuery<Autor>query = em.createQuery("Select a from Autor a",Autor.class);
		List<Autor> lstAutor = query.getResultList();
		System.out.println("Cantidad de categorias " +lstAutor);
		if(lstAutor.size() == 0) {
			JOptionPane.showMessageDialog(this,"Combo no tiene datos");
		}else {
			cboAutor.addItem("Seleccione");
			for (Autor a : lstAutor) {
				cboAutor.addItem(a.getCod_autor()+".-"+a.getNom_autor());
			}
			}
		}

	void registrar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em =fabrica.createEntityManager();
		int codigo = leerCodigo();
		String nombre = leerNombre();
		int autor=leerAutor();
		String genero = leerGenero();
		int stock = leerStock();
		double precio = leerPrecio();
		
		Libro l = new Libro();
		l.setId_libro(codigo);
		l.setDescri_libro(nombre);
		l.setCod_autor(autor);
		l.setGenero_libro(genero);
		l.setStock_libro(stock);
		l.setPrecio(precio);
		try {
			em.getTransaction().begin();
			em.persist(l);
			em.getTransaction().commit();
			System.out.println("Registro de nuevo Libro : " + l.getDescri_libro());
			JOptionPane.showMessageDialog(this,"Nuevo Registro de Libro");
		} catch (Exception e) {
			System.out.println("Error : " +e.getClass().getName());
			JOptionPane.showMessageDialog(this, "Error al Registrar");
		}	
	}
	int leerCodigo() {
		int codigo=0;
		codigo=Integer.parseInt(txtCodigo.getText());
		if(codigo==0) {
			JOptionPane.showMessageDialog(this,"Rellene el codigo");
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		}
		return codigo;
	}
	String leerNombre() {
		String nombre=null;
		nombre=txtNombre.getText();
		if(nombre==null) {
			JOptionPane.showMessageDialog(this,"Rellene el nombre");
			txtNombre.setText("");
			txtNombre.requestFocus();
		}
		return nombre;
	}
	int leerAutor() {
		int categoria=-1;
		categoria=cboAutor.getSelectedIndex();
		if(categoria==0) {
			JOptionPane.showMessageDialog(this,"Escoja la categoria");
			cboAutor.setSelectedIndex(0);
			cboAutor.requestFocus();
		}
		return categoria;
	}
	String leerGenero() {
		String nombre=null;
		nombre=txtGenero.getText();
		if(nombre==null) {
			JOptionPane.showMessageDialog(this,"Rellene el Genero");
			txtGenero.setText("");
			txtGenero.requestFocus();
		}
		return nombre;
	}
	int leerStock() {
		int stock=-1;
		stock=Integer.parseInt(txtStock.getText());
		if(stock==-1) {
			JOptionPane.showMessageDialog(this,"Rellene el Stock del Producto");
			txtStock.setText("");
			txtStock.requestFocus();
		}
		return stock;
	}
	double leerPrecio() {
		Double precio=0.0;
		precio=Double.parseDouble(txtPrecio.getText());
		if(precio==0.0) {
			JOptionPane.showMessageDialog(this,"Rellene el Precio del Producto");
			txtPrecio.setText("");
			txtPrecio.requestFocus();
		}
		return precio;
	}
}
