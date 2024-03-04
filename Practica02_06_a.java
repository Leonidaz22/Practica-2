package Parte3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Libreria.Archivotxt;
import Modelo.Categoria;
import Modelo.Insumo;
import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;

public class Practica02_06_a extends JFrame implements ActionListener {

	ListaInsumos listaInsumo;
    ListaCategorias listaCategorias;
    Archivotxt archivoCategorias;
    Archivotxt archivoInsumos;

    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea areaProductos;
    private JPanel panelFormulario, panel1, panel2, panel3, panel4;
    private JList<Categoria> listaCategoria;
    private DefaultListModel<Categoria> modeloCategoria;
    private JLabel labelCategoria, labelId, labelInsumo;
    private JScrollPane scrollPane, scrollPane_jlist;
    
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
        modeloCategoria = this.listaCategorias.generarModelCategorias();
    }
    
    public Practica02_06_a() {
    	super("Administración de Productos");
        this.inicializarCategorias();

       
        panel1 = new JPanel(new FlowLayout());
        panel2 = new JPanel(new FlowLayout());
        panel3 = new JPanel(new FlowLayout());
        panel4 = new JPanel(new FlowLayout());
        
        labelId = new JLabel("ID:");
        labelInsumo = new JLabel("Insumo:");
        Tid = new JTextField(10);
        Tinsumo = new JTextField(20);
        
        panel1.add(labelId);
        panel1.add(Tid);
        panel1.add(labelInsumo);
        panel1.add(Tinsumo);
        
        labelCategoria = new JLabel("Categoría:");
        scrollPane_jlist = new JScrollPane();
        scrollPane_jlist.setPreferredSize(new Dimension(200, 30));
        
        listaCategoria = new JList<Categoria>();
        scrollPane_jlist.setViewportView(listaCategoria);
        listaCategoria.setModel(modeloCategoria);
        listaCategoria.setEnabled(true);
        panel2.add(labelCategoria);
        panel2.add(scrollPane_jlist);
     
         scrollPane = new JScrollPane();
         areaProductos = new JTextArea(10, 40);
         scrollPane.setViewportView(areaProductos);
         
         if (listaInsumo.toString() != null) {
             areaProductos.setText(listaInsumo.toString());
             areaProductos.setEditable(false);
         }
         
         scrollPane.setPreferredSize(new Dimension(400, 300));
         scrollPane.setVisible(true);

         panel3.add(scrollPane);
         
         Bagregar = new JButton("Agregar");
         this.Bagregar.addActionListener(this);
         Beliminar = new JButton("Eliminar");
         this.Beliminar.addActionListener(this);
         Bsalir = new JButton("Salir");
         this.Bsalir.addActionListener(this);
         panel4.add(Bagregar);
         panel4.add(Beliminar);
         panel4.add(Bsalir);
         
         getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
         
         getContentPane().add(panel1);
         getContentPane().add(panel2);
         getContentPane().add(panel3);
         getContentPane().add(panel4); 

         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         setPreferredSize(new Dimension(600, 500));
         pack();
         setLocationRelativeTo(null);
         setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.Bagregar) {
            this.Altas();
        } else if (e.getSource() == this.Beliminar) {
            this.Eliminar();
        } else if (e.getSource() == Bsalir) {
            if (this.Bsalir.getText().compareTo("Cancelar") == 0)
                this.VolveralInicio();
            else
                this.dispose();
        }
	}
	
	public void VolveralInicio() {
        Bagregar.setText("Agregar");
        Bsalir.setText("Salir");
        Beliminar.setEnabled(true);
        Tinsumo.setEditable(false);
        Tid.setEditable(false);
        listaCategoria.setEnabled(false);
        Tid.setText("");
        Tinsumo.setText("");
        listaCategoria.setSelectedIndex(0);
    }

    public void Altas() {
        if (Bagregar.getText().equals("Agregar")) {
            listaCategoria.setSelectedIndex(0);
            Bagregar.setText("Salvar");
            Bsalir.setText("Cancelar");
            Beliminar.setEnabled(false);
            Tid.setEditable(true);
            Tinsumo.setEditable(true);
            listaCategoria.setEnabled(true);
            listaCategoria.setFocusable(true);
        } else {
            if (esdatoscompletos()) {
                String id, insumo, idCategoria;
                id = Tid.getText().trim();
                insumo = Tinsumo.getText().trim();
                idCategoria = modeloCategoria.get(listaCategoria.getSelectedIndex()).getIdcategoria();
                Insumo nodo = new Insumo(id, insumo, idCategoria);

                if (!listaInsumo.agregarInsumo(nodo)) {
                    String mensaje = "Lo siento, el ID " + id + " ya existe y está asignado a " + listaInsumo.buscarInsumo(id);
                    JOptionPane.showMessageDialog(this, mensaje);
                } else {
                    archivoInsumos.guardar(listaInsumo.toArchivo());
                    areaProductos.setText(listaInsumo.toString());
                }
            }
            VolveralInicio();
        }
    }

    public Boolean esdatoscompletos() {
        boolean enc = false;
        String id, insumo, idcategoria;
        id = "";
        insumo = "";
        idcategoria = "";
        id = Tid.getText().trim();
        insumo = Tinsumo.getText().trim();
        if (listaCategoria.getSelectedIndex() >= 0) {
            idcategoria = modeloCategoria.get(listaCategoria.getSelectedIndex()).getIdcategoria();
        }
        if (!id.isEmpty() && !insumo.isEmpty() && !idcategoria.isEmpty()) {
            enc = true;
        }
        return enc;
    }

    public void Eliminar() {
    	String[] opciones = listaInsumo.idinsumos();
        
        if (opciones == null || opciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay insumos disponibles para eliminar.");
            return;
        }
        String id = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Eliminar", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if ((id != null) && (!id.isEmpty())) {
            if (!listaInsumo.eliminarInsumoPorId(id)) {
                JOptionPane.showMessageDialog(this, "No existe este ID");
            } else {
                archivoInsumos.guardar(listaInsumo.toArchivo());
                areaProductos.setText(listaInsumo.toString());
            }
        }
    }
    
	public static void main(String[] args) {
		new Practica02_06_a();
	}
	
}
