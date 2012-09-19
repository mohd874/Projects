package controllers;

import play.mvc.*;
import models.*;

import java.util.*;

public class Application extends Controller {

    public static void index() {
	List<Author> authors = Author.findAll();
	for(Author a : authors){
		System.out.println("Author "+a.firstName+" has published "+a.books.size()+" book(s)");
	}
	System.out.println("There are "+authors.size()+" Authors in the List");

        render(authors);
    }

    public static void search(String query) {
	renderJSON("test");
    }
}
