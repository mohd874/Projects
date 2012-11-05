package sketch;

import java.io.IOException;

public class Sketch {

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("C:\\Program~1\\sbt\\sbt sbt-version");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
