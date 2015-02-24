package ist.meic.pa.command;

import java.util.LinkedList;

import ist.meic.pa.InspectedElement;
import ist.meic.pa.command.exceptions.CommandException;
import ist.meic.pa.command.exceptions.InspectorIllegalAccessException;
import ist.meic.pa.command.exceptions.InspectorIllegalArgumentException;
import ist.meic.pa.command.exceptions.InvalidGotoException;
import ist.meic.pa.textui.InspectorIO;

/**
 * This command allows the user to navigate through the history of
 * inspected objects.
 * 
 * "g <number>", there number is the index of the object in the
 * history list
 * 
 */
public class Goto extends Action {

    public Goto() {
    }

    @Override
    public InspectedElement execute(LinkedList<InspectedElement> history, String command)
	    throws CommandException {

	InspectedElement inspectedObject = null;

	try{
	    int input = Integer.parseInt(command);

	    /*
	     * 'goto' call without args
	     */
	    if(input < 1){
		throw new InvalidGotoException(command);
	    }

	    inspectedObject = history.get(Math.min(input - 1, history.size() - 1));
	} catch (NumberFormatException e){
	    throw new InvalidGotoException(command);
	}

	/*
	 * Update inspected argument
	 */
	try {
	    InspectorIO.printInspection(inspectedObject.getObject().getClass(), inspectedObject);
	} catch (IllegalArgumentException e) {
	    throw new InspectorIllegalArgumentException(command);
	} catch (IllegalAccessException e) {
	    throw new InspectorIllegalAccessException(command);
	}
	return (inspectedObject != history.getLast() ? inspectedObject : null);
    }
}
