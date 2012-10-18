package project.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sun.org.mozilla.javascript.internal.ast.ForInLoop;

public class Problem18 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		solution1();
//		solution2();
		String fileLocation = "src\\project\\euler\\Problem18.txt";
		if(args.length > 0){
			fileLocation = args[0];
		}
		
		String fileContent = readFile(fileLocation);
		
		char[] chars = fileContent.toCharArray();
		System.out.println("--------------");
		System.out.println("--   chars   --");
		System.out.println("--------------");
		System.out.println();
		System.out.println("--------------");
		System.out.println("Size: "+chars.length);
		System.out.println("--------------");

		int[] current = {0};
		int[] old = {0};
		
		int rowCount = 1;
		int num;
		int currentIndex = 0;
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(c == ' '){
				continue;
			}
			if(c == '\n'){
				old = current;
				currentIndex = 0;
				rowCount++;
				currentIndex = 0;
				current = new int[rowCount];
				continue;
			}
			
			num = Integer.parseInt(Character.getNumericValue(c)+""+Character.getNumericValue(chars[i+1]));
			i++;
			
			int r1 = 0, r2 = 0;
			if(currentIndex < old.length){
				 r1 = num + old[currentIndex];
			}
			if(currentIndex > 0){
				r2 = num + old[currentIndex-1];
			}
			
			if(r1 > r2){
				current[currentIndex] = r1;
			}else{
				current[currentIndex] = r2;
			}
			
			currentIndex++;
		}
		System.out.println("old");
		for (int i = 0; i < old.length; i++) {
			int j = old[i];
			System.out.print(j+"-");
		}
		
		System.out.println();
		System.out.println("current");
		for (int i = 0; i < current.length; i++) {
			int j = current[i];
			System.out.print(j+"-");
		}
		
		System.out.println("Getting biggest value");
		int res = 0;
		for (int i = 0; i < old.length; i++) {
			int j = old[i];
			if(res < j){
				res = j;
			}
		}
		System.out.println("Result: "+res);
		
	}

	private static void solution2() throws FileNotFoundException, IOException {
		String fileLocation = "src\\project\\euler\\Problem18.txt";
		
		File file = new File(fileLocation);
		
		BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
		
		String line = br.readLine();
		
		List<int []> res = new ArrayList<int []>();
		int count = 1;
		while(line != null){
			line = br.readLine();
			
			int[] nums = new int[count];
			res.add(nums);
			
			int numsIndex = 0;
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if(c != ' ' && c != '\n'){
					int n = Integer.valueOf(c);
					
					nums[numsIndex] = n + res.get(res.size()-2)[numsIndex];
				}else{
					
				}
			}
		}
	}

	private static void solution1() {
		String fileLocation = "src\\project\\euler\\Problem18.txt";
		String numbers = "";
		try {
			numbers = readFile(fileLocation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		int countLines = 0;
		countLines = countLines(numbers, countLines);
		
		int[][] res = new int[countLines][];
		
		int i = 0;
		int j = 0;
		int curNum = 0;
		while (i != countLines){
			res[i] = new int[i+1];
			
			char c = numbers.charAt(j);
			
			while(c != '\n'){
				curNum = Integer.valueOf(c);
				
				if(i > 1){
					if(j > 0){
						res[i] = res[i-1];
					}
					
				}
				
				if(numbers.charAt(j+1) == '\n'){
					j++;
				}else{
					j = j+2;
				}
				c = numbers.charAt(j);
			}
		}
	}

	private static int countLines(String numbers, int countLines) {
		for (int i = 0; i < numbers.length(); i++) {
			char c = numbers.charAt(i);
			System.out.print(c);
			if(c == '\n'){
				countLines++;
			}
		}
		return countLines;
	}

	private static String readFile(String fileLocation) throws FileNotFoundException, IOException {
		File file = new File(fileLocation);
		
		BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
		String line = br.readLine();
		StringBuilder sb = new StringBuilder();
		while(line != null){
			sb.append(line+'\n');
			line = br.readLine();
		}
		
		return sb.toString();
	}

}
