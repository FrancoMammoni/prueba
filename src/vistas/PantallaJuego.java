package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PantallaJuego extends JPanel implements Runnable{
	
	protected Image fondo, marito, luisito;
	
	
	// Configuraciones de la pantalla:
	int tamanioOriginalBaldoza = 16; // Tamanio default de los elementos (jugadores y bichos) 16x16 pixeles
	
	// Como las computadoras de hoy en dia tienen resoluciones mayores, los elementos que sean
	// 16x16 se van a ver recontra chicos, por lo que hay q escalar a los pjs.
	int escala = 3; // Los bichos de 16x16 se van a ver en 48x48. Es la mas comun en juegos retros modernos
	
	int tamanioBaldoza = tamanioOriginalBaldoza * escala;
	
	// En base a el tamanio de las baldozas, se decide el tamanio de la pantalla.
	
	int maxScreenCol = 16; // 16 baldozas de alto.
	int maxScreenRow = 12; // 12 baldozas de alto.
	int anchoPantalla = tamanioBaldoza * maxScreenRow;
	int altoPantalla = tamanioBaldoza * maxScreenCol;
	
	// Con estos parametros la pantalla queda como 768*576
	lectorInput lectorInput = new lectorInput();
	
	// Posicion Default del pj, la posicion X:0 Y:0 es en la esquina izq del panel
	int jugadorPosX = 100;
	int jugadorPosY = 450;
	int jugadorPosX2 = 200;
	int jugadorPosY2 = 450;
	int velocidadJugador = 5; // Cada vez q se mueva se mueve 5 pixeles hacia la direccion que se mueve
	
	// Efepeeses (FPS)
	int fps = 30;
	
	public PantallaJuego() {
		try {
            fondo = ImageIO.read(new File("recursos/imagenFondo.jpg")); // Asegúrate de que la ruta es correcta
            marito = ImageIO.read(new File("recursos/marito.png"));
            luisito = ImageIO.read(new File("recursos/luisito.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
		this.setPreferredSize(new Dimension(altoPantalla, anchoPantalla));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // Para que no se creen dibujos en la pantalla, y no se tosqueen con las sprites nuestras
		// entonces se mejora el "renderizado" del jogo
		this.addKeyListener(lectorInput); 
		this.setFocusable(true); // Se hace "focus" al game panel al momento de presionar una tecla estando en el frame
	}
	
	
	
	Thread hiloJuego;
	public void arrancarHiloJuego() {
		hiloJuego = new Thread(this); // El panel de juego se pasa como parametro
		hiloJuego.start(); // Llama al run automaticamente
	}

	
	
	
	// El run arranca el hilo
	// En el run, se crea el Loop de juego, q es de lo mas importante en el juego, es decir los ticks (esto capaz iria en la clase Timer).
	@Override
	public void run() { // De la interfaz runnable
		//long tiempoActual = System.currentTimeMillis(); // mil milisegundos = 1 seg (tambien podemos usar nanosegundos, 1millon = 1seg)
		
		double intervaloTick = 1000 / fps; // 1 segundo / cant fps; eto es lo que duerme el hilo
		double siguienteIntervaloTick = System.currentTimeMillis() + intervaloTick;
		
		while(hiloJuego != null) { // Mientras q el hilo existe, se repite el loop
		/*
		 * PASOS: 
		 * 1 UPDATE
		 * 2 reDIBUJAR
		 * Es lo de actualizar() q tenemo en timer, si se setea a 30fps, el juego hace esto 30 veces por segundo.
		 * esto usaria los metodos de actualizar() y dibujarComponentes() (PODEMOS USAR EL paintComponent() DE JAVA JPANEL)
		 * hay q frenar los ticks, pq van muy rapido
		*/
	
			actualizar();
			repaint(); // tmbn de jpanel, es como q llama a paintComponent.
					
			try {
				double milisegundosRestantes = siguienteIntervaloTick - System.currentTimeMillis(); // aca es el teimpo en el q se duerme (ver captura chino)
				if(milisegundosRestantes < 0) {
					milisegundosRestantes = 0;
				}
				Thread.sleep((long) milisegundosRestantes);
				
				siguienteIntervaloTick += intervaloTick;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public void actualizar() {
		// cambiamo la posicion del pj;
		
		if(this.lectorInput.arriba && this.lectorInput.derecha) {
			this.jugadorPosX += this.velocidadJugador;
			this.jugadorPosY -= this.velocidadJugador;
			
		} else if(this.lectorInput.arriba && this.lectorInput.izquierda) {
			this.jugadorPosX -= this.velocidadJugador;
			this.jugadorPosY -= this.velocidadJugador;
			
		} else if(this.lectorInput.abajo && this.lectorInput.izquierda) {
			this.jugadorPosX -= this.velocidadJugador;
			this.jugadorPosY += this.velocidadJugador;
			
		} else if(this.lectorInput.abajo && this.lectorInput.derecha) {
			this.jugadorPosX += this.velocidadJugador;
			this.jugadorPosY += this.velocidadJugador;
			
		} else if(this.lectorInput.arriba == true) {
			this.jugadorPosY -= this.velocidadJugador;
			
		} else if(this.lectorInput.abajo) {
			this.jugadorPosY += this.velocidadJugador;
			
		} else if(this.lectorInput.izquierda) {
			this.jugadorPosX -= this.velocidadJugador;
			
		} else if(this.lectorInput.derecha) {
			this.jugadorPosX += this.velocidadJugador;
		}
		
		
		
		if(this.lectorInput.arriba2 && this.lectorInput.derecha2) {
			this.jugadorPosX2 += this.velocidadJugador;
			this.jugadorPosY2 -= this.velocidadJugador;
			
		} else if(this.lectorInput.arriba2 && this.lectorInput.izquierda2) {
			this.jugadorPosX2 -= this.velocidadJugador;
			this.jugadorPosY2 -= this.velocidadJugador;
			
		} else if(this.lectorInput.abajo2 && this.lectorInput.izquierda2) {
			this.jugadorPosX2 -= this.velocidadJugador;
			this.jugadorPosY2 += this.velocidadJugador;
			
		} else if(this.lectorInput.abajo2 && this.lectorInput.derecha2) {
			this.jugadorPosX2 += this.velocidadJugador;
			this.jugadorPosY2 += this.velocidadJugador;
			
		} else if(this.lectorInput.arriba2 == true) {
			this.jugadorPosY2 -= this.velocidadJugador;
			
		} else if(this.lectorInput.abajo2) {
			this.jugadorPosY2 += this.velocidadJugador;
			
		} else if(this.lectorInput.izquierda2) {
			this.jugadorPosX2 -= this.velocidadJugador;
			
		} else if(this.lectorInput.derecha2) {
			this.jugadorPosX2 += this.velocidadJugador;
		}
	}
	
	public void paintComponent(Graphics grafico) { // La clase graphic es como nuestro "pincel"
		super.paintComponent(grafico); 			   // Pq PantallaJuego es hija de JPanel
		
		Graphics2D grafico2d = (Graphics2D) grafico; // Las clases son similares, pero la 2d es mejor para lo q estamos haciendo, tiene mas funciones
		if(fondo != null) {
			grafico2d.drawImage(fondo, 0, 0, altoPantalla, anchoPantalla, this);
		}
		if(marito != null) {
			grafico2d.drawImage(marito, this.jugadorPosX, this.jugadorPosY, this.tamanioBaldoza, this.tamanioBaldoza, this);
			grafico2d.drawImage(luisito, this.jugadorPosX2, this.jugadorPosY2, this.tamanioBaldoza - 20, this.tamanioBaldoza - 12, this);
		}
		grafico2d.dispose(); // mejora el uso de memoria, es como q borra buffer
	}
	
	
}
