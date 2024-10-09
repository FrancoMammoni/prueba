package vistas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class lectorInput implements KeyListener {

	boolean arriba, abajo, izquierda, derecha;
	boolean arriba2, abajo2, izquierda2, derecha2;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Mucho que no se usa en nuestro contexto
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int idTecla = e.getKeyCode();
		
		if(idTecla == KeyEvent.VK_W) { // Si la tecla presionada es W...
			this.arriba = true;
		}
		
		if(idTecla == KeyEvent.VK_A) { 
			this.izquierda = true;
		}
		
		if(idTecla == KeyEvent.VK_S) { 
			this.abajo = true;
		}
		
		if(idTecla == KeyEvent.VK_D) {
			this.derecha = true;
		}
		
		
		if(idTecla == KeyEvent.VK_I) { // Si la tecla presionada es W...
			this.arriba2 = true;
		}
		
		if(idTecla == KeyEvent.VK_J) { 
			this.izquierda2 = true;
		}
		
		if(idTecla == KeyEvent.VK_K) { 
			this.abajo2 = true;
		}
		
		if(idTecla == KeyEvent.VK_L) {
			this.derecha2 = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int idTecla = e.getKeyCode();
		if(idTecla == KeyEvent.VK_W) { // Si la tecla que se larga es W...
			this.arriba = false;
		}
		
		if(idTecla == KeyEvent.VK_A) { 
			this.izquierda = false;
		}
		
		if(idTecla == KeyEvent.VK_S) { 
			this.abajo = false;
		}
		
		if(idTecla == KeyEvent.VK_D) {
			this.derecha = false;
		}
		
		if(idTecla == KeyEvent.VK_I) { // Si la tecla presionada es W...
			this.arriba2 = false;
		}
		
		if(idTecla == KeyEvent.VK_J) { 
			this.izquierda2 = false;
		}
		
		if(idTecla == KeyEvent.VK_K) { 
			this.abajo2 = false;
		}
		
		if(idTecla == KeyEvent.VK_L) {
			this.derecha2 = false;
		}
	}
	
}
