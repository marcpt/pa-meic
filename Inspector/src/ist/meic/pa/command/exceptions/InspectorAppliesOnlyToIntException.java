package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the user tries to invoke any
 * inspector method that is only available to "ints"
 */
public class InspectorAppliesOnlyToIntException extends CommandException {

    private static final long serialVersionUID = 1L;
    String type;

    public InspectorAppliesOnlyToIntException(String type) {
	this.type = type;
    }
    
    public String getMessage(){
	return "Cannot apply operation to " + type + " (!= int)";
    }

}
