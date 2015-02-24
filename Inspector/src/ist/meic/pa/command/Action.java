package ist.meic.pa.command;

import ist.meic.pa.InspectedElement;
import ist.meic.pa.command.exceptions.CommandException;
import java.util.LinkedList;

/**
 * Abstract class for all actions available in the program
 */
public abstract class Action {

    public Action() {
    }

    public abstract InspectedElement execute(LinkedList<InspectedElement> history, String str)
	    throws CommandException;
}
