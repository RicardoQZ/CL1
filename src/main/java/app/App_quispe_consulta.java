package app;

import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Libro;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class App_quispe_consulta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5365634416329309665L;
	private JPanel contentPane;
	private JTable tblSalida;
	DefaultTableModel modelo= new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_quispe_consulta frame = new App_quispe_consulta();
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
	public App_quispe_consulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTADO DE LIBROS");
		lblNewLabel.setBounds(307, 10, 209, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 120, 746, 269);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Autor");
		modelo.addColumn("Genero");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		scrollPane.setViewportView(tblSalida);
		scrollPane.setViewportView(tblSalida);
		listado();
	}
	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em=fabrica.createEntityManager();
		TypedQuery<Libro>query2=em.createQuery("Select l from Libro l",Libro.class);
		List<Libro> lstLibro = query2.getResultList();
		for(Libro l : lstLibro) {
			Object fila[]= {l.getId_libro(),l.getDescri_libro(),l.getCod_autor(),l.getGenero_libro(),
					l.getStock_libro(),l.getPrecio()};
		modelo.addRow(fila);
		}
	}
	
}
