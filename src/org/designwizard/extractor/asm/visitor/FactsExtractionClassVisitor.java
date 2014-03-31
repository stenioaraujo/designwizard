package org.designwizard.extractor.asm.visitor;

import java.net.URLClassLoader;
import java.util.Collection;

import org.designwizard.archmodule.ArchModule;
import org.designwizard.extractor.asm.event.FactEvent;
import org.designwizard.extractor.asm.event.FactsEventSourceImpl;
import org.designwizard.extractor.asm.util.OpcodesTranslator;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class FactsExtractionClassVisitor extends FactsEventSourceImpl {

	private String className;
	private URLClassLoader loader;

	public FactsExtractionClassVisitor(String className) {

		this.className = className;

	}
	
	public FactsExtractionClassVisitor(String className, URLClassLoader loader) {

		this.className = className;
		this.loader = loader;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

		this.className = name;
		this.visitPackage(name);

		if (this.loader != null) {
			visitModule(loader, className);
		}
		
		super.factEvent = new FactEvent(FactsExtractionClassVisitor.class, name);
		super.fireClassExtracted();

		Collection<String> modifiers = OpcodesTranslator.extractOpcodes(access);
		super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,name,modifiers);
		super.fireModifiersExtracted();

		
		// java.lang.Object does not extend any class
		if (superName != null) {
	
			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"EXTENDS",name,superName);
			super.fireRelationExtracted();
		
		}

		for (int i = 0; i < interfaces.length; ++i) {

			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"IMPLEMENTS",name,interfaces[i]);
			super.fireRelationExtracted();
		
		}

		super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,name,extractVisibility(access));
		super.fireVisibilityExtracted();

	}

	private void visitModule(URLClassLoader loader, String className) {
		try {
			Class<?> classe = loader.loadClass(className.replace('/', '.'));
			
			if (classe.isAnnotationPresent(ArchModule.class)) {
				ArchModule module = classe.getAnnotation(ArchModule.class);
				String moduleName = "ArchModule."+module.value();
				
				super.factEvent = new FactEvent(FactsExtractionClassVisitor.class, moduleName);
				super.fireModuleExtracted();
				
				super.factEvent = new FactEvent(FactsExtractionClassVisitor.class, "CONTAINS", moduleName, className);
				super.fireRelationExtracted();
			}
		} catch (ClassNotFoundException e) {
			// TODO Se der erro, diz que n�o tem m�dulo? Melhor
			e.printStackTrace();
		}
	}
	
	private void visitPackage(String className) {

		FactEvent event = extractPackage(className);
	

		super.factEvent = event;
		String packageName = super.factEvent.getEntity();
		super.firePackageExtracted();

		super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"CONTAINS", packageName, className);
		super.fireRelationExtracted();
		
		
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return super.visitAnnotation(desc, visible);
	}
	
	private FactEvent extractPackage(String className) {

		String classNameWithDots = className.replaceAll("[/\\\\]+", ".");

		if (!classNameWithDots.contains(".")) {
		
			return new FactEvent(FactsExtractionClassVisitor.class,"default");
		
		}
		
		String packageName = classNameWithDots.substring(0,classNameWithDots.lastIndexOf("."));
		return new FactEvent(FactsExtractionClassVisitor.class, packageName);
	}

	/*private Collection<FactEvent> extractPackages(String name) {

		Collection<FactEvent> returnCollection = new ArrayList<FactEvent>();

		String classNameWithDots = name.replaceAll("[/\\\\]+", ".");

		if (!classNameWithDots.contains(".")) {
		
			returnCollection.add(new FactEvent(FactsExtractionClassVisitor.class,"default"));
		
		} else {

			String packageName = classNameWithDots.substring(0,classNameWithDots.lastIndexOf("."));
	
			StringTokenizer st = new StringTokenizer(packageName,".");
			String builtString = "";
			builtString = st.nextToken();
	
			returnCollection.add(new FactEvent(FactsExtractionClassVisitor.class,builtString));
	
			while (st.hasMoreTokens()) {
				
				builtString = builtString + "." + st.nextToken();
				returnCollection.add(new FactEvent(FactsExtractionClassVisitor.class,builtString));
			
			}
		}
		
		return returnCollection;

	}*/
	
	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {

		String field = "F:"+this.className+"."+name;
		
		if (!name.contains("$")) {
		
			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"INSTANCEOF",field,desc);
			super.fireRelationExtracted();

			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"CONTAINS",this.className,field);
			super.fireRelationExtracted();

			Collection<String> modifiers = OpcodesTranslator.extractOpcodes(access);
			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,this.className+"."+name,modifiers);
			super.fireModifiersExtracted();

			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,field,extractVisibility(access));
			super.fireVisibilityExtracted();
		
		}
		
		return new FactsExtractionFieldVisitor();

	}

	@Override
	public void visitInnerClass(String name, String outerName, String shortName, int access) {
		String formattedName = name.replaceAll("[/\\\\]+", ".");
		
		// Anonymous Class
		if ( outerName == null) {
			visitAnonymousClass(formattedName, access);
		} else {
			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class, formattedName);
			super.fireClassExtracted();

			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"CONTAINS",outerName,formattedName);
			super.fireRelationExtracted();

			super.factEvent = new FactEvent(this,formattedName,extractVisibility(access));
			super.fireVisibilityExtracted();

			Collection<String> modifiers = OpcodesTranslator.extractOpcodes(access);
			modifiers.add("INNER");
			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,formattedName,modifiers);
			super.fireModifiersExtracted();
		}

	}

	private void visitAnonymousClass(String name, int access) {
	
		super.factEvent = new FactEvent(FactsExtractionClassVisitor.class, name);
		super.fireClassExtracted();
		
		String outerName = name.substring(0,name.lastIndexOf("$"));
				
		super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"CONTAINS",outerName,name);
		super.fireRelationExtracted();
		
		super.factEvent = new FactEvent(this,name,extractVisibility(access));
		super.fireVisibilityExtracted();
		
		Collection<String> modifiers = OpcodesTranslator.extractOpcodes(access);
		modifiers.add("INNER");
		modifiers.add("ANONYMOUS");
		
		super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,name,modifiers);
		super.fireModifiersExtracted();
	
	}
	
	

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		String method = ""; 

		if ((access & Opcodes.ACC_STATIC) != 0) {
		
			method = "S:"+" "+"M:"+this.className+"."+name+" "+desc;
		
		} else {
		
			method = "M:"+this.className+"."+name+" "+desc;
		
		}

		if (!method.contains("$SWITCH_TABLE$")) {
	
			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"CONTAINS",this.className,method);
			super.fireRelationExtracted();
		
		}

		method = "M:"+this.className+"."+name+" "+desc;
		
		if (!method.contains("$SWITCH_TABLE$")) {
		
			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,method,extractVisibility(access));
			super.fireSignatureExtracted();

			if (exceptions != null) {
			
				for (String exception : exceptions) {
				
					super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,"THROWS",method,exception);
					super.fireRelationExtracted();
				
				}
			
			}
			
			Collection<String> modifiers = OpcodesTranslator.extractOpcodes(access);
			super.factEvent = new FactEvent(FactsExtractionClassVisitor.class,method,modifiers);
			super.fireModifiersExtracted();
		
		}

		FactsExtractionMethodVisitor tcv = new FactsExtractionMethodVisitor(method);
		tcv.addListener(super.listeners.getFirst());
		return tcv;

	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {}
	@Override
	public void visitSource(String source, String debug) {}
}