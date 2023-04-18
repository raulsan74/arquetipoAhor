package arquetipoAhor;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import arquetipoAhor.EstadoAhorcado;
import arquetipoAhor.java.FileAhorcado;
import arquetipoAhor.java.Player;
import junit.framework.TestCase;

public class TestPlayer  extends TestCase {
	Player player = new Player();
	/*
	 * testPlayerGeneralFeatures: Se prueban diferentes características de la clase Player.
	 */
	@Test
	public void testPlayerGeneralFeatures() {
		FileAhorcado file = new FileAhorcado();
		assertEquals(";0;0;;", this.player.toString()); // Comprobamos que el estado inicial se ha creado correctamente
		this.player.setNombre("Pepito");  // Le ponemos un nombre al player
		assertEquals("Pepito;0;0;;", this.player.toString()); // Comprobamos que correctamente tiene el nombre que le hemos puesto
		this.player.setPalabraJuego("GATO");  // Le asignamos la palabra secreta
		assertEquals("Pepito;0;0;GATO;", this.player.toString());  // Copmrobamos que la palabra secreta se ha agregado correctamente
		Set<Character> letrasUtilizadas = new TreeSet<Character>(); // Ahora simularemos 3 fallos al intentar descubrir la palabra
		letrasUtilizadas.add('A');
		this.player.incIntentos();
		this.player.setEstado(EstadoAhorcado.CABEZA);
		letrasUtilizadas.add('B');
		this.player.incIntentos();
		this.player.setEstado(EstadoAhorcado.CUERPO);
		letrasUtilizadas.add('C');
		this.player.incIntentos();
		this.player.setEstado(EstadoAhorcado.BRAZO_DERECHO);
		this.player.setLetraUtilizadas(letrasUtilizadas);
		assertEquals("Pepito;3;3;GATO;A,B,C", this.player.toString()); // Comprobamos que player tiene el estado correcto
		this.player.SaveData();  // Usamos la opción de guardar
		assertEquals("PEPITO;3;3;GATO;A,B,C", file.getPlayer("Pepito")); // Comprobamos que se ha guardado correctamente
	}

}
