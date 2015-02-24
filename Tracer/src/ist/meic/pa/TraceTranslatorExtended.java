package ist.meic.pa;

import javassist.*;
import javassist.expr.*;

public class TraceTranslatorExtended extends TraceTranslator {

	protected void makeHistorical(CtClass ctClass) throws CannotCompileException {
		super.makeHistorical(ctClass);
		
		final String castTemplate = 
				"{" +
				"  ist.meic.pa.Trace.storeHistory($1, \"  CAST to \" + \"%s\");" +
				"  $_ = $proceed($$);" +
				"}";
			
		for(CtBehavior ctBehavior : ctClass.getDeclaredBehaviors()) {
			ctBehavior.instrument(
				new ExprEditor() {
					public void edit(Cast ct) throws CannotCompileException {
							String behaviorFileLine;
							try {
								behaviorFileLine = ct.getType().getName() + " on " + ct.getFileName() + ":" + ct.getLineNumber();
								ct.replace(String.format(castTemplate, behaviorFileLine));
							} catch (NotFoundException e) {
								e.printStackTrace();
							}						
					}
				});
				
 		}
	}
}
