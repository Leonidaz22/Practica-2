package Parte2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Parte1.Practica02_03_a;

public class Practica02_04 extends JFrame implements ActionListener {
	
	private JFrame VentanaPrincipal;
    private JMenuBar BarraMenu;
    private JMenu MOperacion, MConfiguracion, MSalir;
    private JMenuItem SMsalida, SMcategorias, SMinsumos;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Practica02_04 window = new Practica02_04();
					window.VentanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Practica02_04() {
		VentanaPrincipal = new JFrame();
        VentanaPrincipal.setBounds(100, 100, 622, 395);
        VentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BarraMenu = new JMenuBar();
        VentanaPrincipal.setJMenuBar(BarraMenu);

        MOperacion = new JMenu("Operacion");
        BarraMenu.add(MOperacion);

        MConfiguracion = new JMenu("Configuracion");
        BarraMenu.add(MConfiguracion);

        MSalir = new JMenu("Salir");
        BarraMenu.add(MSalir);

        SMcategorias = new JMenuItem("Categorias");
        MConfiguracion.add(SMcategorias);

        SMinsumos = new JMenuItem("Insumos");
        MConfiguracion.add(SMinsumos);

        SMsalida = new JMenuItem("Salida");
        MSalir.add(SMsalida);

        this.SMcategorias.addActionListener(this);
        this.SMinsumos.addActionListener(this);
        this.SMsalida.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.SMsalida)
            this.VentanaPrincipal.dispose();
        else if (e.getSource() == this.SMcategorias)
            JOptionPane.showMessageDialog(this.VentanaPrincipal, "Llamando a Conceptos");
        else if (e.getSource() == this.SMinsumos) {
        	Practica02_03_a hijo = new Practica02_03_a();
            hijo.setVisible(true);
        }
	}

}
