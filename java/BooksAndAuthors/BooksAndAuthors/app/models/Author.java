package models;

import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
import play.modules.search.*;

@Entity
@Indexed
public class Author extends Model{
	@Field
	public String firstName;
	@Field
	public String lastName;
	@OneToMany
	public List<Book> books;

	public Author(){
		firstName = null;
		lastName = null;
		books = null;
	}

	public Author(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public Author(String firstName, String lastName, List<Book> books){
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = books;
	}

	public void addBook(Book book){
		if(null==books)books = new ArrayList<Book>();
		books.add(book);			
	}

	@Override
	public String toString(){
		return "Author name: "+firstName+" "+lastName;
	}
}
