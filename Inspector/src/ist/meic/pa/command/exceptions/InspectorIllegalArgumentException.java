package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the user tries to invoke any
 * inspector passing an illegal or inappropriate argument
 */
public class InspectorIllegalArgumentException extends CommandException {

    private static final long serialVersionUID = 1L;
    private String command;
    
    public InspectorIllegalArgumentException(String command) {
	this.command = command;
    }
    public String getMessage(){
	return command + ": method has been passed an illegal or"
		+ " inappropriate argument";
    }

}
