package ist.meic.pa.command;

import ist.meic.pa.InspectedElement;
import ist.meic.pa.textui.InspectorIO;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This command allows the user to inspect the list of the last
 * inspected objects

 * "ls"
 */
public class Ls extends Action {

    public Ls() {
    }

    @Override
    public InspectedElement execute(LinkedList<InspectedElement> history, String str) {

	/* List of all inspected
	 * objects so far
	 */

	InspectorIO.syserr("History of " + history.size() + " inspected"
		+ (history.size() == 1 ? " object" : " objects"));

	InspectedElement inspEl= null;
	ListIterator<InspectedElement> listIterator = history.listIterator();

	int index = 1;
	while (listIterator.hasNext()) {
	    inspEl = listIterator.next();
	    if(inspEl.isPrimitive())
		InspectorIO.syserr(index++ +  "\t" + inspEl.getPrimitiveType()
			+ (inspEl.modified() ? "\t(modified)" : ""));
	    else
		InspectorIO.syserr(index++ +  "\t" + inspEl.getObject().getClass().getName()
			+ (inspEl.modified() ? "\t(modified)" : ""));
	}

	/*
	 * Since this function does not change the inspected object
	 * it must return null so the history queue does not get updated
	 */

	return null;
    }
}
