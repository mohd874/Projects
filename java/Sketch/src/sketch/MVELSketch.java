/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketch;

//import org.mvel2.MVEL;



/**
 *
 * @author m.alhabshi
 */
public class MVELSketch {

    public static void main(String[] args) {
        Person p = new Person();
        p.setName("Mr. foo");
        
        System.out.println(System.nanoTime());
        
//        System.out.println(MVEL.eval("name == 'Mr. foo'", p));
//        System.out.println(MVEL.eval("name", p));
        
        System.out.println(System.nanoTime());
    }
}

class Person {
    private String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }
}