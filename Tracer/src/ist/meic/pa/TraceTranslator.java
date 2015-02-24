package ist.meic.pa;

import javassist.*;
import javassist.expr.*;

public class TraceTranslator implements Translator {

	@Override
	public void start(ClassPool pool) throws NotFoundException, CannotCompileException {}
	
	@Override
	public void onLoad(ClassPool pool, String className) throws NotFoundException, CannotCompileException {
		CtClass ctClass = pool.get(className);
		makeHistorical(ctClass);
	
	}

	protected void makeHistorical(CtClass ctClass) throws CannotCompileException {
		final String constructorTemplate = 
			"{" +
			"  $_ = $proceed($$);" +
			"  ist.meic.pa.Trace.storeHistory($_, \"  <- \" + \"%s\");" +
			"}";
		
		final String methodTemplate =
			"{" +
			"  ist.meic.pa.Trace.manageStringCodeBefore($args, \"%s\");" +
			"  $_ = $proceed($$);" +
			"  ist.meic.pa.Trace.manageStringCodeAfter(($w) $_, \"%s\");" +
			"}";
		
		for(CtBehavior ctBehavior : ctClass.getDeclaredBehaviors()) {
			ctBehavior.instrument(
				new ExprEditor() {
					public void edit(NewExpr ne) throws CannotCompileException {
						
						try {
							String behaviorFileLine = ne.getConstructor().getLongName() + " on " + ne.getFileName() + ":" + ne.getLineNumber();
							ne.replace(String.format(constructorTemplate, behaviorFileLine));
						} catch(NotFoundException e) {
							e.printStackTrace();
						}
					}
					
					public void edit(MethodCall mc) throws CannotCompileException {
						
						try {
							String behaviorFileLine = mc.getMethod().getLongName() + " on " + mc.getFileName() + ":" + mc.getLineNumber();
							mc.replace(String.format(methodTemplate, behaviorFileLine, behaviorFileLine));
						} catch(NotFoundException e) {
							e.printStackTrace();
						}
					}
				});
 		}
	}

}
