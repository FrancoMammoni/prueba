package vistas;

import javax.swing.JFrame;

public class Main {

	public static void main (String[] args) {
		
		JFrame ventana = new JFrame();
		PantallaJuego pantalla = new PantallaJuego();
		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar la pantalla
		ventana.setResizable(false); // Para que no se pueda cambiar el tamanio de la pantalla
		ventana.setTitle("Ventana de Prueba"); // Titulo de la ventana
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		ventana.add(pantalla);
		ventana.pack(); // Hace que el frame se transforme/acomode al tamanio del panel (y de todos los componentes que tenga aniadidos)
		
		pantalla.arrancarHiloJuego();
	}
}
