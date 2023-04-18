package junit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import es.uned.master.java.Ahorcado;
import es.uned.master.java.FileAhorcado;
import es.uned.master.java.Player;
import junit.framework.TestCase;

public class TestAhorcado extends TestCase {
	
	/*
	 * testIniciarJuego: Probamos una iteración del juego desde el inicio
	 * 
	 * Como no tenemos entrada desde la consola, la introduciremos a partir de un fichero. Usaremos una letra
	 * que sabemos que no está contenida en ninguna de las posibles palabras, y provocamos luego una salida al menu
	 * introduciendo un 9. Así pues tras la primera iteración sabemos que el estado será el de haber fallado adivinar una letra.
	 */
	@Test
	public void testIniciarJuego() {
		File file = new File("./src/data/testIniciarJuego"); // Leemos la entrada a través de un fichero
		try {
			Scanner scan = new Scanner(file);
			Ahorcado ahorcado = new Ahorcado(scan);
			ahorcado.iniciarJuego("Juanito");  // Se inicia el juego
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Player player = new Player("Juanito");
		player.incEstado();
		String palabraAleatoria = player.getPalabraJuego(); //Obtenemos la palabra aleatoria que es imposible de conocerla a priori
		FileAhorcado fileAhorcado = new FileAhorcado();
		assertEquals("JUANITO;1;1;"+palabraAleatoria+";Z",fileAhorcado.getPlayer("Juanito")); // Se comprueba el estado previsto tras una iteración
	}
	
	/*
	 * testContinuarJuego: Probamos una iteración continuando un juego ya iniciado anteriormente
	 * 
	 * Hacemos otra vez lo mismo pero recuperando el juego ya iniciado, Con lo que obtendremos un estado final de
	 * doble fallo al intentar acertar una letra.
	 */
	@Test
	public void testContinuarJuego() {
		File file = new File("./src/data/testContinuarJuego"); // Leemos la entrada a través de un fichero
		try {
			Scanner scan = new Scanner(file);
			Ahorcado ahorcado = new Ahorcado(scan);
			ahorcado.continuarJuego("Juanito"); // Se continuamos el juego
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Player player = new Player("Juanito");
		player.incEstado();
		String palabraAleatoria = player.getPalabraJuego(); //Obtenemos la palabra aleatoria que es imposible de conocerla a priori
		FileAhorcado fileAhorcado = new FileAhorcado();
		assertEquals("JUANITO;2;2;"+palabraAleatoria+";W,Z",fileAhorcado.getPlayer("Juanito")); // Se comprueba el estado previsto tras continuar el juego con otra iteración
	}

}
