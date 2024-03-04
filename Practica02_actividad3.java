package Parte2;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Libreria.Librerias;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Practica02_actividad3 extends JInternalFrame implements ActionListener {
	
	Librerias libreria;
    private JTextField nombreObraField, descripcionObraField;
    private JButton Bobra;
    private int idContador = 1;
    private JPanel panelFormulario;
    private JLabel labelNombreObra, labelDescripcionObra;
    private JFrame abuelo2;
    private JMenuBar barra;
	
	public Practica02_actividad3() {
		super("Crear Obra", true, true, true, true);
        setBounds(100, 100, 400, 200);
        
        this.libreria = new Librerias();
        this.abuelo2 = abuelo2;
        this.barra = this.abuelo2.getJMenuBar();
        this.barra = this.libreria.menusPadre(this.barra, false);

        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario);

        labelNombreObra = new JLabel("Nombre de la obra:");
        labelNombreObra.setBounds(10, 20, 120, 20);
        panelFormulario.add(labelNombreObra);

        nombreObraField = new JTextField();
        nombreObraField.setBounds(140, 20, 200, 20);
        panelFormulario.add(nombreObraField);

        labelDescripcionObra = new JLabel("Descripción de la obra:");
        labelDescripcionObra.setBounds(10, 60, 120, 20);
        panelFormulario.add(labelDescripcionObra);

        descripcionObraField = new JTextField();
        descripcionObraField.setBounds(140, 60, 200, 20);
        panelFormulario.add(descripcionObraField);

        Bobra = new JButton("Crear Obra");
        Bobra.setBounds(140, 100, 120, 30);
        Bobra.addActionListener(this);
        panelFormulario.add(Bobra);
	}
	
	private void crearObra() {
        String nombreObra = nombreObraField.getText().trim();
        String descripcionObra = descripcionObraField.getText().trim();

        if (!nombreObra.isEmpty() && !descripcionObra.isEmpty()) {
            String idObra = generarIdAutomatico();
            guardarObraEnArchivo(idObra, nombreObra, descripcionObra);

            nombreObraField.setText("");
            descripcionObraField.setText("");
        }
    }
    private String generarIdAutomatico() {
        String nuevoId = "OBRA-" + idContador;
        idContador++;
        return nuevoId;
    }

    private void guardarObraEnArchivo(String idObra, String nombreObra, String descripcionObra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Obras.txt", true))) {
            writer.write(idObra + "|" + nombreObra + "|" + descripcionObra);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Bobra) {
            crearObra();
        }
	}

}
