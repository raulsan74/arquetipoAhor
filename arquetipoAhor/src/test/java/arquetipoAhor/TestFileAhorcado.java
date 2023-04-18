package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uned.master.java.FileAhorcado;
import junit.framework.TestCase;

public class TestFileAhorcado extends TestCase  {
	private FileAhorcado file = new FileAhorcado();
	
	/*
	 * testGetPlayer: Se prueba el metodo getPlayer
	 */
	@Test
	public void testGetPlayer() {
		file.cleanPlayer("PEPITO");  // Se limpia los datos de Pepito
		assertEquals("", file.getPlayer("PEPITO"));  // Comprobamos que getPlayer nos da "" al haber limpiado los datos
		file.savePlayer("PEPITO", "PEPITO;DATO1;DATO2;DATO3"); // Guardamos datos para Pepito
		assertEquals("PEPITO;DATO1;DATO2;DATO3", file.getPlayer("PEPITO"));  // Comprobamos que getPlayer nos devuelve correctamente los datos guardados
	}
	/*
	 * testCleanPlayer: Se prueba el metodo cleanPlayer
	 */
	@Test
	public void testCleanPlayer() {
		file.savePlayer("PEPITO","PEPITO;DATO1;DATO2;DATO3"); // Guardamos datos para Pepito
		file.cleanPlayer("PEPITO");  // Los limpiamos con cleanPlayer
		assertEquals("", file.getPlayer("PEPITO"));  // Copmrobamos que se limpiaron correctamente 
	}
	/*
	 * testSavePlayer: Se prueba el metodo savePlayer
	 */
	@Test
	public void testSavePlayer() {
		file.cleanPlayer("PEPITO");  // Limpiamos los posibles datos que tenga Pepito
		file.savePlayer("PEPITO", "PEPITO;DATO1;DATO2;DATO3");  // Guardamos datos nuevos
		assertEquals("PEPITO;DATO1;DATO2;DATO3", file.getPlayer("PEPITO")); // Los recuperamos para ver si se guardaron correctamente
	}

}
