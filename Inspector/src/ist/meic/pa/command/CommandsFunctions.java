package ist.meic.pa.command;

import ist.meic.pa.InspectedElement;
import ist.meic.pa.exceptions.InspectPrimitiveTypeException;
import ist.meic.pa.exceptions.InvalidArgumentInspectorException;
import ist.meic.pa.exceptions.InvalidInvocation;

import java.lang.reflect.Field;

/**
 * Defines auxiliary functions to analyze the command input
 * provided by the user, such as checking command signature,
 * finding fields in an object and processing the input itself.
 */
public class CommandsFunctions {
    /*
     * For "i name", "m name value" and "c name value0 value1 ..."
     * checks method/commands signature - the method's/command's name
     * and the parameter types and number of parameters.
     */
    public static void checkCommandSignature(InspectedElement ie, String str, int numMinArgs, int numMaxArgs) throws InvalidInvocation {
	/* Checks if the current object is
	 * not from a primitive type
	 */
	if (ie.isPrimitive()) {
	    throw new InspectPrimitiveTypeException("Current object being inspected"
		    + " is a primitive type.");
	}

	/*
	 * Checks if arguments are provided.
	 * "i name", "m name value", "c name value0 value1 ..."
	 */
	if(str == null){
	    throw new InvalidArgumentInspectorException("You need to provide arguments.");
	}

	/*
	 * Checks number of parameters
	 */
	String[] splitedParameters = splitParameters(str);
	if( !(splitedParameters.length >= numMinArgs  && splitedParameters.length <= numMaxArgs) ){
	    throw new InvalidArgumentInspectorException("Wrong number of arguments.");
	}
    }

    public static Field findField(Class<?> cl, String fieldName)
	    throws SecurityException, NoSuchFieldException {
	Class<?> c = cl;

	Field[] flds = c.getDeclaredFields();
	for (Field field : flds) {
	    if(field.getName().equals(fieldName)){
		return field;
	    }
	}
	while(c.getSuperclass() != null){
	    c = c.getSuperclass();
	    flds = c.getDeclaredFields();
	    for (Field field : flds) {
		if(field.getName().equals(fieldName)){
		    return field;
		}
	    }
	}
	throw new NoSuchFieldException(); 
    }

    /*
     * Removes redundant spaces
     */
    public static String[] splitParameters(String str){
	str = str.replaceAll("^\\s+", ""); 
	str = str.replaceAll("\\s+$", "");
	return str.split("\\s+");
    }

}
