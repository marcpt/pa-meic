package ist.meic.pa.exceptions;

/**
 * Exception thrown then the user tries to invoke an operation that is only
 * available to non-primitive types.
 */
public class InspectPrimitiveTypeException extends InvalidInvocation{

    private static final long serialVersionUID = 1L;

    private String _msg;


    public InspectPrimitiveTypeException(){
    };

    public InspectPrimitiveTypeException(String str){
	_msg = str;
    };

    public String getMessage(){
	return "This operation is not allowed.\n" + _msg;

    }
}
