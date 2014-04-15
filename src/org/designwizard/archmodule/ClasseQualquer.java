package org.designwizard.archmodule;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlEnum;

@ArchModule("banco")
public class ClasseQualquer {

	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		testaQualquerCoisa();
	}
	
	private static void testaQualquerCoisa() throws IOException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		URLClassLoader loader = new URLClassLoader(new URL[]{new URL("file:///home/stenio/workplace2/designwizard/classes/org/designwizard/archmodule/ClasseQualquer.class")});
		Class<?> classe = loader.loadClass("org.designwizard.archmodule.ClasseQualquer");
		for (Annotation an: classe.getAnnotations()) {
			Class<?> a = an.annotationType();
			for (Method method: a.getMethods()) {
				if (method.getDeclaringClass().equals(an.annotationType())) {
					System.out.println(method.invoke(an, null));
				}
			}
		}
	}
	
	public String oi() {
		return "oi";
	}
}
