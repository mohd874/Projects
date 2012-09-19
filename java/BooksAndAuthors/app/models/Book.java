package models;

import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;

import play.modules.search.*;

@Entity
@Indexed
public class Book extends Model{
	@Field
	public String title;
	@Field
	@Column(unique=true)
	public String ISBN;
	@Field
	public String description;

	public Book(){
		title = null;
		ISBN = null;
		description = null;
	}

	public Book(String title, String ISBN, String description){
		this.title = title;
		this.ISBN = ISBN;
		this.description = description;
	}

	@Override
	public String toString(){
		return "Book title: "+title;
	}
}
