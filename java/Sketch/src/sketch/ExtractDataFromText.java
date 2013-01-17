package sketch;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractDataFromText {

	//The sms message
	private final String smsTxt = "A trxn on your ADIB VISA Covered Card 4713**5076 at V Star Multimedia LLC in Dubai, United Arab Emi on 16/01/13 08:52 for AED, 250.00 is approved.";
	//							   A trxn on your ADIB VISA Covered Card [\d*] at [\w*] in [\w*] on [\w*] for [\w], [\d*] is approved
	//data to be extracted
	/*
	 * Credit Card
	 * How used it
	 * location
	 * Date and Time
	 * Currency
	 * Money Amount
	 */
	private String cardNo = "";
	private String usedBy = "";
	private String location = "";
	private Date date = null;
	private String currency = "";
	private double moneyAmount = 0;
	
	private void extractInfo(){
		String [] regex = {
				"\\d{4}\\S+\\d{4}", // Card
				" at .* in ",     // used by
				" in .* on ",     // Location
				" on \\d{2}/\\d{2}/\\d{2} \\d{2}:\\d{2} for",     // date
				" for .*,",     // currency
				", \\d*\\S\\d* is approved",     // money
		};
		Pattern p;
		for(String reg : regex){
			p = Pattern.compile(reg);
			Matcher m = p.matcher(smsTxt);

			printFindings(m);
		}
	}

	private void printFindings(Matcher m) {
		if(m.find()){
			System.out.println("Found!");
			System.out.println(m.group());
		}else{
			System.out.println("Not Yet!");
		}
	}
	
	public static final String EXAMPLE_TEST = "This is my small example string which I'm going to use for pattern matching.";

	  public static void main(String[] args) {
	    new ExtractDataFromText().extractInfo();
	  }

}
