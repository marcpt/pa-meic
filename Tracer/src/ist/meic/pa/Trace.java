package ist.meic.pa;

import java.util.*;

public class Trace {

	private static Map<Object, ArrayList<String>> history = new IdentityHashMap<Object, ArrayList<String>>();
	
	
	public static void print(Object ob) {
		if(history.containsKey(ob)) {
			System.err.println("Tracing for " + ob);
			for(String el : history.get(ob)) {
				System.err.println(el);
			}
		} else {
			System.err.println("Tracing for " + ob + " is nonexistent!");
		}
	}
	
	public static void storeHistory(Object ob, String el) {
		if(ob != null) {
			if(!history.containsKey(ob)) {
				history.put(ob, new ArrayList<String>());
			}
			history.get(ob).add(el);
		}
	}
	
	public static void manageStringCodeBefore(Object args[], String behaviorFileLine){
		for(int i = 0; i < args.length; i++) {
			storeHistory(args[i], "  -> " + behaviorFileLine);
		}
	}
	
	public static void manageStringCodeAfter(Object result,  String behaviorFileLine){
		storeHistory(result, "  <- " + behaviorFileLine);
	}
	
}
