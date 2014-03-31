package org.designwizard.archmodule;

import java.io.IOException;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;

import org.designwizard.design.ClassNode;
import org.designwizard.main.DesignWizard;

@ArchModule("banco")
public class ClasseQualquer {

	public static void main(String[] args) throws IOException{
		testaQualquerCoisa();
	}
	
	private static void testaQualquerCoisa() throws IOException{
		//DesignWizard dw = new DesignWizard("/home/stenio/Desktop/projetinho.jar");
		DesignWizard dw = new DesignWizard("/home/stenio/Desktop/sampleEAR/queGeramDiscordanciaNoDesignWizard/jboss-developer-jboss-eap-quickstarts-35b9ef7");
		
		Set<ClassNode> classes = dw.getAllClasses();
		
		for (ClassNode classe : classes){
			System.out.println(classe.toString());
		}
		/*
		dw = new DesignWizard("/home/stenio/Desktop/testeDePenetracaoDoDesignWizard.zip");
		
		classes = dw.getAllClasses();
		/*
		for (ClassNode classe : classes){
			System.out.println(classe.toString());
		}*/
	}
	
	public String oi() {
		return "oi";
	}
}
