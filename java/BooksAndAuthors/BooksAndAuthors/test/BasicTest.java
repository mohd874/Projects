import org.junit.*;
import java.util.*;
import javax.persistence.*;
import play.test.*;
import play.db.jpa.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    @Test
    public void queryTest() {
        Book b1 = new Book("title1","271927","Cool Book").save();
        Book b2 = new Book("Booking","215436","For Airlines").save();
        Book b3 = new Book("Java=Coffee","363141","Not For Programmers").save();
        Book b4 = new Book("Java Paradise","362541","About Indonesia").save();
        Book b5 = new Book("Hello World","111452","All about Hello World Examples").save();
        Book b6 = new Book("Cooking","552236","Just for Cooking").save();

	Author a1 = new Author("Mohamed", "Omar").save();
	Author a2 = new Author("Ahmed", "Salem").save();
	Author a3 = new Author("Ahmed", "Saleh").save();
	Author a4 = new Author("Ali", "Mohamed").save();
	
	a1.addBook(b1);
	a1.addBook(b2);
	a2.addBook(b3);
	a3.addBook(b4);
	a4.addBook(b5);
	a4.addBook(b6);
	
	EntityManager em = JPA.em();
	String qString = "Moh";
	List results = em.createQuery("select * from * where * like '%"+qString+"%'").getResultList();
	for(Object o : results){
		System.out.println(o);
	}
	assertNotNull(results);
    }

}
