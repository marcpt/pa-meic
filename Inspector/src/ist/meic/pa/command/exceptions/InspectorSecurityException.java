package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the security manager
 * indicates a security violation
 */
public class InspectorSecurityException extends CommandException {

    private static final long serialVersionUID = 1L;
    private SecurityException e;

    public InspectorSecurityException(SecurityException e) {
	this.e = e;
    }

    public String getMessage(){
	return "Security manager indicated a security violation:\n" + 
		e.getMessage();
    }

}
