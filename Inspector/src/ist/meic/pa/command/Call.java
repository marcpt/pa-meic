package ist.meic.pa.command;

import ist.meic.pa.InspectedElement;
import ist.meic.pa.command.exceptions.*;
import ist.meic.pa.command.textui.CommandKey;
import ist.meic.pa.exceptions.InvalidInvocation;
import ist.meic.pa.textui.InspectorIO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * This command allows the user to call the method named <name>, providing
 * the respective arguments.
 * "c <name> <value-0> <value-1> ... <value-n>"
 */
public class Call extends Action {

    public Call() {}

    @Override
    public InspectedElement execute(LinkedList<InspectedElement> history, String command)
	    throws CommandException {

	/* Object currently presented */
	InspectedElement objCP = history.getLast();

	/*
	 * Verifies if command signature is correct
	 * Throws exception if signature does not match
	 * any of the available ones
	 */
	try {
	    CommandsFunctions.checkCommandSignature(objCP, command, 0, Integer.MAX_VALUE);
	} catch (InvalidInvocation e1) {
	    throw new InspectorInvalidInvocationException(CommandKey.CALL + " "
		    + (command == null ? "<empty>" : command) 
		    + ": " + e1.getMessage());
	} 

	/* Removes redundant spaces */
	String[] splittedParameters = CommandsFunctions.splitParameters(command);
	/* Gets the method name */
	String methodName = splittedParameters[0];

	/* Don't forget that splittedParameters
	 * includes method's name
	 */

	@SuppressWarnings("unchecked")
	Class<Integer>[] parameterTypes = (Class<Integer>[]) new Class[splittedParameters.length - 1];

	for (int i = 1; i < splittedParameters.length; i++) {
	    parameterTypes[i - 1] = Integer.TYPE;
	}

	/* Throws NoSuchMethodException */
	Method meth;

	try{
	    meth = objCP.getObject().getClass().getDeclaredMethod(methodName, parameterTypes);
	} catch(NoSuchMethodException e){
	    throw new InspectorNoSuchMethodException(methodName);
	}

	/* Turn arguments in the string into ints arguments */
	Integer[] argsMeth = new Integer[splittedParameters.length - 1];
	for (int i = 1; i < splittedParameters.length; i++) {
	    argsMeth[i - 1] = Integer.parseInt(splittedParameters[i]);
	}

	meth.setAccessible(true);

	/*
	 * Invoking method and saving returned
	 * object in callMethReturn
	 */
	Object callMethReturn = null;;
	try {
	    callMethReturn = meth.invoke(objCP.getObject(), (Object[]) argsMeth);
	} catch (IllegalArgumentException e1) {
	    throw new InspectorIllegalArgumentException(command);
	} catch (IllegalAccessException e1) {
	    throw new InspectorIllegalAccessException(command);
	} catch (InvocationTargetException e1) {
	    throw new InspectorInvocationTargetException(e1);
	}

	/*
	 * Getting returned object's type
	 */
	Class<?> returnType = meth.getReturnType();

	/*
	 * Next possible object
	 * in the "stack"
	 */
	InspectedElement nextCurrentOBI = new InspectedElement(callMethReturn);

	/* Inspects returned value, if there is one */
	if (!returnType.equals(Void.TYPE)) {
	    try {
		return InspectorIO.printInspection(returnType, nextCurrentOBI);
	    } catch (IllegalArgumentException e) {
		throw new InspectorIllegalArgumentException(command);
	    } catch (IllegalAccessException e) {
		throw new InspectorIllegalAccessException(command);
	    }
	}
	else{ /* Returned type is VOID */
	    return null;
	}
    }
}
