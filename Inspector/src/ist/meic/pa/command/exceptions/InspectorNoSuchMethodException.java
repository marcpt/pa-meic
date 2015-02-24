package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the user tries to invoke a method that does
 * not exist in the context of the currently inspected object
 */
public class InspectorNoSuchMethodException extends CommandException {
    
    
    private static final long serialVersionUID = 1L;
    private String methodName;

    public InspectorNoSuchMethodException(String methodName) {
	this.methodName = methodName;
    }

    public String getMessage(){
	return "No such method: " + methodName;
    }

}
