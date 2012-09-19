package models;

/**
 *
 * @author mohamed
 */
import play.*;
import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;
import java.util.*;
import play.Logger;

//import play.modules.search.*;
@Entity
//@Indexed
public class Person extends Model {

    //@Field
    @Required
    public String name;
    
    public Long father;
    //@OneToMany(mappedBy = "father")
    //public Set<Person> children;
    @Required
    public Date dateOfBirth;
    //@Required
    public Date dateOfDeath;
    @Lob
    @MaxSize(10000)
    public String about;
    public Blob picture;

    public List<Person> getChildren() {
		List<Person> children = Person.find("father", id).fetch();
        Logger.info(name+"\'s children are: ");
		for(int i=0;i<children.size();i++){
			Logger.info(children.get(i).name);
		}
		return children;
    }

    public String toString() {
        return name;
    }
}
