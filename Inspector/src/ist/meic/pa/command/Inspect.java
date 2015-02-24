package ist.meic.pa.command;

import java.lang.reflect.Field;

import ist.meic.pa.InspectedElement;
import ist.meic.pa.command.exceptions.CommandException;
import ist.meic.pa.command.exceptions.InspectorIllegalAccessException;
import ist.meic.pa.command.exceptions.InspectorIllegalArgumentException;
import ist.meic.pa.command.exceptions.InspectorInvalidInvocationException;
import ist.meic.pa.command.exceptions.InspectorNoSuchFieldException;
import ist.meic.pa.command.exceptions.InspectorSecurityException;
import ist.meic.pa.command.textui.CommandKey;
import ist.meic.pa.exceptions.InvalidInvocation;
import ist.meic.pa.textui.InspectorIO;

import java.util.LinkedList;

/**
 * This command allows the user to inspect the last object
 * provided to the Inspector
 * 
 * "i <name>", where name is the name of the object to be inspected
 */
public class Inspect extends Action {

    public Inspect(){}

    @Override
    public InspectedElement execute(LinkedList<InspectedElement> history, String command)
	    throws CommandException { 

	InspectedElement objectCurrentlyPresented = history.getLast();

	// 1) Verifies if the inspection can be done

	// Verifies if the command was invoked properly
	try {
	    CommandsFunctions.checkCommandSignature(objectCurrentlyPresented, command, 1, 1);
	} catch (InvalidInvocation e) {
	    throw new InspectorInvalidInvocationException(CommandKey.INSPECT + " "
		    + (command == null ? "<empty>" : command) 
		    + ": " + e.getMessage());
	} 

	// Verifies if field exists
	Field field = null;

	try {
	    field = CommandsFunctions.findField(objectCurrentlyPresented.getObject().getClass(), command);
	} catch (SecurityException e) {
	    throw new InspectorSecurityException(e);
	} catch (NoSuchFieldException e) {
	    throw new InspectorNoSuchFieldException(command);
	}

	// 2) Inspection starts here

	// Gets field's class
	Class<?> classTypeOb = field.getType();
	// Gets current field's value
	field.setAccessible(true);
	Object fieldValue;
	try {
	    fieldValue = field.get(objectCurrentlyPresented.getObject());
	} catch (IllegalArgumentException e) {
	    throw new InspectorIllegalArgumentException(command);
	} catch (IllegalAccessException e) {
	    throw new InspectorIllegalAccessException(command);
	}

	/*
	 * Element inspected that will be objectCurrentlyPresented
	 * in the end of this operation 
	 */

	InspectedElement nextCurrentOBI = new InspectedElement(fieldValue);

	try {
	    nextCurrentOBI = InspectorIO.printInspection(classTypeOb, nextCurrentOBI);
	} catch (IllegalArgumentException e) {
	    throw new InspectorIllegalArgumentException(command);
	} catch (IllegalAccessException e) {
	    throw new InspectorIllegalAccessException(command);
	}
	return nextCurrentOBI;
    }

}
