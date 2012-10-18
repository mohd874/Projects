package sketch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Sketch {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		String fileLocation = "src\\project\\euler\\Problem18.txt";
		File file = new File(fileLocation);
		
		BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
		String line = br.readLine();
		
		String fileContent = readFile(fileLocation);
		
		StringTokenizer st = new StringTokenizer(fileContent, " ");
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			if(token.charAt(0) == '\n'){
				System.out.println("break");
			}
			System.out.print(token+"-");
		}
		
		char[] chars = fileContent.toCharArray();
		System.out.println("--------------");
		System.out.println("--   chars   --");
		System.out.println("--------------");
		System.out.println();
		System.out.println("--------------");
		System.out.println("Size: "+chars.length);
		System.out.println("--------------");
		
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(c == '\n'){
				System.out.println("break");
			}
			System.out.print(c+"-");
		}
//		int count = 1;
//		int[] temp;
//		while(line != null){
//			line = br.readLine();
//			temp = new int[count];
//
//			
//		}
		
		
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
