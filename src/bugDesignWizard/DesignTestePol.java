package bugDesignWizard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import junit.framework.TestCase;

import org.designwizard.design.ClassNode;
import org.designwizard.exception.NotAnInterfaceException;
import org.designwizard.main.DesignWizard;

public class DesignTestePol extends TestCase {
	
	private DesignWizard dw;
	private PrintWriter out;
	
	@Override
	protected void setUp() throws Exception {
		dw = new DesignWizard("/home/stenio/Desktop/epolAtual/");
		out = new PrintWriter(new BufferedWriter(new FileWriter("/home/stenio/Desktop/bugsDesignWizard/DesignTestePol.txt", true)));
		super.setUp();
	}
	
	
	public void testViewDoesNotDependOnModel() throws IOException {
		out.println("Regra: View cannot-access Model");
		Set<ClassNode> allClasses = dw.getAllClasses();
		for (ClassNode caller : allClasses) {
			
			if (caller.getName().startsWith("br.gov.dpf.epol.action.") || caller.getName().startsWith("br.gov.dpf.epol.util.")) {
				for (ClassNode callee : caller.getCalleeClasses()) {
					if (callee.getName().startsWith("br.gov.dpf.epol.model.")) {
						out.println(caller.getName() + " dependsOn " + callee.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}
	
	public void testModelDoesNotDependOnView() throws NotAnInterfaceException, IOException {
		out.println("Regra: Model cannot-access View");
		Set<ClassNode> allClasses = dw.getAllClasses();
		for (ClassNode callee : allClasses) {
			
			if (callee.getName().startsWith("br.gov.dpf.epol.action.") || callee.getName().startsWith("br.gov.dpf.epol.util.")) {
				for (ClassNode caller : callee.getCallerClasses()) {
					if (caller.getName().startsWith("br.gov.dpf.epol.model.")) {
						out.println(caller.getName() + " dependsOn " + callee.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}
	
	
	public void testModelDoesNotExtendView() throws IOException {
		out.println("Regra: Model cannot-extend View");
		
		Set<ClassNode> allClasses = dw.getAllClasses();
		
		for (ClassNode c : allClasses) {
			
			if (c.getName().startsWith("br.gov.dpf.epol.model.")) {
				
				ClassNode superClass = c.getSuperClass();
				if (superClass.getName().startsWith("br.gov.dpf.epol.action.") || superClass.getName().startsWith("br.gov.dpf.epol.util.")) {
					out.println(c.getName() + " extends " + superClass.getName());
				}
				
			}
				
		}
		out.flush();
		out.close();
	}
	
	public void testModelDoesNotDependOnController() throws NotAnInterfaceException, IOException {
		out.println("Regra: Model cannot-access Controller");
		Set<ClassNode> allClasses = dw.getAllClasses();
		for (ClassNode callee : allClasses) {
			
			if (callee.getName().startsWith("br.gov.dpf.epol.session.") || callee.getName().startsWith("br.gov.dpf.epol.dao.")) {
				for (ClassNode caller : callee.getCallerClasses()) {
					if (caller.getName().startsWith("br.gov.dpf.epol.model.")) {
						out.println(caller.getName() + " dependsOn " + callee.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}
	
	public void testModelDoesNotExtendController() throws IOException {
		out.println("Regra: Model cannot-extend Controller");
		
		Set<ClassNode> allClasses = dw.getAllClasses();
		
		for (ClassNode c : allClasses) {
			
			if (c.getName().startsWith("br.gov.dpf.epol.model.")) {
				
				ClassNode superClass = c.getSuperClass();
				if (superClass.getName().startsWith("br.gov.dpf.epol.session.") || superClass.getName().startsWith("br.gov.dpf.epol.dao.")) {
					out.println(c.getName() + " extends " + superClass.getName());
				}
				
			}
				
		}
		out.flush();
		out.close();
	}
	
	public void testViewDoesNotDependOnDAOServiceAndBean() throws IOException {
		out.println("Regra: View cannot-access br.gov.dpf.epol.session.dao.DAOService, br.gov.dpf.epol.session.dao.DAOBean");
		
		Set<ClassNode> allClasses = dw.getAllClasses();
		for (ClassNode caller : allClasses) {
			
			if (caller.getName().startsWith("br.gov.dpf.epol.action.") || caller.getName().startsWith("br.gov.dpf.epol.util.")) {
				for (ClassNode callee : caller.getCalleeClasses()) {
					if (callee.getName().startsWith("br.gov.dpf.epol.session.dao.DAOService.") 
							|| callee.getName().startsWith("br.gov.dpf.epol.session.dao.DAOBean.")) {
						out.println(caller.getName() + " dependsOn " + callee.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}
	
}
