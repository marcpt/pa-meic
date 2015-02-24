package ist.meic.pa.command.exceptions;

import java.lang.reflect.InvocationTargetException;

/**
 * Exception thrown when an exception has been thrown
 * by the inspected object
 */
public class InspectorInvocationTargetException extends CommandException {

    private static final long serialVersionUID = 1L;
    String msg;

    public InspectorInvocationTargetException(InvocationTargetException e1) {
	msg = e1.getMessage();
    }

    public String getMessage(){
	return "Exception thrown by an invoked"
		+ " method or constructor: " + msg;
    }

}
