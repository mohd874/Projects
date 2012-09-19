package utils;


import java.util.*;
import models.*;
import java.lang.*;

public class GoogleChartsUtils {

	/*

	This method will prepare a JSON string that will construct a datatable for the Family Tree Org Chart view

	*/
	public static String createFamilyTreeDataTable(Person person){
		StringBuffer sb = new StringBuffer();

		//prepare columns
		sb.append("{\"cols\":[");

		sb.append("{\"id\":\"\",\"label\":\"Name\",\"pattern\":\"\",\"type\":\"string\"},"); //add col ID
		sb.append("{\"id\":\"\",\"label\":\"Parent\",\"pattern\":\"\",\"type\":\"string\"},"); //add col parent
		sb.append("{\"id\":\"\",\"label\":\"Info\",\"pattern\":\"\",\"type\":\"string\"},"); //add col tooltip
		sb.append("],");

		//prepare rows
		sb.append("\"rows\":[");
		//sb.append("{\"c\":[{\"v\":\""+person.name+"\",\"f\":null},{\"v\":null,\"f\":null},{\"v\":\"Birth Date: TO BE IMPLEMENTED!!\",\"f\":null}]},");
		//add all children
		addChildrenToDataTable(person,sb);
		sb.append("],\"p\":null}");

		return sb.toString();
	}

	private static void addChildrenToDataTable(Person person, StringBuffer sb){
		sb.append("{\"c\":[{\"v\":\""+person.name+"\",\"f\":null},{\"v\":");
		
		if(person.father != null){
			//sb.append("\""+Person.findById(person.father).name+"\"");
			sb.append("\" Father Name \"");
		}else{
			sb.append("null");
		}
		
		sb.append(",\"f\":null},{\"v\":\"Birth Date: TO BE IMPLEMENTED!!\",\"f\":null}]},");
		
		for(Person p : person.getChildren()){
			sb.append("{\"c\":[{\"v\":\""+p.name+"\",\"f\":null},{\"v\":\" Father Name \",\"f\":null},{\"v\":\"Birth Date: TO BE IMPLEMENTED!!\",\"f\":null}]},");
			for(Person c : p.getChildren()){
				addChildrenToDataTable(c,sb);
			}
		}
	}
}
