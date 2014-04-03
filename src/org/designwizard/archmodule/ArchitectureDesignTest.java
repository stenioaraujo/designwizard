package org.designwizard.archmodule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;
import org.designwizard.design.ClassNode;
import org.designwizard.design.PackageNode;
import junit.framework.Assert;
import org.designwizard.main.DesignWizard;
import junit.framework.AssertionFailedError;
public class ArchitectureDesignTest extends TestCase {
	DesignWizard dw;
	Set<ClassNode> allClasses;
	List<String> listPackagesExceptaction = new ArrayList<String>();
	List<String> listClassesExceptaction = new ArrayList<String>();
	List<String> listDirectoryaction = new ArrayList<String>();
	Set<ClassNode> action = new HashSet<ClassNode>();
	List<String> listPackagesExceptlistagem = new ArrayList<String>();
	List<String> listClassesExceptlistagem = new ArrayList<String>();
	List<String> listDirectorylistagem = new ArrayList<String>();
	Set<ClassNode> listagem = new HashSet<ClassNode>();
	List<String> listPackagesExcepttest = new ArrayList<String>();
	List<String> listClassesExcepttest = new ArrayList<String>();
	List<String> listDirectorytest = new ArrayList<String>();
	Set<ClassNode> test = new HashSet<ClassNode>();
	List<String> listPackagesExcepttestunit = new ArrayList<String>();
	List<String> listClassesExcepttestunit = new ArrayList<String>();
	List<String> listDirectorytestunit = new ArrayList<String>();
	Set<ClassNode> testunit = new HashSet<ClassNode>();
	List<String> listPackagesExceptconverter = new ArrayList<String>();
	List<String> listClassesExceptconverter = new ArrayList<String>();
	List<String> listDirectoryconverter = new ArrayList<String>();
	Set<ClassNode> converter = new HashSet<ClassNode>();
	List<String> listPackagesExceptsessiontarefa = new ArrayList<String>();
	List<String> listClassesExceptsessiontarefa = new ArrayList<String>();
	List<String> listDirectorysessiontarefa = new ArrayList<String>();
	Set<ClassNode> sessiontarefa = new HashSet<ClassNode>();
	List<String> listPackagesExceptmodel = new ArrayList<String>();
	List<String> listClassesExceptmodel = new ArrayList<String>();
	List<String> listDirectorymodel = new ArrayList<String>();
	Set<ClassNode> model = new HashSet<ClassNode>();

	public ArchitectureDesignTest() throws Exception {
		dw = new DesignWizard("/home/stenio/erroDeIza/classes/");
		allClasses = dw.getAllClasses();
		listPackagesExceptaction.add("br.gov.dpf.epol.action.converter");
		listPackagesExceptaction.add("br.gov.dpf.epol.action.validator");
		listPackagesExceptaction.add("br.gov.dpf.epol.action.listagem");
		listDirectoryaction.add("br.gov.dpf.epol.action");
		for (String string : listDirectoryaction) {
			try {PackageNode naction = dw.getPackage(string);
			Set<ClassNode> nClassesaction = naction.getAllClasses();
			for(ClassNode c : nClassesaction) {
				if(!listClassesExceptaction.contains(c.getClassName()) && !listPackagesExceptaction.contains(c.getPackage().getClassName())) {action.add(c);}
			}
			} catch (Exception e) {
				action.add(dw.getClass(string));
			}
		}
		listDirectorylistagem.add("br.gov.dpf.epol.action.listagem");
		for (String string : listDirectorylistagem) {
			try {PackageNode nlistagem = dw.getPackage(string);
			Set<ClassNode> nClasseslistagem = nlistagem.getAllClasses();
			for(ClassNode c : nClasseslistagem) {
				if(!listClassesExceptlistagem.contains(c.getClassName()) && !listPackagesExceptlistagem.contains(c.getPackage().getClassName())) {listagem.add(c);}
			}
			} catch (Exception e) {
				listagem.add(dw.getClass(string));
			}
		}
		listClassesExcepttest.add("br.gov.dpf.epol.test.util.Log4jUtil");
		listDirectorytest.add("br.gov.dpf.epol.test.integration");
		listDirectorytest.add("br.gov.dpf.epol.test.util");
		for (String string : listDirectorytest) {
			try {PackageNode ntest = dw.getPackage(string);
			Set<ClassNode> nClassestest = ntest.getAllClasses();
			for(ClassNode c : nClassestest) {
				if(!listClassesExcepttest.contains(c.getClassName()) && !listPackagesExcepttest.contains(c.getPackage().getClassName())) {test.add(c);}
			}
			} catch (Exception e) {
				test.add(dw.getClass(string));
			}
		}
		listDirectorytestunit.add("br.gov.dpf.epol.test.unit");
		for (String string : listDirectorytestunit) {
			try {PackageNode ntestunit = dw.getPackage(string);
			Set<ClassNode> nClassestestunit = ntestunit.getAllClasses();
			for(ClassNode c : nClassestestunit) {
				if(!listClassesExcepttestunit.contains(c.getClassName()) && !listPackagesExcepttestunit.contains(c.getPackage().getClassName())) {testunit.add(c);}
			}
			} catch (Exception e) {
				testunit.add(dw.getClass(string));
			}
		}
		listDirectoryconverter.add("br.gov.dpf.epol.action.converter");
		listDirectoryconverter.add("br.gov.dpf.epol.util.LabelConverter");
		for (String string : listDirectoryconverter) {
			try {PackageNode nconverter = dw.getPackage(string);
			Set<ClassNode> nClassesconverter = nconverter.getAllClasses();
			for(ClassNode c : nClassesconverter) {
				if(!listClassesExceptconverter.contains(c.getClassName()) && !listPackagesExceptconverter.contains(c.getPackage().getClassName())) {converter.add(c);}
			}
			} catch (Exception e) {
				converter.add(dw.getClass(string));
			}
		}
		listDirectorysessiontarefa.add("br.gov.dpf.epol.session");
		for (String string : listDirectorysessiontarefa) {
			try {PackageNode nsessiontarefa = dw.getPackage(string);
			Set<ClassNode> nClassessessiontarefa = nsessiontarefa.getAllClasses();
			for(ClassNode c : nClassessessiontarefa) {
				if(!listClassesExceptsessiontarefa.contains(c.getClassName()) && !listPackagesExceptsessiontarefa.contains(c.getPackage().getClassName())) {sessiontarefa.add(c);}
			}
			} catch (Exception e) {
				sessiontarefa.add(dw.getClass(string));
			}
		}
		listDirectorymodel.add("br.gov.dpf.epol.model");
		for (String string : listDirectorymodel) {
			try {PackageNode nmodel = dw.getPackage(string);
			Set<ClassNode> nClassesmodel = nmodel.getAllClasses();
			for(ClassNode c : nClassesmodel) {
				if(!listClassesExceptmodel.contains(c.getClassName()) && !listPackagesExceptmodel.contains(c.getPackage().getClassName())) {model.add(c);}
			}
			} catch (Exception e) {
				model.add(dw.getClass(string));
			}
		}
	}
	//  only Listagem, Test can-access Action exceptModules TestUnit
	public void testRule0() {
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		allClassesExcept.addAll(testunit);
		for(ClassNode callee : action) {
			Set<ClassNode> callersaction = new HashSet<ClassNode>();
			callersaction.addAll(callee.getCallerClasses());
			if (!callersaction.isEmpty()) {
				for(ClassNode caller : callersaction) {
					try {
						Assert.assertTrue(!allClassesExcept.contains(caller) || listagem.contains(caller) || test.contains(caller));
					} catch (AssertionFailedError e) {
						System.out.println(callee.getName() + " foi acessado por " + caller.getName()); 
					}
				}
			}
		}
	}
	//  only Test can-access Converter
	public void testRule1() {
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for(ClassNode callee : converter) {
			Set<ClassNode> callersconverter = new HashSet<ClassNode>();
			callersconverter.addAll(callee.getCallerClasses());
			if (!callersconverter.isEmpty()) {
				for(ClassNode caller : callersconverter) {
					try {
						Assert.assertTrue(!allClassesExcept.contains(caller) || test.contains(caller));
					} catch (AssertionFailedError e) {
						System.out.println(callee.getName() + " foi acessado por " + caller.getName()); 
					}
				}
			}
		}
	}
	//  Converter cannot-access All exceptModules Converter
	public void testRule2() {
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		allClassesExcept.addAll(converter);
		for(ClassNode caller : converter) {
			Set<ClassNode> calleeconverter = new HashSet<ClassNode>();
			calleeconverter.addAll(caller.getCalleeClasses());
			if (!calleeconverter.isEmpty()) {
				for(ClassNode callee : calleeconverter) {
					try {
						Assert.assertTrue(allClassesExcept.contains(callee) || !allClasses.contains(callee));
					} catch (AssertionFailedError e) {
						System.out.println(caller.getName() + " acessou " + callee.getName()); 
					}
				}
			}
		}
	}
	//  Converter can-only-access Converter
	public void testRule3() {
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for(ClassNode caller : converter) {
			Set<ClassNode> calleeconverter = new HashSet<ClassNode>();
			calleeconverter.addAll(caller.getCalleeClasses());
			if (!calleeconverter.isEmpty()) {
				for(ClassNode callee : calleeconverter) {
					try {
						Assert.assertTrue(!allClassesExcept.contains(callee) || converter.contains(callee));
					} catch (AssertionFailedError e) {
						System.out.println(caller.getName() + " acessou " + callee.getName()); 
					}
				}
			}
		}
	}
	//  Model can-only-access SessionTarefa
	public void testRule4() {
		Set<ClassNode> allClassesExcept = new HashSet<ClassNode>();
		for(ClassNode caller : model) {
			Set<ClassNode> calleemodel = new HashSet<ClassNode>();
			calleemodel.addAll(caller.getCalleeClasses());
			if (!calleemodel.isEmpty()) {
				for(ClassNode callee : calleemodel) {
					try {
						Assert.assertTrue(!allClassesExcept.contains(callee) || sessiontarefa.contains(callee));
					} catch (AssertionFailedError e) {
						System.out.println(caller.getName() + " acessou " + callee.getName()); 
					}
				}
			}
		}
	}
}
