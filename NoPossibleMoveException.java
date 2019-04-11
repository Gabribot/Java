
public class NoPossibleMoveException extends Exception{
	   public NoPossibleMoveException() {
		   super("DIREZIONE NON CONSENTITA!");
	    }

	    public NoPossibleMoveException(String message) {
	        super(message);
	    }
}
