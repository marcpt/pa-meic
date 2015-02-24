package ist.meic.pa;

import ist.meic.pa.command.Action;
import ist.meic.pa.command.Command;
import ist.meic.pa.command.exceptions.CommandException;
import ist.meic.pa.textui.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Main class. Includes the interactive shell
 * where users introduce their input
 */
public class Inspector {

    public void inspect(Object object) {

	InspectedElement inspectedEl = new InspectedElement(object);

	// Objects history list
	LinkedList<InspectedElement> history = new LinkedList<InspectedElement>();

	Command command = new Command();
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	Class<?> objectClassType = inspectedEl.getObject().getClass();

	// Prints inspection of the object
	try {
	    inspectedEl = InspectorIO.printInspection(objectClassType, inspectedEl);
	}
	catch (IllegalArgumentException e2) {
	    InspectorIO.syserr("ERROR! Inspector must exit.\n"
		    + "Reason: Illegal Argument -  " + e2.getMessage());
	    return;

	} catch (IllegalAccessException e2) {
	    InspectorIO.syserr("ERROR! Inspector must exit.\n"
		    + "Reason: Illegal Access: " + e2.getMessage());
	    return;
	}

	history.add(inspectedEl);

	/*
	 * Inspector's shell
	 */
	while (true) {

	    InspectorIO.sysout(InspectorMessages.PROMPT);
	    String str = null;

	    try {
		str = bufferedReader.readLine();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	    String[] cmd = str.split(" ", 2);
	    cmd[0] = cmd[0].toLowerCase(); // Command in lower case

	    if (cmd[0].equals("q")) {
		InspectorIO.syserr("\nExiting inspection...\n");
		break;
	    }

	    try {
		Action act = command.getAction(cmd[0]);

		inspectedEl = act.execute(history, cmd.length > 1 ? cmd[1] : null);

		if (inspectedEl != null) {
		    history.add(inspectedEl);
		}

	    } catch (CommandException e) {
		InspectorIO.syserr(e.getMessage());
	    } catch (NullPointerException e1){
		InspectorIO.syserr("Invalid Operation!");
	    }

	}
    }
}
