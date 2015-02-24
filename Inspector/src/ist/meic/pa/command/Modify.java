package ist.meic.pa.command;

import ist.meic.pa.InspectedElement;
import ist.meic.pa.command.exceptions.CommandException;
import ist.meic.pa.command.exceptions.InspectorAppliesOnlyToIntException;
import ist.meic.pa.command.exceptions.InspectorIllegalAccessException;
import ist.meic.pa.command.exceptions.InspectorIllegalArgumentException;
import ist.meic.pa.command.exceptions.InspectorInvalidInvocationException;
import ist.meic.pa.command.exceptions.InspectorNoSuchFieldException;
import ist.meic.pa.command.exceptions.InspectorSecurityException;
import ist.meic.pa.command.textui.CommandKey;
import ist.meic.pa.exceptions.InvalidInvocation;
import ist.meic.pa.textui.InspectorIO;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * This command allows the user to modify a field of the currently
 * inspected object
 * "m <name> <value>"
 */
public class Modify extends Action {

    public Modify(){
    }

    @Override
    public InspectedElement execute(LinkedList<InspectedElement> history, String command)
	    throws CommandException{

	InspectedElement elementBeingModified = history.getLast();

	try {
	    CommandsFunctions.checkCommandSignature(elementBeingModified, command, 2, 2);
	} catch (InvalidInvocation e) {
	    throw new InspectorInvalidInvocationException(CommandKey.MODIFY + " "
		    + (command == null ? "<empty>" : command) 
		    + ": " + e.getMessage());
	}

	String[] nameAndValue = CommandsFunctions.splitParameters(command);
	Field fieldToModify = null;
	try {
	    fieldToModify = CommandsFunctions.findField(elementBeingModified.getObject().getClass(), nameAndValue[0]);
	} catch (SecurityException e) {
	    throw new InspectorSecurityException(e);
	} catch (NoSuchFieldException e) {
	    throw new InspectorNoSuchFieldException(command);
	}

	/*
	 * Applies only to ints
	 */
	if( !(int.class == fieldToModify.getType())){
	    throw new InspectorAppliesOnlyToIntException(fieldToModify.getType().toString());
	}

	Object objBeingModified = elementBeingModified.getObject();
	int newValue = Integer.parseInt(nameAndValue[1]);

	fieldToModify.setAccessible(true);

	/*
	 * Sets the new value to the field to modify in
	 * the objBeingModifiedsets the new value to the field
	 * to modify in the obBeingModified
	 */
	try {
	    fieldToModify.set(objBeingModified, newValue);
	} catch (IllegalArgumentException e) {
	    throw new InspectorIllegalArgumentException(command);
	} catch (IllegalAccessException e) {
	    throw new InspectorIllegalAccessException(command);
	}

	/*
	 * Prints inspection from the object
	 * that owns the field being modified 
	 */
	try {
	    InspectorIO.printNonPrimitiveInspection(elementBeingModified);
	} catch (IllegalArgumentException e) {
	    throw new InspectorIllegalArgumentException(command);
	} catch (IllegalAccessException e) {
	    throw new InspectorIllegalAccessException(command);
	}

	elementBeingModified.setModified();

	/*
	 * As the object is being modified, no new
	 * object is added to the history list
	 */
	return null;
    }

}
