package ist.meic.pa.command;

import java.util.LinkedList;

import ist.meic.pa.InspectedElement;
import ist.meic.pa.command.textui.CommandMessages;

/**
 * This command provides information to the user about the available
 * commands in the program, including the command signature and a short
 * description about each feature. 
 * 
 * "h"
 * 
 */
public class Help extends Action {

    public Help(){}

    @Override
    public InspectedElement execute(LinkedList<InspectedElement> history, String str){
	CommandMessages.printHelp();
	return null;
    }
}
