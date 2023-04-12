package arquetipoAhor;

import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

//import es.uned.master.java.juegoAhorcado.base.AhorcadoException;
//import es.uned.master.java.juegoAhorcado.base.FileAhorcado;

public class Player {
	
	private String nombre; //Nombre del jugador
	private EstadoAhorcado estado;  //estado actual del juego
	private int intentos; //intentos realizados
	private String palabraJuego; //palabra del juego
	private Set<Character> letrasUtilizadas;  //letras utilizadas
	
	final String token=";" ;	//utilizado como separador de campos en el string a almacenar en fichero
	final int MAX_ERRORES=6;   //numero máximo de errores
	
	/** Construye un player vacio
	 * 
	 */
	public Player() {
		this.nombre ="";
		this.estado=EstadoAhorcado.INICIAL;
		this.intentos=0;
		this.palabraJuego="";
		this.letrasUtilizadas=new TreeSet<Character>();
	}
	
	/** Construye un player a partir del nombre 
	 * @param nombre: Nombre del jugador del cual obtener los datos de partida
	 */
	public Player(String nombre) {	
		this.nombre = nombre;
		this.letrasUtilizadas=new TreeSet<Character>();
		getData(); 
		
	}
	
	
	  /**
     * The obtenerEstadoAhorcado function takes a String value and returns an EstadoAhorcado enum.
     * 
     *
     * @param valor Get the value of the state from a file
     *
     * @return The estadoahorcado enum value     
     */
	
	private EstadoAhorcado obtenerEstadoAhorcado(String valor) {
		  
		EstadoAhorcado estadoRetorno=EstadoAhorcado.INICIAL;
		 int estadoFile = Integer.parseInt(valor);
		 
		 for (EstadoAhorcado estado : EstadoAhorcado.values()) { 
			   if (estadoFile==estado.getEstado()) {
				   estadoRetorno=estado;
			   }
			}
		  
		return estadoRetorno;		
	}
	
	
	/**
     * The obtenerLetras function takes a string of letters and returns a set of characters.
     * 
     *
     * @param letras Store the letters that are in the file
     *
     * @return A set<character>; object     
     */
	private Set<Character> obtenerLetras(String letras){
		Set<Character> letrasFile=new TreeSet<Character>();
		
		 StringTokenizer st = new StringTokenizer(letras,",");
	      while (st.hasMoreTokens()){ 
	    	  Character ch=st.nextToken().charAt(0);
	    	  letrasFile.add(ch);	   	  
	      }
	      
	      return letrasFile;
		
	}
	
	
	 /**
     * The obtenerData function takes a string as an argument and parses it into the
     * appropriate data fields for the Player object.
     * 
     *
     * @param stringPlayer Get the data from the file
     *    
     * @docauthor Trelent
     */
	
	private void obtenerData(String stringPlayer) {
		int posicion=0;
		 StringTokenizer st = new StringTokenizer(stringPlayer,";");
	      while (st.hasMoreTokens()){ 
	    	 posicion++;    	  
	    	  switch (posicion) { 
	    	    case 1: 
	    	    	this.setNombre(st.nextToken());
	    	     break;
	    	    case 2:
	    	    	this.setEstado(obtenerEstadoAhorcado(st.nextToken()));
	    	     break;
	    	    case 3:
	    	    	  this.setIntentos(Integer.parseInt(st.nextToken()));
		    	  break;
	    	    case 4:
		    	    this.setPalabraJuego(st.nextToken());
		    	     break;
	    	    case 5:
		    	    this.setLetraUtilizadas(obtenerLetras(st.nextToken()));
		    	 break;	    	   
	    	  }
	        }
		
		
	}
	
	
	 /**
     * The getData function is used to retrieve the data from a file.
     * 
     *     
     */
	private void getData() {
		// TODO Auto-generated method stub
		FileAhorcado file=new FileAhorcado();		
		String stringPlayer=file.getPlayer(this.nombre);
		
		if (stringPlayer.isEmpty()) {
			this.estado=EstadoAhorcado.INICIAL;
			this.intentos=0;
			this.palabraJuego="";
			this.letrasUtilizadas=new TreeSet<Character>();
			
		}else {		
			obtenerData(stringPlayer);
		}	
		
	}
	
	/**
     * The SaveData function saves the player's values to a file.
     *     
     */
	public void SaveData() {
		FileAhorcado file=new FileAhorcado();
		try {
			file.savePlayer(this.getNombre(), this.toString());	
		}catch (AhorcadoException ex){
			throw new AhorcadoException(ex.getMessage());			
		}
	}

	
	  /**
     * The setLetraUtilizadas function is used to set the letters that have been used by player.
     * 
     *
     * @param letras Set<Character>
     *
     * 
     */
	public void setLetraUtilizadas(Set<Character> letras) {
		this.letrasUtilizadas.addAll(letras);
		
	}
	
	public Set<Character> getLetraUtilizadas() {
		return letrasUtilizadas;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the estado
	 */
	public EstadoAhorcado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoAhorcado estado) {
		this.estado = estado;
	}
	/**
	 * @return the intentos
	 */
	public int getIntentos() {
		return intentos;
	}
	/**
	 * @param intentos the intentos to set
	 */
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	/**
	 * @return the palabraJuego
	 */
	public String getPalabraJuego() {
		return palabraJuego;
	}
	/**
	 * @param palabraJuego the palabraJuego to set
	 */
	public void setPalabraJuego(String palabraJuego) {
		this.palabraJuego = palabraJuego;
	}
	
	
	 /**
     * The getStringLetrasUtilizadas function returns a string with the letters used by player.
     *
     * @return A string with the letters used by player     
     */
	private String getStringLetrasUtilizadas() {
		String letras="";
		
		//obtenemos un string con las letras utilizadas
		  Iterator<Character> ItLetras = this.letrasUtilizadas.iterator();
	        while (ItLetras.hasNext()){//mientras halla elementos en el conjuntoL
	        	letras += ItLetras.next().toString() + ",";//imprime elementos
	        }
	        
	        if (!letras.isEmpty()){ //eliminamos la última coma
	        	letras=letras.substring(0, letras.length()-1);
	        }
	        
		return letras;
		
	}
	
	 /**
     * The toString function returns a string representation of the Player.
     * This string is used by FileAhorcado class to save the player to file     
     * @return A string that represents the state of the Player
     *     
     */
	
	public String toString() {		
		String linea="";	
	
		//Construimos un string con los datos del jugador:
		//nombre;estado;intentos;palabra_juego;letras_utilizadas
		linea=this.getNombre()+token+
				this.getEstado().getEstado()+token+
				this.getIntentos()+token+
				this.getPalabraJuego()+token+
				this.getStringLetrasUtilizadas();
	
	        
	        
	   return linea;
	}
	
	
	 /**
     * The incLetra function adds letra in set letrasUtilizadas.
     *
     */
	public void incLetra(char letra) {
	
		this.letrasUtilizadas.add(letra);
					
	}
	
	
	
	 /**
     * The incIntentos function increments the intentos variable by 1.
     *    
     */
	public void incIntentos() {
		this.intentos++;
	}
	
	
	 /**
     * The incEstado function increments the state of the player by one.
     * If the new state is greater than 6, then it sets it to 6.
     *
     *    
     */
	public void incEstado() {
		
		int newState=this.estado.getEstado()+1;
		
		if (newState>MAX_ERRORES) { //el estado no puede ser mayor de 6
			newState=MAX_ERRORES;
		}
		this.setEstado(obtenerEstadoAhorcado(Integer.toString(newState)));
		
	}
	
	
	 /**
     * The getNumErrores function returns the number of errors that have been detected in the program.
     * (valor del estado)
     *
     * @return The number of errors that have occurred in the system
     *
     */
	public int getNumErrores() {
		
		return this.getEstado().getEstado();
		
	}
	
	public void cleanFile() {
		FileAhorcado file=new FileAhorcado();
		try {
			file.cleanPlayer(this.getNombre());	
		}catch (AhorcadoException ex){
			throw new AhorcadoException(ex.getMessage());			
		}
		
	}

}

