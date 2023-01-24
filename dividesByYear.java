package heritage;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Scanner;

public class dividesByYear {

	public static void main(String[] args) throws IOException {
		LocalDate today = LocalDate.now();
		LocalDate before10 = today.minusYears(10);
		LocalDate before9 = today.minusYears(9);


		File file = new File("data.csv");
		Scanner listFile = new Scanner(file);
		
		FileWriter expired = new FileWriter("expired.txt");
		FileWriter renew = new FileWriter("renew.txt");
		
		while (listFile.hasNextLine()) {
			String data = listFile.nextLine().trim();
			String[] data1 = data.split(",");
			String[] dateComp = data1[2].split("-");	
			LocalDate lastDate = LocalDate.of(Integer.parseInt(dateComp[0]), 
					Integer.parseInt(dateComp[1]), Integer.parseInt(dateComp[2]));
			if(lastDate.isBefore(before10)) {
				
				expired.write(data1[0]+", "+data1[1]+", "+data1[2]+"\n");
			}else if(lastDate.isBefore(before9)){
				
				renew.write(data1[0]+", "+data1[1]+", "+data1[2]+"\n");
			}

		}
		listFile.close();
		expired.close();
		renew.close();

	}

}
