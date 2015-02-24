package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the user performs any operation that
 * is not valid in the context of the current invocation
 */
public class InspectorInvalidInvocationException extends CommandException {

    private String command;
    
    private static final long serialVersionUID = 1L;
    
    public InspectorInvalidInvocationException(String command) {
	this.command = command;
    }
    
    public String getMessage(){
	return command;
    }
    
}
