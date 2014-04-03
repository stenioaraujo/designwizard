package org.designwizard.design;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.designwizard.design.relation.Relation;
import org.designwizard.design.relation.Relation.TypesOfRelation;

public class AnnotationNode implements Entity{
	private String name;
	private Set<String> attributes;
	private Map<String, String> values;
	private Set<Relation> relations;
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ClassNode getClassNode() {
		return null;
	}

	@Override
	public String getShortName() {
		return name.substring(name.lastIndexOf('.'));
	}

	@Override
	public PackageNode getPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRelation(Relation relation) {
		if (relation != null) {
			relations.add(relation);
		}
	}

	@Override
	public boolean containsRelation(Relation relation) {
		return relations.contains(relation);
	}

	@Override
	public Collection<Relation> getRelations(TypesOfRelation type) {
		Set<Relation> feedBack = new HashSet<Relation>();
		
		for (Relation relation: relations) {
			if (relation.getType() == type) {
				feedBack.add(relation);
			}
		}
		
		return feedBack;
	}

	@Override
	public boolean removeRelation(Relation relation) {
		return relations.remove(relation);
	}

	@Override
	public TypesOfEntities getTypeOfEntity() {
		return TypesOfEntities.ANNOTATION;
	}

	@Override
	public Set<MethodNode> getCallerMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<MethodNode> getCalleeMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ClassNode> getCallerClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ClassNode> getCalleeClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PackageNode> getCallerPackages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PackageNode> getCalleePackages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Modifier> getModifiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modifier getVisibility() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addModifier(Modifier modifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModifiers(Collection<Modifier> modifiers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsModifiers(Modifier... modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String[]> getImpactOfAChange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAbstract() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
