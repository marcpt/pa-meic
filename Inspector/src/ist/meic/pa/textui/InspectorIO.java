package ist.meic.pa.textui;

import ist.meic.pa.InspectedElement;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Stack;

/**
 * Inspector methods to print strings in the System.err and auxiliary
 * methods to reduce redundancy in the code when the program needs to
 * print information about certain fields about the inpected object
 */
public class InspectorIO {

    public static void syserr(String str){
	System.err.println(str);
    }

    public static void sysout(String str){
	System.out.print(str);
    }

    public static void println(String str){
	System.out.println(str);
    }

    public static void printFieldsInspection(Class<?> cl, Object ob)
	    throws IllegalArgumentException, IllegalAccessException {

	Field[] fieldsArray = cl.getDeclaredFields();

	for (Field field : fieldsArray) {
	    field.setAccessible(true);
	    if (!Modifier.isStatic(field.getModifiers())) {
		syserr(InspectorMessages.msgInformation(
			Modifier.toString(field.getModifiers()),
			field.getType(), field.getName(), field.get(ob)));
	    }
	}
    }

    public static void printPrimitiveInspection(InspectedElement nextObBI) throws IllegalArgumentException, IllegalAccessException{
	syserr(nextObBI.getObject().toString());
    }

    public static void printNonPrimitiveInspectionOld(InspectedElement nextObBI) throws IllegalArgumentException, IllegalAccessException{
	// its class
	classOfTheObject(nextObBI.getObject());

	//name, type and current value of each field (form its class)
	printFieldsInspection(nextObBI.getObject().getClass(), nextObBI.getObject());
	//name, type and current value of each field (form its Super class)
	
	Class<?> classC = nextObBI.getObject().getClass();
	while(classC.getSuperclass()!=null){
	    printFieldsInspection(classC.getSuperclass(), nextObBI.getObject());
	    classC = classC.getSuperclass();
	}
    }
    
    public static void printNonPrimitiveInspection(InspectedElement nextObBI) throws IllegalArgumentException, IllegalAccessException{
		Stack<Class<?>> classStack = new Stack<Class<?>>();
		Class<?> classC;

		// its class
		classOfTheObject(nextObBI.getObject());

		classStack.push(nextObBI.getObject().getClass());
		classC = nextObBI.getObject().getClass();
		while (classC.getSuperclass() != null) {
			classC = classC.getSuperclass();
			classStack.push(classC);
		}
		
		printFieldsInspection(classStack.pop(), nextObBI.getObject());
		while(!classStack.isEmpty()){
		    printFieldsInspection(classStack.pop(), nextObBI.getObject());
		}
	}

    public static void classOfTheObject(Object object){
	syserr(InspectorMessages.msgInstance(object));
	syserr(InspectorMessages.msgSeparator());
    }

    public static InspectedElement printInspection(Class<?> type, InspectedElement nextCurrentOBI) throws IllegalArgumentException, IllegalAccessException {
	// For primitive fields
	if (type.isPrimitive()) {
	    // prints his current value only
	    printPrimitiveInspection(nextCurrentOBI);

	    // return the newCurrentObject that is a primitive type
	    nextCurrentOBI.setIsPrimitive(true);
	    nextCurrentOBI.setPrimitiveType(type.toString());
	    return nextCurrentOBI;
	}

	// For non primitive fields
	// prints his class and name, type and current value of each field
	printNonPrimitiveInspection(nextCurrentOBI);

	// return the newCurrentObject that is a primitive type
	return nextCurrentOBI;

    }

}
