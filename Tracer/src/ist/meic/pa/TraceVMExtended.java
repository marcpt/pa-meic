package ist.meic.pa;

import javassist.*;

public class TraceVMExtended {
	private static void usage() {
		System.err.println("Java Tracer Extended");
		System.err.println("Usage: java ist.meic.pa.TraceVMExtended {class name}");
	}

	public static void main(String[] args) throws Exception, Throwable {
		if(args.length < 1) {
			usage();
			System.exit(1);
		} else {
			Translator translator = new TraceTranslatorExtended();
			ClassPool pool = ClassPool.getDefault();

			Loader classLoader = new Loader();
			classLoader.addTranslator(pool, translator);
				
			classLoader.delegateLoadingOf("ist.meic.pa.Trace");
			
			String[] restArgs = new String[args.length - 1];
			System.arraycopy(args, 1, restArgs, 0, restArgs.length);
			classLoader.run(args[0], restArgs);
		}
	}

}


