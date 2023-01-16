package heritage;

import java.time.LocalDateTime;

public class numberofdays {

	public static void main(String[] args) {
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();

		int numDays;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			numDays = 31;
			break;
		case 2:
			if (!(year % 4 == 0)) {
				numDays = 28;
			} else {
				if (year % 400 != 0) {
					numDays = 28;

				} else if (year % 100 != 0) {
					numDays = 29;

				} else {
					numDays = 29;

				}

			}

			break;

		default:
			numDays = 30;
			break;
		}

		System.out.println("the current month has " + numDays + " days");

	}
}


