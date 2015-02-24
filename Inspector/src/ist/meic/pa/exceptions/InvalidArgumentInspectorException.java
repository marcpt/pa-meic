package ist.meic.pa.exceptions;

/**
 * Invocation thrown when the command signature is incorrect
 */
public class InvalidArgumentInspectorException extends InvalidInvocation{

    private static final long serialVersionUID = 1L;

    private String _msg = new String ("Invalid command.\n");


    public InvalidArgumentInspectorException(){
    };

    public InvalidArgumentInspectorException(String str){
	_msg = _msg + str;
    };

    public String getMessage(){
	return _msg;

    }
}
