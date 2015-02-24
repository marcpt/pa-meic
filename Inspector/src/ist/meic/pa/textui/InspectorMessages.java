package ist.meic.pa.textui;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Inspector methods to print command prompt and 
 * information about an object
 */
public class InspectorMessages {

    public static final String PROMPT = "\n> ";

    public static final String msgSeparator(){
	return "----------";
    }

    public static final String msgInstance(Object object) {
	return object.toString() + " is an instance of class " + 
		object.getClass().getName();
    }

    public static final String printArray(Object[] obA){
	String str = "[";

	for (int i = 0; i < obA.length; i++) {
	    str = str + " " + obA[i];
	}
	return str + " ]";
    }

    private static final String printAnyArray(Object aObject) {
	String str = "";
	if (aObject.getClass().isArray()) {
	    if (aObject instanceof Object[]) // can we cast to Object[]
		str = Arrays.toString((Object[]) aObject);
	    else {  // we can't cast to Object[] - case of primitive arrays
		int length = Array.getLength(aObject);
		Object[] objArr = new Object[length];
		for (int i=0; i<length; i++)
		    objArr[i] =  Array.get(aObject, i);
		str = str + Arrays.toString(objArr);
	    }
	}
	return str;
    }

    public static final String msgInformation(Object modifier,
	    Object type, Object name, Object object){
	if(object==null){
	    return (modifier.toString().isEmpty() ? "" : modifier.toString() + " ")
		    + type.toString() + " " + name.toString()
		    + " = " + "null";
	} else {
	    if(object.getClass().isArray()){
		return (modifier.toString().isEmpty() ? "" : modifier.toString() + " ")
			+ type.toString() + " " + name.toString()
			+ " = " + printAnyArray(object);
	    } else {
		return (modifier.toString().isEmpty() ? "" : modifier.toString() + " ")
			+ type.toString() + " " + name.toString()
			+ " = " + object.toString();
	    }
	}
    }
}
