package ist.meic.pa;

/**
 * Used to represent elements that are inspected
 */
public class InspectedElement {

    private boolean isPrimitive;
    private Object object; 
    private String primitiveType;
    private boolean modified;

    public InspectedElement(){
	isPrimitive = false;
	object = null;
	primitiveType = null;
	modified = false;
    }

    public InspectedElement(Object obj) {
	isPrimitive = false;
	primitiveType = null;
	object = obj;
	modified = false;
    }
    
    public boolean modified(){
	return modified;
    }

    public boolean isPrimitive() {
	return isPrimitive;
    }

    public void setIsPrimitive(boolean bool) {
	isPrimitive = bool;
    }

    public Object getObject() {
	return object;
    }

    public void setObject(Object obj) {
	object = obj;
    }

    public String getPrimitiveType() {
	return primitiveType;
    }

    public void setPrimitiveType(String pt) {
	primitiveType = pt;
    }

    public void setModified() {
	modified = true;
    }
}
