package arquetipoAhor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;





public class FileAhorcado {
	private File archivoR;
	private File archivoW;
	private FileReader fr;
	private BufferedReader bfr;
	private FileWriter fw;
	private BufferedWriter bfw;
		
	final String carpeta_data="./src/data";
	final String file="./src/data/ahorcado.muj"; //Fichero fijo donde almacenar estado de jugadores
	final String fileaux="./src/data/ahorcado.aux"; //Fichero temporal para actualizar/borrar jugad
	/**
	 * 
	 * @exception AhorcadoException
	 */
	public FileAhorcado() {
		//Constructor que comprueba el acceso al fichero. Solo es verificación.
		//Crea las carpetas necesarias si no existen.
		try {		
						
	        File directorio = new File(carpeta_data);
	        if (!directorio.exists()) {
	        	directorio.mkdirs();	            
	        }
			
	        File fileData = new File(file);
	        if (!fileData.exists()) {
	        	fileData.createNewFile()	;            
	        }
	        
	        File fileAux = new File(fileaux);
	        if (!fileAux.exists()) {
	        	fileAux.createNewFile()	;            
	        }
	        	        	        
			abrirLectura();
			
		}catch (Exception ex){
			throw new AhorcadoException("Error en la verificación de ficheros de datos" );	
		}finally{
			try{
				cerrarLectura();
				
			}catch(Exception ex){}
		}
		
	}
	
		
	
	//Devuelve el registro de datos existentes del jugador o un player vacio si no los encuentra.
	 /**
     * The getPlayer function takes a String as an argument and returns the player's information from the file.
     * 
     *
     * @param nombre Compare it with the name in the line of the file
     *
     * @return A string with the player's information. Empty string if player does not exist
     *   
     *  @exception AhorcadoException
     */
	public String getPlayer(String nombre) {
	String lineaFile="";
	String lineaRetorno="";
	boolean playerEncontrado=false;
	
		
		abrirLectura();		
		nombre=nombre.toUpperCase();
				
		try {
			while ((lineaFile = this.bfr.readLine()) != null  && !playerEncontrado){
				
				if (mismoNombre(nombre, lineaFile)) {						
					lineaRetorno=lineaFile;
					playerEncontrado=true;
				
				}
			}
		} catch (IOException e) {			
			throw new AhorcadoException("Error en la lectura del fichero de datos: " + file);	
		}catch (AhorcadoException ex){
			throw new AhorcadoException(ex.getMessage());			
		}finally{		
		
			cerrarLectura();
		}
		
		return lineaRetorno.toUpperCase();
	}
	
	
	 /**
     * The mismoNombre function compares the name of a player with the name in a line of the file.
     * 
     *
     * @param nombre Compare with the name in the file
     * @param String Compare the name of the file with a line in the file
     *
     * @return True if the name is equal to the name in the file     
     */
	
	private boolean mismoNombre(String nombre, String lineaFile) {
		boolean retorno=false;
		String nombreFile="";
		
		 StringTokenizer st = new StringTokenizer(lineaFile,";");
	       if (st.hasMoreTokens()){ //El nombre está en la posición 1
	        	nombreFile=st.nextToken();
	        }
	        
	        if (nombre.compareToIgnoreCase(nombreFile)==0) {
	        	retorno=true;
	        } else {
	        	retorno=false;
	        }
		
		
		return retorno;
	}
	
	
	 /**
     * The cerrarLectura function closes the BufferedReader and FileReader objects.
     *    
     * @exception AhorcadoException
     */	
	
private void cerrarLectura() {
	try{
		if (this.bfr != null){
			this.bfr.close();
		}
		if (this.fr != null){
			this.fr.close();
			}
	}catch(Exception ex){
		throw new AhorcadoException("Error cerrando el fichero de datos: " + file);	
	}
	
}


/**
 * The cerrarEscritura function closes the file writer and buffer writer.
 *
 * @exception AhorcadoException
 */

private void cerrarEscritura() {

	try{
		if (this.bfw != null){
			this.bfw.close();
		}
		if (this.fw != null){
			this.fw.close();
			}
	}catch(Exception ex){
		throw new AhorcadoException("Error cerrando el fichero de datos: " + fileaux);	
	}
	
}

/**
 * The abrirLectura function opens the file for reading.
 * 
 *  @exception AhorcadoException
 */

private void abrirLectura() {
	boolean error=false;	
	try {	
		this.archivoR = new File(file);
		this.fr = new FileReader(this.archivoR);
		this.bfr = new BufferedReader(this.fr);
		
	}catch (FileNotFoundException ex){
		error=true;
		throw new AhorcadoException("Fichero no encontrado: " + file);	
	}catch (NullPointerException ex){
		error=true;
		throw new AhorcadoException("Fichero de lectura nulo");	
	}finally{
		try{
			if (error) {
				cerrarLectura();
			}
			
		}catch(Exception ex){}
	}
		
}


/**
 * The abrirEscritura function opens the file for writing.
 * 
 *  @exception AhorcadoException
 */

private void abrirEscritura() {
	boolean error=false;	
	try {	
		this.archivoW = new File(fileaux);
		this.fw = new FileWriter(this.archivoW);
		this.bfw = new BufferedWriter(this.fw);
		
	}catch (FileNotFoundException ex){
		error=true;
		throw new AhorcadoException("Fichero no encontrado: " + fileaux);	
	}catch (IOException ex){
		error=true;
		throw new AhorcadoException("Fichero existe pero no es accesible: " + fileaux);	
	}finally{
		try{
			
			if (error) {
				cerrarEscritura();
			}
			
		}catch(Exception ex){}
	}
		
}


/**
 * The renombrarFicheros function renames the file that contains the player data.
 * It is used to rename a temporary file with the name of a previous one, so that it can be overwritten.
 * 
 */

private void renombrarFicheros() {
	
	 File filePartida = new File(file); 
	    if (filePartida.delete()) { //borramos fichero original muj
	    	  File fileChange = new File(fileaux);
	    	  
	          // Renombramos el fichero aux a muj	          
	          File rename = new File(file);
	    
	         fileChange.renameTo(rename);	    
	        
	    } 
}


/**
 * The escribirLineaPlayer function writes a line (UpperCase) of text and newline to the file.
 * 
 *
 * @param lineaPlayer Write the player's name in the file
 * 
 * @exception AhorcadoException
 */
private void escribirLineaPlayer(String lineaPlayer) {
	try {	
		lineaPlayer=lineaPlayer.toUpperCase();
		this.bfw.write(lineaPlayer);
		this.bfw.newLine();
	}catch (IOException ex){
		throw new AhorcadoException("Error escribiendo el fichero de datos: " + fileaux);	
	}catch (Exception ex){
		throw new AhorcadoException("Error general escribiendo el fichero de datos: " + fileaux);
	}
	
}
	



public void cleanPlayer(String nombre) {
		
		String lineaFile="";		
		
		try {	
			
			abrirLectura();			
			abrirEscritura();			
			
			while ((lineaFile = this.bfr.readLine()) != null){
				
				if (!mismoNombre(nombre, lineaFile)) {
					escribirLineaPlayer(lineaFile);					
				}				
			}		
			
		}catch (AhorcadoException ex){
			throw new AhorcadoException(ex.getMessage());			
		}catch (IOException ex){
			throw new AhorcadoException("Error leyendo el fichero de datos: " + file);
			
		}
		finally{
			try{				
				cerrarLectura();				
				cerrarEscritura();
				
			    renombrarFicheros();
			
				
			}catch(AhorcadoException ex){
				throw new AhorcadoException(ex.getMessage());
			}
		}

}







/**
 * The savePlayer function saves the player's information to a file.
 * Build an aux file with the lines of the original data file.
 * If it finds a line with the same name (same player) it replaces it with the new line.
 * If it doesn't find the line, it adds it to the end of the file.
 *
 * Finally rename the aux file to muj file
 * 
 * @param nombre Key Compare with the name of the player in lineafile
 * @param String Get the name of the player to be deleted
 *
 *@exception AhorcadoException
 */


public void savePlayer(String nombre, String lineaPlayer) {
		boolean saveOK=false;
		boolean playerEncontrado=false;
		String lineaFile="";		
		
		try {	
			
			abrirLectura();			
			abrirEscritura();			
			
			while ((lineaFile = this.bfr.readLine()) != null){
				
				if (mismoNombre(nombre, lineaFile)) {
					
					escribirLineaPlayer(lineaPlayer);				
					playerEncontrado=true;
					
				}else {		
					escribirLineaPlayer(lineaFile);
					
				}				
			}
			
			if (!playerEncontrado) { //Si no existia el player en el fichero
				escribirLineaPlayer(lineaPlayer);				
			}
			
			saveOK=true;
			
		}catch (AhorcadoException ex){
			throw new AhorcadoException(ex.getMessage());			
		}catch (IOException ex){
			throw new AhorcadoException("Error leyendo el fichero de datos: " + file);
			
		}
		finally{
			try{				
				cerrarLectura();				
				cerrarEscritura();
				
				if (saveOK) {
					renombrarFicheros();
				}
				
			}catch(AhorcadoException ex){
				throw new AhorcadoException(ex.getMessage());
			}
		}		
		
	}
	

}

