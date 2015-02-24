package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the currently executing method does not have access
 * to the definition of the specified class, field, method
 * or constructor.
 */
public class InspectorIllegalAccessException extends CommandException {

    private static final long serialVersionUID = 1L;
    private String command;

    public InspectorIllegalAccessException(String command) {
	this.command = command;
    }
    public String getMessage(){
	return command + ": currently executing method does not have access"
		+ "to the definition of the specified class, field, method"
		+ "or constructor";
    }

}
