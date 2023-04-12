package arquetipoAhor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Clase que nos permite jugar a un sencillo juego del ahorcado a partir de lista predefinida
 * de palabras.
 * 
 */
public class Ahorcado {
		
	//private List <Character> letras;  //Lista de letras indicadas
	private Set <Character> letras;  //Lista de letras indicadas
	Player jugador;
	Palabra palabraJuego; 
	Scanner Sc;
	
	/**
	 * Constructor que inicializa las propiedades de la clase.
	 * La palabra incognita se obtiene dentro de palabraJuego
	 * 
	 */
	public Ahorcado(Scanner ScIN) {
		
		palabraJuego=new Palabra();
		//letras= new ArrayList<Character>();
		letras=new  TreeSet<Character>();
		Sc=ScIN;
				
	}	
		
	
	
	/**
	 * imprimirLetrasLeidas: Método que escribe en consola la lista de letras leidas	 *
	 * @param 
	 * 		null
	 * @return 
	 * 		void
	 * 
	 */	
	private void imprimirLetrasLeidas() {
		String cadena="Letras introducidas: ";
		//int index=0;
		
		/*
		for (index=0; index<letras.size() ; index++) {			
			cadena= cadena + " " + letras.get(index);
		}
		*/
		
		Iterator<Character> ItLetras = this.letras.iterator();
	    while (ItLetras.hasNext()){//mientras halla elementos en el conjuntoL
	       	cadena = cadena + " " + ItLetras.next().toString() ;//imprime elementos
	    }
		
		System.out.println(cadena);	
	}
	
	/**
	 * leerLetra: Método que lee de consola hasta recibir un caracter alfabético
	 * y lo incluye en la lista de letras leidas
	 * @param 
	 * 		Scanner Sc: consola de la que leer el caracter
	 * @return 
	 * 		char. Retorna el caracter leido y lo incluye en la lista de letras leidas
	 * 
	 */
	private char leerLetra() {
		String lectura="";
		boolean letra_valida=false;
		boolean terminar=false;
		char retorno=' ';
		
		while (!letra_valida) {
			System.out.println("Teclee una nueva letra ('9' Menú Principal): ");	
			try {
				lectura= Sc.next(); //OJO... necesitamos una letra
				if (lectura.compareTo("9")==0){
					terminar=true;	
					letra_valida=true;
				}else {
					letra_valida=Character.isLetter(lectura.toUpperCase().charAt(0));
				}
				
			}catch(NoSuchElementException e){
				letra_valida=false;	            
	        }catch(IllegalStateException e){
	        	letra_valida=false;	            
	        }
			
			
		}
		
		if (terminar) {
			retorno='9';
		}else {		
			letras.add(lectura.toUpperCase().charAt(0));
			retorno=lectura.toUpperCase().charAt(0);
		}
		
		return retorno;
		
	}
	
	
	public void iniciarJuego(String nombre) {
		jugador=new Player();
		jugador.setNombre(nombre);	
		palabraJuego=new Palabra();
		jugador.setPalabraJuego(palabraJuego.getMisterio());
		letras.clear();
		jugar();
	}
	
	public void continuarJuego(String nombre) {
		jugador=new Player(nombre);
		if (!jugador.getPalabraJuego().isEmpty()) { //si el jugador no estaba guardado mantenemos la palabra
			palabraJuego=new Palabra(jugador.getPalabraJuego());
		}else {
			jugador.setPalabraJuego(palabraJuego.getMisterio());
		}
			
		letras.clear();
		letras.addAll(jugador.getLetraUtilizadas());
		jugar();	
		
	}
	
	private void imprimirInicio() {		
		System.out.println();
		System.out.println("Comenzamos!");	
		System.out.println();
	}
	
	/**
	 * jugar: Método que dirige el juego. Lee letras, verifica y pinta la horca
	*
	 */
	private void jugar() {
		
		char letraLeida=' ';
		boolean forzarSalida=false;
		
		//Scanner Sc= new Scanner(System.in);
		
		imprimirInicio();		
		
		imprimirHorca();
		
		//Pintamos la palabra
		palabraJuego.EscribirPalabra(letras);	
		if (!letras.isEmpty()){
			imprimirLetrasLeidas();			
		}
		
		//mientras no hayamos encontrado la palabra y menos de 6 errores
		while (palabraJuego.ContinuarJuego(letras)) {	
			
			//Leemos letra
			letraLeida=leerLetra();
			if (letraLeida=='9') {
				forzarSalida=true;
				break;
			}
			
			jugador.incIntentos();
			jugador.incLetra(letraLeida);
			
			//comprobar letra
			if (!palabraJuego.ComprobarLetra(letraLeida)) {
				jugador.incEstado();				
			}									
		
			imprimirHorca();
			
			//Escribimos palabra con letra ya localizadas
			palabraJuego.EscribirPalabra(letras);		
			//imprimimos letras empleada
			imprimirLetrasLeidas();	
			jugador.SaveData();

		}
		
		if (!forzarSalida) { //El juego ha terminado por superar errores o acierto de palabra		
			if (palabraJuego.PalabraEncontrada(letras)) {
				System.out.println("¡Enhorabuena! ¡Has acertado la palabra!");	
			} else {
				System.out.println("Lo siento. No has acertado la palabra");	
				System.out.println("La palabra correcta es: " + palabraJuego.getMisterio());	
			}
			
			jugador.cleanFile(); //limpiamos registro del jugador para proxima partida
		}	
		
		
		System.out.println("");	
		//Sc.close();
	}
	
	private void imprimirHorca() {
		
		String horca=jugador.getEstado().getFigura();
		System.out.println(horca);
		System.out.println();
		
	}
	

}
