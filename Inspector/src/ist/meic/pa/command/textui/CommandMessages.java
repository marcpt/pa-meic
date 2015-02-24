package ist.meic.pa.command.textui;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Command Messages for Help Menu
 */
public final class CommandMessages {

    private static final String HEADER	= "Inspector commands:";

    private static final String CALL 	= CommandKey.CALL + " <value_0> ... <value_n>" +
	    "\nCalls the method named name using the currently presented object as"
	    + "\nreceiver and the provided values as arguments and inspects the "
	    + "\nreturned value, if there is one";

    private static final String INSPECT = CommandKey.INSPECT + " <name>" +
	    "\nInspects the value of the field named name of the object currently"
	    + "\npresented and makes that value the current inspected object.";

    private static final String MODIFY 	= CommandKey.MODIFY + " <name> <value>" +
	    "\nModifies the value of the field 'named' name of the object currently"
	    + "\npresented so that it becomes 'value'.";

    private static final String QUIT 	= CommandKey.QUIT + 
	    "\nTerminates inspection, allowing the calling program to"
	    + "\nproceed its execution.";

    private static final String GOTO 	= CommandKey.GOTO + " <n>" + 
	    "\nMakes the <n>th object in the history of inspected"
	    + "\nobjects the current inspected object.";

    private static final String LS 	= CommandKey.LS + 
	    "\nPrints the history of the last inspected objects.";

    private static final List<String> menu = Collections.unmodifiableList(new LinkedList<String>(){
	private static final long serialVersionUID = 1L; {

	    /*
	     * Menu header
	     */

	    add(HEADER);

	    /*
	     * Menu description
	     */

	    add(CALL);
	    add(GOTO);
	    add(INSPECT);
	    add(LS);
	    add(MODIFY);
	    add(QUIT);

	}});

    public static void printHelp(){
	for (String str : menu) {
	    System.err.println("\n" + str);
	}
    }
}