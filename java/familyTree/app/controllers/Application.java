package controllers;

import java.util.*;
import models.*;
import play.mvc.*;
import utils.*;
import play.Logger;

//import play.modules.search.*;
public class Application extends BaseController {

    public static void index() {
        List<Person> persons = Person.find("father = null").fetch();
        render(persons);
    }

    public static void show(long id) {
        Person p = Person.findById(id);
        render(p);
    }

    public static void orgchart() {
		List<Person> persons = Person.find("father = null").fetch();
		Person firstFather = persons.get(0);
		//String data = GoogleChartsUtils.createFamilyTreeDataTable(persons.get(0));
		String data = ChartsUtils.createJsonFamilyTree(firstFather);
		render(persons, data);
    }

    public static void search() {
        render();
    }

    public static void list(String qString, boolean isFullTextSearch, boolean isAjaxCall, int size, int page) {
        //For Debugging only
//        LOG("Application.search() was called");
//        LOG("Args:");
//        LOG("qString: " + qString);
//        LOG("isFullTextSearch: " + isFullTextSearch);
//        LOG("isAjaxCall: " + isAjaxCall);
//        LOG("size: " + size);
//        LOG("page: " + page);

        Set<Person> persons = null;

        if (null == qString || qString.trim().equals("")) {
            persons = new HashSet<Person>();
            persons.addAll((Collection) Person.findAll());
        } else {
            StringTokenizer st = new StringTokenizer(qString, " ");
            String token = null;
            persons = new HashSet<Person>();
            while (st.hasMoreTokens()) {
                token = st.nextToken();
                if (isFullTextSearch) {
                    //Search.Query q = Search.search("name:"+token+"*", Person.class);
                    //persons.addAll((Collection)q.fetch());
                } else {
                    persons.addAll((Collection) Person.find("name LIKE '%" + token + "%' OR father.name LIKE '%" + token + "%'").fetch(page, size));
                }
            }
        }

        if (isAjaxCall) {
            renderJSON(persons);
        } else {
            render(persons, page, size);
        }
    }

	public static void shortDescription(long id){
		Person p = Person.findById(id);
		String fullName = NameUtil.getPersonName(p);
//		LOG("p: "+p.name);
//		LOG("fullname: "+fullName);
		render(p, fullName);
	}
    
    public static void details(long id){
    	Person person = Person.findById(id);
    	//LOG(person.name+"::"+person.id+"::"+person.dateOfBirth);
    	render(person);
    }
    
    public static void addChild(String childName, long childFatherId){
    	Person child = new Person();
    	child.name = childName;
    	child.father = childFatherId;
    	child.save();
    	details(childFatherId);
    }
    
    public static void save(Person person){
//    	LOG("saveing... :: person.id = "+person.id);
    	
    	person.save();
    	details(person.id);
    }
}
