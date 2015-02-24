package ist.meic.pa.command.exceptions;

/**
 * Exception thrown when the user tries to invoke a command that does not
 * exist
 */

public class NoActionException extends CommandException{

    private static final long serialVersionUID = 1L;
    private final static String COMMAND_NOT_FOUND = "Command not found: ";
    private final static String HELP = "Type 'h' for help";

    private String _action;

    public NoActionException(){};

    public NoActionException(String action) {
	_action = action;
    }

    public String getAction(){
	return _action;
    }

    public String getMessage(){
	return COMMAND_NOT_FOUND + _action + "\n" + HELP;

    }
}
