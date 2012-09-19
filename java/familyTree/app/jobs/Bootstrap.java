/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jobs;

/**
 *
 * @author mohamed
 */
import play.Logger;
import play.jobs.*;
import play.test.Fixtures;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
//         Fixtures.deleteAll();
//         Fixtures.loadModels("initial-data2.yml");
    	
    	if(Person.count() != 0) return;
    	
    	String osName= System.getProperty("os.name");
		Logger.info("Operating system name =>"+ osName);
		
		//Load data from file. yaml files are pain in the a$$. they throw all kind of exceptions
		String dataLocation;
		if(osName.indexOf("Windows") != -1){
			dataLocation = ".\\familyTree\\conf\\test-data-raw.txt";
		}else{
			dataLocation = "./conf/test-data-raw.txt";
		}
		
		File data = new File(dataLocation);
		System.out.println("\n********************************\n"+data.getAbsolutePath()+"\n********************************\n");
		Scanner sc = null;
		try {
			sc = new Scanner(data, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] cols;
		Person p;
		
		DateFormat dateParser = new SimpleDateFormat("yyyy-mm-dd");
		while(sc.hasNextLine()){
			cols = sc.nextLine().split(",",-1);
			Logger.info("Length: "+cols.length);
			for(int i=0; i<cols.length;i++){
				Logger.info("Split Result: "+cols[i]);
			}
			p = new Person();
			p.name = cols[0];
			
			//get The Father
			Long fatherId = -1L;
			try {
				fatherId = Long.parseLong(cols[1]);
			} catch (Exception e) {
				// fatherNotFound
				Logger.info("Could not find father for "+p.name);
			}
			if(fatherId != -1){
				p.father = fatherId;
			}
			
			//DOB
			try{
				p.dateOfBirth = dateParser.parse(cols[2]); 
			}catch(ParseException e){
				
			}
			
			//DOD
			try{
				p.dateOfDeath = dateParser.parse(cols[3]); 
			}catch(ParseException e){
				
			}
			
			//about
			p.about = cols[4];
			
			//picture
			// TODO: grab the picture from string
			
			p.save();
		}
    }

}

