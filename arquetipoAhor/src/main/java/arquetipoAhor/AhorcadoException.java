package arquetipoAhor;

public class AhorcadoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AhorcadoException(String mensaje) {
        super("Ahorcado Exception:" + mensaje);
    }
    public AhorcadoException() {
    	super("Ahorcado Exception...");
    }
}