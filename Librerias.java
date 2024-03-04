package Libreria;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;

public class Librerias {
	
	public Icon EtiquetaImagen(String archivoimagen, int x, int y)
	{
		String ruta = archivoimagen;
		ImageIcon imagen = new ImageIcon(ruta);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT));
		return icono;
	}
	
	public JMenuBar menusPadre(JMenuBar barra, boolean accion) {
	    JMenuBar barraAux = barra;
	    int cantMenus = barraAux.getMenuCount();

	    for (int pos = 0; pos < cantMenus; pos++) {
	        barraAux.getMenu(pos).setEnabled(accion);
	    }

	    return barraAux;
	}
	
}
