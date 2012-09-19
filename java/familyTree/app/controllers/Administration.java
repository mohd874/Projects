package controllers;

import java.util.List;

import play.Logger;

import models.Person;

public class Administration extends BaseController{

	
	public static void index(){
		render();
	}
	
	public static void persons(){
		List<Person> persons = Person.em().createQuery("SELECT p FROM Person p ORDER BY p.father").setMaxResults(30).getResultList();
		render(persons);
	}
}
