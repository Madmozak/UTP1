/**
 *
 *  @author Grzechnik Mariusz S14456
 *
 */

package zad2;

import java.util.Comparator;

public class Purchase {
	
	String id;
	String nazwisko;
	String towar;
	double ilosc;
	double cena;
	
	public Purchase(String id, String nazwisko, String towar, double ilosc, double cena ) {
		this.id=id;
		this.nazwisko=nazwisko;
		this.towar=towar;
		this.ilosc=ilosc;
		this.cena=cena;
	}
	
	public static Comparator<Purchase> nazwiskaComparator = new Comparator<Purchase>() {

		@Override
		public int compare(Purchase n1, Purchase n2) {
			String nazwisko1 = n1.getNazwisko();
			String nazwisko2 = n2.getNazwisko();
			return nazwisko1.compareTo(nazwisko2);
		}
		
	};
	
	public static Comparator<Purchase> kosztyComparator = new Comparator<Purchase>() {

		@Override
		public int compare(Purchase k1, Purchase k2) {
			Double koszt1 = k1.getCena() * k1.getIlosc();
			Double koszt2 = k2.getCena() * k2.getIlosc();
			return koszt2.compareTo(koszt1);
		}
		
	};
	
	public String getId() {
		return id;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}
	public String getTowar() {
		return towar;
	}
	public Double getIlosc() {
		return ilosc;
	}
	public Double getCena() {
		return cena;
	}
	
	public Double getKoszt() {
		return cena*ilosc;
	}
	@Override
	public String toString() {
		return id + ";"+ 
				nazwisko + ";" + towar + ";" + Double.toString(ilosc) + ";" + Double.toString(cena);

	}
}
