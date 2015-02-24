package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the inspected object does not have
 * a field or a specified name provided by the user
 */

public class InspectorNoSuchFieldException extends CommandException {
    
    private static final long serialVersionUID = 1L;
    private String command;

    public InspectorNoSuchFieldException(String field) {
	this.command = field;
    }
    
    public String getMessage(){
	return "Class doesn't have a field of a specified name: " + command;
    }

}
