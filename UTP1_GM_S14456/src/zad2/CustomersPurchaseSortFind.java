/**
 *
  *  @author Grzechnik Mariusz S14456
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CustomersPurchaseSortFind {

	ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();
	
	public void readFile(String fname) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = br.readLine();
			while(line != null) {
				String[] sLine = line.split(";");
				Purchase purchase = new Purchase(sLine[0], sLine[1], sLine[2], Double.parseDouble(sLine[3]), Double.parseDouble(sLine[4]));
				purchaseList.add(purchase);
				
				line=br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Cos poszlo nie tak...");
			e.printStackTrace();
			
		}
		
		for(int i = 0; i < purchaseList.size(); i++) {
			System.out.println(purchaseList.get(i).toString());
		}
	}

	public void showSortedBy(String string) {
		
		if(string.equals("Nazwiska")) {
			Collections.sort(purchaseList, Purchase.nazwiskaComparator);
			System.out.println();
			System.out.println("Nazwiska");
			for(Purchase pur : purchaseList) {
				System.out.println(pur);
			}
			
		} else if(string.equals("Koszty")){
			Collections.sort(purchaseList, Purchase.kosztyComparator);
			System.out.println();
			System.out.println("Koszty");
			for(Purchase pur : purchaseList) {
				System.out.println(pur + "(koszt: " + pur.getKoszt() + ")");
			}
		}
	}

	public void showPurchaseFor(String id) {
		System.out.println();
		System.out.println("Klient " + id);
		for(Purchase pur : purchaseList) {
			if(pur.id.equals(id))
				System.out.println(pur);
		}
		
	}
}
