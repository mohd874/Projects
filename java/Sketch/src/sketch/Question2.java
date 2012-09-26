/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bader 9009
 */
public class Question2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static String readString(String msg){
        System.out.println(msg);
        try {
            return br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Question2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static int readInteger(String msg){
        try {
            return Integer.parseInt(readString(msg));
        } catch (Exception ex) {
            Logger.getLogger(Question2.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
           
        String name1=readString("Enter Student Name 1= ");
        String name2=readString("Enter Student Name 2= ");
        String name3=readString("Enter Student Name 3= ");
        String name4=readString("Enter Student Name 4= ");
        String name5=readString("Enter Student Name 5= ");
        
        int project1=readInteger("Enter Project Grade 1= ");
        int project2=readInteger("Enter Project Grade 2= ");
        int project3=readInteger("Enter Project Grade 3= ");
        int project4=readInteger("Enter Project Grade 4= ");
        int project5=readInteger("Enter Project Grade 5= ");
        
        int exam1=readInteger("Enter Exam Grade 1= ");
        int exam2=readInteger("Enter Exam Grade 2= ");
        int exam3=readInteger("Enter Exam Grade 3= ");
        int exam4=readInteger("Enter Exam Grade 4= ");
        int exam5=readInteger("Enter Exam Grade 5= ");
        
        double score1=project1*0.4+exam1*0.6;
        double score2=project2*0.4+exam2*0.6;
        double score3=project3*0.4+exam3*0.6;
        double score4=project4*0.4+exam4*0.6;
        double score5=project5*0.4+exam5*0.6;
        double num_F=0;
        double num_D=0;
        double num_Cm=0;
        double num_C=0;
        double num_B=0;
        double num_Bp=0;
        double num_Am=0;
        double num_A=0;
        String grade1="A";
        String grade2="A";
        String grade3="A";
        String grade4="A";
        String grade5="A";
        
        
         if (score1>=0&&score1<=59) 
            {
            grade1=("F");
            num_F=num_F+1;
            }
    else
         if (score1>=60&&score1<=64)
         {
         grade1=("D");
         num_D=num_D+1;
         }
    else
         if (score1>=65&&score1<=69)
         {
         grade1=("C-");
         num_Cm=num_Cm+1;
         }      
    else  
         if (score1>=70&&score1<=74)
         {
         grade1=("C+");
         num_C=num_C+1;
         }
    else  
         if (score1>=75&&score1<=79)
         {
         grade1=("B");
         num_B=num_B+1;
         }
    else  
         if (score1>=80&&score1<=84)
         {
         grade1=("B+");
         num_Bp=num_Bp+1;
         }
    else  
         if (score1>=85&&score1<=89)
         {
         grade1=("A-");
         num_Am=num_Am+1;
         }
    else  
         if (score1>=90&&score1<=100)
         {
         grade1=("A");
         num_A=num_A+1;
         }
         
         //grade 2
         if (score2>=0&&score2<=59) 
            {
            grade2=("F");
            num_F=num_F+1;
            }
    else
         if (score2>=60&&score2<=64)
         {
         grade2=("D");
         num_D=num_D+1;
         }
    else
         if (score2>=65&&score2<=69)
         {
         grade2=("C-");
         num_Cm=num_Cm+1;
         }      
    else  
         if (score2>=70&&score2<=74)
         {
         grade2=("C+");
         num_C=num_C+1;
         }
    else  
         if (score2>=75&&score2<=79)
         {
         grade2=("B");
         num_B=num_B+1;
         }
    else  
         if (score2>=80&&score2<=84)
         {
         grade2=("B+");
         num_Bp=num_Bp+1;
         }
    else  
         if (score2>=85&&score2<=89)
         {
         grade2=("A-");
         num_Am=num_Am+1;
         }
    else  
         if (score2>=90&&score2<=100)
         {
         grade2=("A");
         num_A=num_A+1;
         }
         //grade3
         if (score3>=0&&score3<=59) 
            {
            grade3=("F");
            num_F=num_F+1;
            }
    else
         if (score3>=60&&score3<=64)
         {
         grade3=("D");
         num_D=num_D+1;
         }
    else
         if (score3>=65&&score3<=69)
         {
         grade3=("C-");
         num_Cm=num_Cm+1;
         }      
    else  
         if (score3>=70&&score3<=74)
         {
         grade3=("C+");
         num_C=num_C+1;
         }
    else  
         if (score3>=75&&score3<=79)
         {
         grade3=("B");
         num_B=num_B+1;
         }
    else  
         if (score3>=80&&score3<=84)
         {
         grade3=("B+");
         num_Bp=num_Bp+1;
         }
    else  
         if (score3>=85&&score3<=89)
         {
         grade3=("A-");
         num_Am=num_Am+1;
         }
    else  
         if (score3>=90&&score3<=100)
         {
         grade3=("A");
         num_A=num_A+1;
         }
         //grade4
         if (score4>=0&&score4<=59) 
            {
            grade4=("F");
            num_F=num_F+1;
            }
    else
         if (score4>=60&&score4<=64)
         {
         grade4=("D");
         num_D=num_D+1;
         }
    else
         if (score4>=65&&score4<=69)
         {
         grade4=("C-");
         num_Cm=num_Cm+1;
         }      
    else  
         if (score4>=70&&score4<=74)
         {
         grade4=("C+");
         num_C=num_C+1;
         }
    else  
         if (score4>=75&&score4<=79)
         {
         grade4=("B");
         num_B=num_B+1;
         }
    else  
         if (score4>=80&&score4<=84)
         {
         grade4=("B+");
         num_Bp=num_Bp+1;
         }
    else  
         if (score4>=85&&score4<=89)
         {
         grade4=("A-");
         num_Am=num_Am+1;
         }
    else  
         if (score4>=90&&score4<=100)
         {
         grade4=("A");
         num_A=num_A+1;
         }
         //grade5
          if (score5>=0&&score5<=59) 
            {
            grade5=("F");
            num_F=num_F+1;
            }
    else
         if (score5>=60&&score5<=64)
         {
         grade5=("D");
         num_D=num_D+1;
         }
    else
         if (score5>=65&&score5<=69)
         {
         grade5=("C-");
         num_Cm=num_Cm+1;
         }      
    else  
         if (score5>=70&&score5<=74)
         {
         grade5=("C+");
         num_C=num_C+1;
         }
    else  
         if (score5>=75&&score5<=79)
         {
         grade5=("B");
         num_B=num_B+1;
         }
    else  
         if (score5>=80&&score5<=84)
         {
         grade5=("B+");
         num_Bp=num_Bp+1;
         }
    else  
         if (score5>=85&&score5<=89)
         {
         grade5=("A-");
         num_Am=num_Am+1;
         }
    else  
         if (score5>=90&&score5<=100)
         {
         grade5=("A");
         num_A=num_A+1;
         }
         
         System.out.println("-----------------------------------------------");
         System.out.println("CLASS STATISTICS");
         System.out.println("-----------------------------------------------");
         System.out.println("Name"+"    "+"Project"+"    "+"(40%)"+"    "+"Exam"+"    "+"(60%0"+"    "+"Score"+ "    "+"Grade");
         System.out.println(name1+"    "+project1+"    "+project1*0.4+"    "+exam1+"  "+exam1*0.6+"    "+score1+ "    "+grade1);
         System.out.println(name2+"    "+project2+"    "+project2*0.4+"    "+exam2+"  "+exam2*0.6+"    "+score2+ "    "+grade2);
         System.out.println(name3+"    "+project3+"    "+project3*0.4+"    "+exam3+"  "+exam3*0.6+"    "+score3+ "    "+grade3);
         System.out.println(name4+"    "+project4+"    "+project4*0.4+"    "+exam4+"  "+exam4*0.6+"    "+score4+ "    "+grade4);
         System.out.println(name5+"    "+project5+"    "+project5*0.4+"    "+exam5+"  "+exam5*0.6+"    "+score5+ "    "+grade5);
         System.out.println("-----------------------------------------------");
         System.out.println("No of students with F grade:"+num_F);
         System.out.println("No of students with D grade:"+num_D);
         System.out.println("No of students with C- grade:"+num_Cm);
         System.out.println("No of students with C+ grade:"+num_C);
         System.out.println("No of students with B grade:"+num_B);
         System.out.println("No of students with B+ grade:"+num_Bp);
         System.out.println("No of students with A- grade:"+num_Am);
         System.out.println("No of students with A grade:"+num_A);
         System.out.println("-----------------------------------------------");
         
         double persentage=((num_A+num_Am+num_B+num_Bp+num_C+num_Cm+num_D)/(num_A+num_Am
+num_B+num_Bp+num_C+num_Cm+num_D+num_F))*100;
         
         System.out.println("Persentage of students with passing grade:"+persentage);
         System.out.println("Persentage of students with failing grade:"+(100-persentage));
         
         double highest_score=0;
         if (score1>highest_score)
         {
         highest_score=score1;
         }
    else if (score2>highest_score)
         {
         highest_score=score2;
         }
    else if (score3>highest_score)
         {
         highest_score=score3;
         }
   else if (score4>highest_score)
         {
         highest_score=score4;
         }     
  else if (score5>highest_score)
         {
         highest_score=score5;
         }
         double lowest_score=0;
         if (score1<lowest_score)
         {
         lowest_score=score1;
         }
  else    if (score2<lowest_score)
         {
         lowest_score=score2;
         }
  else    if (score3<lowest_score)
         {
         lowest_score=score3;
         }     
  else    if (score4<lowest_score)
         {
         lowest_score=score4;
         }       
  else    if (score5<lowest_score)
         {
         lowest_score=score5;
         }       
         double average;
         average=(score1+score2+score3+score4+score5)/5;
         
         System.out.println("Average score: "+average);

    }
    
}
