package ist.meic.pa.command;
import ist.meic.pa.command.exceptions.CommandException;
import ist.meic.pa.command.exceptions.NoActionException;
import ist.meic.pa.command.textui.CommandKey;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The Inspector Command Menu. Contains an unmodifiable map
 * where all concrete command classes are stored, and defines a
 * getAction method to be used by some client.
 */
public final class Command {

    private final Action call;
    private final Action inspect;
    private final Action modify;
    private final Action list;
    private final Action go_to;
    private final Action help;

    private final Map<String, ActionCreator> actionsMap;

    public Command(){

	call  = new Call();
	inspect = new Inspect();
	modify = new Modify();
	list = new Ls();
	go_to = new Goto();
	help = new Help();

	actionsMap =  Collections.unmodifiableMap(new HashMap<String, ActionCreator>(){
	    private static final long serialVersionUID = 1L; {
		put(CommandKey.CALL, new ActionCreator() { public Action create() { return call; }});
		put(CommandKey.INSPECT, new ActionCreator() { public Action create() { return inspect; }});
		put(CommandKey.MODIFY, new ActionCreator() { public Action create() { return modify; }});
		put(CommandKey.LS, new ActionCreator() { public Action create() { return list; }});
		put(CommandKey.GOTO, new ActionCreator() { public Action create() { return go_to; }});
		put(CommandKey.HELP, new ActionCreator() { public Action create() { return help; }});
	    }});
    };

    public Action getAction(String action) throws CommandException{

	ActionCreator act = actionsMap.get(action.split(" ")[0]);

	if(act == null){
	    throw new NoActionException(action);
	}
	return act.create();
    }
}
