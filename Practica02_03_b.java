package Parte2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import Libreria.Archivotxt;
import Libreria.Librerias;
import Modelo.Categoria;
import Modelo.Insumo;
import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Practica02_03_b extends JInternalFrame implements ActionListener {
	
	// Declaramos los objetos para el manejo de categorías y productos y el archivo categoría
    ListaInsumos listaInsumo;
    ListaCategorias listaCategorias;
    // Objetos archivos
    Archivotxt archivoCategorias;
    Archivotxt archivoInsumos;
    Librerias libreria;
    // Declaración de los objetos de los controles
    private JList ListaCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JPanel panelFormulario;
    private JTable TareaProductos;
    private JLabel Limagen;
    private DefaultListModel<Categoria> modeloCategoria;
    private DefaultTableModel modeloInsumos;
    private JFrame padre;
    private JMenuBar barra;
	//private JTextArea areaProductos;
	
	public void inicializarCategorias() {
		this.listaInsumo = new ListaInsumos();
	    this.archivoCategorias = new Archivotxt("Categoria.txt");
	    this.archivoInsumos = new Archivotxt("Insumos.txt");
	    this.listaCategorias = new ListaCategorias();
	    if (this.archivoCategorias.existe()) {
	        this.listaCategorias.cargarCategorias(this.archivoCategorias.cargar());
	    }
	    if (this.archivoInsumos.existe()) {
	        this.listaInsumo.cargarInsumo(this.archivoInsumos.cargar());
	    }
	    Categoria nodo1 = new Categoria("01","Materiales");
    	Categoria nodo2 = new Categoria("02","Mano de Obra");
    	Categoria nodo3 = new Categoria("03","Maquinaria y Equipo");
    	Categoria nodo4 = new Categoria("04","Servicios");
    	this.listaCategorias.agregarCategoria(nodo1);
      	this.listaCategorias.agregarCategoria(nodo2);
      	this.listaCategorias.agregarCategoria(nodo3);
      	this.listaCategorias.agregarCategoria(nodo4);
	    modeloCategoria = new DefaultListModel<Categoria>();
	    modeloCategoria = this.listaCategorias.generarModelCategorias();
	    this.modeloInsumos = new DefaultTableModel();
	    this.modeloInsumos = this.listaInsumo.getModelo(this.listaCategorias);
	}
	

	public void actualizarTabla() {
	    this.TareaProductos.setModel(this.modeloInsumos);
	    this.TareaProductos.getColumnModel().getColumn(0).setPreferredWidth(5);
	    this.TareaProductos.getColumnModel().getColumn(1).setPreferredWidth(150);
	    this.TareaProductos.getColumnModel().getColumn(2).setPreferredWidth(35);
	}
	
	public Practica02_03_b(JFrame Padre) {
		super("Administracion de Productos");
		//inicializamos las listas para el manejo de datos
		this.inicializarCategorias();
		this.libreria = new Librerias();
		
		 this.padre = Padre;
         this.barra = this.padre.getJMenuBar();
         this.barra = this.libreria.menusPadre(this.barra, false);
		
		setBounds(0, 0, 479, 446);
		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);
        
        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(10,66,71,20);
        panelFormulario.add(labelCategoria);
        
        JLabel labelId= new JLabel("ID:");
        labelId.setBounds(10,9,71,20);
        this.Tid = new JTextField(10);
        this.Tid.setEditable(false);
        this.Tid.setBounds(91,9,147,20);
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);
        
        JLabel labelInsumo= new JLabel("Insumo:");
        labelInsumo.setBounds(10,34,71,20);
        this.Tinsumo = new JTextField(20);
        this.Tinsumo.setEditable(false);
        this.Tinsumo.setBounds(91,35,147,20);
        panelFormulario.add(labelInsumo);
        panelFormulario.add(Tinsumo);
        
        this.Bagregar = new JButton("Agregar");
        this.Bagregar.setBounds(20,104,111,20);
        this.Bagregar.addActionListener(this);
        panelFormulario.setLayout(null);
        panelFormulario.add(Bagregar);
        
        this.Beliminar = new JButton("Eliminar");
        this.Beliminar.setBounds(153,104,111,20);
        this.Beliminar.addActionListener(this);
        panelFormulario.setLayout(null);
        panelFormulario.add(Beliminar);
        
        this.Bsalir = new JButton("Salir");
        this.Bsalir.setBounds(335,376,79,20);
        this.Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);
        
        JScrollPane scrollPane_jlist = new JScrollPane();
        scrollPane_jlist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_jlist.setBounds(91, 61, 157, 42);
        panelFormulario.add(scrollPane_jlist);

        ListaCategoria = new JList();
        scrollPane_jlist.setViewportView(ListaCategoria);
        ListaCategoria.setModel(this.modeloCategoria);
        ListaCategoria.setEnabled(true);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 172, 424, 179);
        panelFormulario.add(scrollPane);
     
        TareaProductos = new JTable();
        this.TareaProductos.setRowSelectionAllowed(true);

        ListSelectionModel cellSelectionModel = this.TareaProductos.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int fila = TareaProductos.getSelectedRow();
                System.out.println((String) TareaProductos.getValueAt(fila, 1));
                actualizarImagen();
            }
        });

        scrollPane.setViewportView(TareaProductos);
        //scrollPane.setBounds(10, 172, 357, 179);
        panelFormulario.add(scrollPane);

        Limagen = new JLabel("Imagen");
        Limagen.setBounds(335, 12, 142, 107);
        panelFormulario.add(Limagen);

        this.actualizarTabla();
        actualizarImagen();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void VolveralInicio() {
	    this.Bagregar.setText("Agregar");
	    this.Bsalir.setText("Salir");
	    this.Beliminar.setEnabled(true);
	    this.Tinsumo.setEditable(false);
	    this.Tid.setEditable(false);
	    this.ListaCategoria.setEnabled(false);
	    this.Tid.setText("");
	    this.Tinsumo.setText("");
	    this.ListaCategoria.setSelectedIndex(0);
	}

	
	public void Altas() {
	    if(this.Bagregar.getText().compareTo("Agregar") == 0) {
	        this.ListaCategoria.setSelectedIndex(0);
	        this.Bagregar.setText("Salvar");
	        this.Bsalir.setText("Cancelar");
	        this.Beliminar.setEnabled(false);
	        this.Tid.setEditable(true);
	        this.Tinsumo.setEditable(true);
	        this.ListaCategoria.setEnabled(true);
	        this.ListaCategoria.setFocusable(true);
	    } else {
	        if (esdatoscompletos()) {
	            String id, insumo, idCategoria;
	            id = this.Tid.getText().trim();
	            insumo = this.Tinsumo.getText().trim();
	            idCategoria = this.modeloCategoria.get(this.ListaCategoria.getSelectedIndex()).getIdcategoria();
	            Insumo nodo = new Insumo(id, insumo, idCategoria);

	            if (!this.listaInsumo.agregarInsumo(nodo)) {
	                String mensaje = "Lo siento, el id " + id + " ya existe y esta asignado a " + this.listaInsumo.buscarInsumo(id);
	                JOptionPane.showMessageDialog(this, mensaje);
	            } else {
	                // Sobreescribimos el archivo cuando se agrega un nuevo elemento
	                this.archivoInsumos.guardar(this.listaInsumo.toArchivo());
	                this.actualizarTabla();
	               
	            }
	        }
	        this.VolveralInicio();
	    }
	}

	public Boolean esdatoscompletos() {
	    boolean enc = false;
	    String id, insumo, idcategoria;
	    id = "";
	    insumo = "";
	    idcategoria = "";
	    id = this.Tid.getText().trim();
	    insumo = this.Tinsumo.getText().trim();
	    if (this.ListaCategoria.getSelectedIndex() >= 0) {
	        idcategoria = this.modeloCategoria.get(this.ListaCategoria.getSelectedIndex()).getIdcategoria();
	    }
	    if (!id.isEmpty() && !insumo.isEmpty() && !idcategoria.isEmpty()) {
	        enc = true;
	    }
	    System.out.println(id + " " + insumo + " " + idcategoria);
	    return enc;
	}
	public void Eliminar() {
	    Object[] opciones = this.listaInsumo.idinsumos();
	    String id = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Eliminar", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

	    if ((id != null) && (!id.isEmpty())) {
	        if (!this.listaInsumo.eliminarInsumoPorId(id)) {
	            JOptionPane.showMessageDialog(this, "No existe este id");
	        } else {
	            // Sobreescribimos el archivo cuando se elimina un elemento
	            this.archivoInsumos.guardar(this.listaInsumo.toArchivo());
	            this.actualizarTabla();
	        }
	    }
	}
	
	public void actualizarImagen() {
	    int fila = TareaProductos.getSelectedRow();
	    String nombreArchivo;
	    String directorioTrabajo = System.getProperty("user.dir");

	    if (fila >= 0) {
	        nombreArchivo = ((String) TareaProductos.getValueAt(fila, 0)) + ".png";
	    } else {
	        nombreArchivo = "000.png";
	    }

	    File fichero = new File(directorioTrabajo + "\\Imagenes\\" + nombreArchivo);
	    System.out.println(this.Limagen.getWidth() + " " + this.Limagen.getHeight() + " " + fichero.getPath());

		String archivo = fichero.getPath();
		this.Limagen.setIcon(libreria.EtiquetaImagen(archivo, this.Limagen.getWidth(), this.Limagen.getHeight()));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.Bagregar)
			this.Altas();
		else if (e.getSource() == this.Beliminar)
			this.Eliminar();
		else if (e.getSource() == Bsalir) {
			if (this.Bsalir.getText().compareTo("Cancelar") == 0)
				this.VolveralInicio();
			else {
				this.barra = this.libreria.menusPadre(this.barra, true);
				this.dispose();
			}
		}
	}

}
