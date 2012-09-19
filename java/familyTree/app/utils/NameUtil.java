package utils;

import java.util.*;
import models.*;
import java.lang.*;
import play.Logger;

public class NameUtil {

	private static final int DEFAULT_NAME_LENGTH = 3;
	
	public static String getPersonName(Person p){
		return getPersonName(p, DEFAULT_NAME_LENGTH);
	}
	
	public static String getPersonName(Person p, int nameLength){
		StringBuffer sb = new StringBuffer();
		
		sb.append(p.name+" ");
		if(p.father != null && nameLength > 0)
			sb.append(getPersonName((Person)Person.findById(p.father), nameLength--));
		
		return sb.toString();
	}
}