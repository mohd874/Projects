package utils;


import java.util.*;
import models.*;
import java.lang.*;
import play.Logger;

public class ChartsUtils {

	private static void Log(String msg){
		Logger.info(msg);
	}
	
	public static String createJsonFamilyTree(Person person){
		StringBuffer sb = new StringBuffer();
			
		Log("getting "+person.name+"\'s data.");
		
		sb.append("{");
		sb.append("id: \""+person.id+"\",");
		sb.append("name: \""+person.name+"\",");
		sb.append("data: {},");
		sb.append("children: [");
		
		List<Person> children = person.getChildren();
		Log("getting "+person.name+"\'s children data. There are "+children.size()+" child(s).");
		if(children != null && children.size() > 0){
			for(int i=0;i<children.size();i++){
				Person child = children.get(i);
				sb.append(createJsonFamilyTree(child));
			}
		}
		
		sb.append("]}");
		if(person.father != null)
			sb.append(",");
		
		return sb.toString();
	}
}
