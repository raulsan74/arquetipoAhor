package arquetipoAhor;

import java.util.Scanner;



public class Menu {
	
	
	private String obtenerNombreJugador(Scanner Sc) {
		final String DANIEL="Daniel";
		final String DINORA="Dinora";
		final String EZEQUIEL="Ezequiel";
		final String RAUL="Raul";
		final String SOFIA="Sofia";
		
		String retorno="";
		int option=0;
		System.out.println("");
		System.out.println("Seleccione Jugador\n"+         
				"1. " + DANIEL + "\n"
				+"2. "+ DINORA + "\n"
				+"3. " + EZEQUIEL + "\n"
				+"4. "+  RAUL + "\n"     
				+"5. " + SOFIA ); 
		
		option=Sc.nextInt();
		
		switch(option){
		case 1:                 
         retorno=DANIEL.toUpperCase();            
             break;                
         case 2:
        	 retorno=DINORA.toUpperCase();
          break;               
         case 3:                
        	 retorno=EZEQUIEL.toUpperCase();  
         case 4:
        	 retorno=RAUL.toUpperCase();                 
         break;
         case 5:
        	 retorno=SOFIA.toUpperCase();                 
         break;
         
         }
		
		return retorno;
	}
		
	public void Inicio(){
			int option=0;
			String nombre="";
			Ahorcado juego;
			
	Scanner Sc= new Scanner(System.in);
			
	do {	
	
		
		System.out.println("¡Bienvenido a la partida !\n"+         
				"1. Iniciar partida\n"
				+"2. Continuar partida\n"		
				+"3. Salir\n"     
				+"¿Que deseas hacer?"); 
		System.out.println("Selecciona una opción");
		
		option=Sc.nextInt();
		
      
		switch(option){
		case 1:   
			nombre=obtenerNombreJugador(Sc);
            juego=new Ahorcado(Sc);
	        juego.iniciarJuego(nombre); 
            
             break;                
         case 2:
        	 nombre=obtenerNombreJugador(Sc);
        	 juego=new Ahorcado(Sc);
        	 juego.continuarJuego(nombre);          	              
           
          break;
         case 3:
        	 System.out.println("Partida Finalizada");                 
         break;
         
         }
     
	} while(option!=3);
     
	Sc.close();
	
    }

	}


