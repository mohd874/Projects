package controllers;

import play.mvc.*;
import models.*;

import play.modules.search.*;

import java.util.*;

public class Application extends Controller {

    public static void index() {
	List<Author> authors = Author.findAll();

	//testResults(authors);

	//System.out.println("There are "+authors.size()+" Authors in the List");

        render(authors);
    }

    public static void searchFullText(String qString) {
	//System.out.println("qString: "+qString);
	Set<Author> authors = null;
	
	if(null == qString || qString.trim().equals("")){
		authors.addAll((Collection)Author.findAll());
	}else{
		StringTokenizer st = new StringTokenizer(qString, " ");
		String token = null;
		authors = new HashSet<Author>();
		while(st.hasMoreTokens()){
			token = st.nextToken();
			Search.Query q = Search.search("firstName:"+token+"* OR lastName:"+token+"*", Author.class);
			authors.addAll((Collection)q.fetch());
		}
	}

	renderJSON(authors);
    }

    public static void searchSQL(String qString) {
	//System.out.println("qString: "+qString);
	Set<Author> authors = null;
	
	if(null == qString || qString.trim().equals("")){
		authors.addAll((Collection)Author.findAll());
	}else{
		StringTokenizer st = new StringTokenizer(qString, " ");
		String token = null;
		authors = new HashSet<Author>();
		while(st.hasMoreTokens()){
			token = st.nextToken();
			authors.addAll((Collection)Author.find("firstName LIKE '%"+token+"%' OR lastName LIKE '%"+token+"%'").fetch());
		}
	}

	renderJSON(authors);
    }

    public static void updateTest(){
	render();
    }

    private static void testResults(List<Author> list){
	System.out.println("List size: "+list.size());
	for(Author a : list){
		System.out.println("Author "+a.firstName+" has published "+a.books.size()+" book(s)");
	}
    }
}
