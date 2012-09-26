package com.htdp.exercise;

public class Exercise311 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float ticketPrice = 0;
		float profit = 0;
		
		ticketPrice = 5f;
		while(ticketPrice != 2){
			System.out.println("ticket price: "+ticketPrice);
			profit = calcRevenue(ticketPrice) - calcCost(ticketPrice);
			System.out.println("Profit: "+profit);
			ticketPrice -= 1;
		}
	}

	private static float calcCost(float ticketPrice) {
		float cost = 180 + calcAttendees(ticketPrice)*0.04f;
		System.out.println("Cost: "+cost);
		return 180 + calcAttendees(ticketPrice)*0.04f;
	}

	private static float calcRevenue(float ticketPrice) {
		float revenue = ticketPrice * calcAttendees(ticketPrice);
		System.out.println("revenue: "+revenue);
		return revenue;
	}

	private static float calcAttendees(float ticketPrice) {
		float attendees = (120 + (15/0.1f)*(5-ticketPrice));
		System.out.println("attendees: "+attendees);
		return attendees;
	}

}
