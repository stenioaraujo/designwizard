package bugDesignWizard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;
import org.designwizard.design.ClassNode;

import junit.framework.Assert;
import org.designwizard.main.DesignWizard;
import junit.framework.AssertionFailedError;
import org.designwizard.exception.InexistentEntityException;

public class ArchitectureDesignTest extends TestCase {
	DesignWizard dw;
	private PrintWriter out;
	Set<ClassNode> allClasses;
	List<String> listPackagesExceptmodel = new ArrayList<String>();
	List<String> listClassesExceptmodel = new ArrayList<String>();
	List<String> listDirectorymodel = new ArrayList<String>();
	Set<ClassNode> model = new HashSet<ClassNode>();
	List<String> listPackagesExceptview = new ArrayList<String>();
	List<String> listClassesExceptview = new ArrayList<String>();
	List<String> listDirectoryview = new ArrayList<String>();
	Set<ClassNode> view = new HashSet<ClassNode>();
	List<String> listPackagesExceptcontroller = new ArrayList<String>();
	List<String> listClassesExceptcontroller = new ArrayList<String>();
	List<String> listDirectorycontroller = new ArrayList<String>();
	Set<ClassNode> controller = new HashSet<ClassNode>();

	public ArchitectureDesignTest() throws Exception {
		dw = new DesignWizard(
				"/home/stenio/Desktop/epolAtual/");
		out = new PrintWriter(new BufferedWriter(new FileWriter("/home/stenio/Desktop/bugsDesignWizard/Architecture.txt", true)));
		allClasses = dw.getAllClasses();
		listDirectorymodel.add("br.gov.dpf.epol.model.");
		for (String string : listDirectorymodel) {
			for (ClassNode c : dw.getAllClasses()) {
				if (c.getClassName().startsWith(string)) {
					model.add(c);
				}
			}
		}
		listDirectoryview.add("br.gov.dpf.epol.action.");
		listDirectoryview.add("br.gov.dpf.epol.util.");
		for (String string : listDirectoryview) {
			for (ClassNode c : dw.getAllClasses()) {
				if (c.getClassName().startsWith(string)) {
					view.add(c);
				}
			}
		}
		listDirectorycontroller.add("br.gov.dpf.epol.session.");
		listDirectorycontroller.add("br.gov.dpf.epol.dao.");
		for (String string : listDirectorycontroller) {
			for (ClassNode c : dw.getAllClasses()) {
				if (c.getClassName().startsWith(string)) {
					controller.add(c);
				}
			}
		}
	}

	// View cannot-access Model
	public void testRule0() {
		out.println("Regra:  View cannot-access Model");
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for (ClassNode caller : view) {
			Set<ClassNode> calleeview = new HashSet<ClassNode>();
			calleeview.addAll(caller.getCalleeClasses());
			if (!calleeview.isEmpty()) {
				for (ClassNode callee : calleeview) {
					try {
						Assert.assertTrue(allClassesExcept.contains(callee)
								|| (!model.contains(callee)));
					} catch (AssertionFailedError e) {
						out.println(caller.getName() + " dependsOn "
								+ callee.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}

	// Model cannot-access View
	public void testRule1() {
		out.println("Regra:  Model cannot-access View");
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for (ClassNode caller : model) {
			Set<ClassNode> calleemodel = new HashSet<ClassNode>();
			calleemodel.addAll(caller.getCalleeClasses());
			if (!calleemodel.isEmpty()) {
				for (ClassNode callee : calleemodel) {
					try {
						Assert.assertTrue(allClassesExcept.contains(callee)
								|| (!view.contains(callee)));
					} catch (AssertionFailedError e) {
						out.println(caller.getName() + " dependsOn "
								+ callee.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}

	// Model cannot-extend View
	public void testRule2() {
		out.println("Regra:  Model cannot-extend View");
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for (ClassNode c : model) {
			if (!allClassesExcept.contains(c)) {
				for (ClassNode ext : view) {
					try {
						Assert.assertTrue(!c.extendsClass(ext));
					} catch (AssertionFailedError e) {
						out.println(c.getName() + " extends "
								+ ext.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}

	// Model cannot-access Controller
	public void testRule3() {
		out.println("Regra:  Model cannot-access Controller");
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for (ClassNode caller : model) {
			Set<ClassNode> calleemodel = new HashSet<ClassNode>();
			calleemodel.addAll(caller.getCalleeClasses());
			if (!calleemodel.isEmpty()) {
				for (ClassNode callee : calleemodel) {
					try {
						Assert.assertTrue(allClassesExcept.contains(callee)
								|| (!controller.contains(callee)));
					} catch (AssertionFailedError e) {
						out.println(caller.getName() + " dependsOn "
								+ callee.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}

	// Model cannot-extend Controller
	public void testRule4() {
		out.println("Regra:  Model cannot-extend Controller");
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for (ClassNode c : model) {
			if (!allClassesExcept.contains(c)) {
				for (ClassNode ext : controller) {
					try {
						Assert.assertTrue(!c.extendsClass(ext));
					} catch (AssertionFailedError e) {
						out.println(c.getName() + " extends "
								+ ext.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}

	// View cannot-access br.gov.dpf.epol.session.dao.DAOService,
	// br.gov.dpf.epol.session.dao.DAOBean
	public void testRule5() {
		List<ClassNode> listDAOService = new ArrayList<ClassNode>();
		try {
			listDAOService.add(dw
					.getClass("br.gov.dpf.epol.session.dao.DAOService"));
		} catch (InexistentEntityException e1) {
		}
		List<ClassNode> listDAOBean = new ArrayList<ClassNode>();
		try {
			listDAOBean.add(dw.getClass("br.gov.dpf.epol.session.dao.DAOBean"));
		} catch (InexistentEntityException e1) {
		}
		out
				.println("Regra:  View cannot-access br.gov.dpf.epol.session.dao.DAOService, br.gov.dpf.epol.session.dao.DAOBean");
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for (ClassNode caller : view) {
			Set<ClassNode> calleeview = new HashSet<ClassNode>();
			calleeview.addAll(caller.getCalleeClasses());
			if (!calleeview.isEmpty()) {
				for (ClassNode callee : calleeview) {
					try {
						Assert.assertTrue(allClassesExcept.contains(callee)
								|| (!listDAOService.contains(callee) && !listDAOBean
										.contains(callee)));
					} catch (AssertionFailedError e) {
						out.println(caller.getName() + " dependsOn "
								+ callee.getName());
					}
				}
			}
		}
		out.flush();
		out.close();
	}
}
