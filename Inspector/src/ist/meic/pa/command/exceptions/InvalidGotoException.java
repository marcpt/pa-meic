package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the user invokes the goto command with zero or
 * a negative number as the argument
 */
public class InvalidGotoException extends CommandException {

    private static final long serialVersionUID = 1L;

    private final static String INVALID_GO_TO = "Invalid goto command: ";

    private String go_to;

    public InvalidGotoException(){
    };

    public InvalidGotoException(String go_to) {
	this.go_to = go_to;
    }

    public String getAction(){
	return go_to;
    }

    public String getMessage(){
	return INVALID_GO_TO + go_to + "\nType \"ls\" to access history list";
    }
}
