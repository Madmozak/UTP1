package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList {

	Collection<T> kolekcja;
	List<T> xlist = new ArrayList<>();
	List<T> listTMP;

	public XList() {
		kolekcja = new ArrayList<>();
	}

	public XList(T... arrays) {
   
		kolekcja = Arrays.asList(arrays);

	}	

	public XList(T[]... arrays) {
    
		kolekcja = new ArrayList<>();
		for (T[] arr : arrays)
			for (T t : arr) {
				kolekcja.add(t);
			}
	}

	public XList(Collection<T> col) {
    
		kolekcja = new ArrayList<>();
		kolekcja.addAll(col);
	}


	@Override
	public String toString() {
		return kolekcja.toString();
	}

	public static <T> XList<T> of(T... arrays) {

		XList<T> tmp = new XList<>(arrays);
		return tmp;
	}

	public static <T> XList<T> of(T[]... arrays) {

		XList<T> tmp = new XList(arrays);
		return tmp;
	}

	public static <T> XList<T> of(Collection<T> col) {
   
		XList<T> tmp = new XList<>(col);
		return tmp;
	}

	public static <T> XList<T> charsOf(T str) {

		Object tab[] = ((String) str).split("");
		XList<T> tmp = new XList<>();
		for (int i = 0; i < tab.length; i++) {
			tmp.kolekcja.add((T) tab[i]);
		}
		return tmp;
	}

	public static <T> XList<T> tokensOf(T str) {

		Object tab[] = ((String) str).split("\\s");
		XList<T> tmp = new XList<>();
		for (int i = 0; i < tab.length; i++) {
			tmp.kolekcja.add((T) tab[i]);
		}
		return tmp;
	}


	public static <T> XList<T> tokensOf(T... str) {

		String sep = ((String) str[1]);
		XList<T> tmp = new XList<>();
		for (T t : str) {
			Object tab[] = ((String) t).split(sep);
			for (Object obj : tab)
				tmp.kolekcja.add((T) obj);
		}
		return tmp;
	}

	public XList<T> union(Collection<T> col) {
		XList<T> returnList = new XList<>();
		if (col instanceof XList) {
			returnList.kolekcja.addAll(this.kolekcja);
			returnList.kolekcja.addAll(((XList) col).kolekcja);
		} else {
			returnList.kolekcja.addAll(this.kolekcja);
			returnList.kolekcja.addAll(col);
		}
		return returnList;
	}

	public XList<T> union(T... arrays) {

		XList<T> returnList = new XList<>();
		returnList.kolekcja.addAll(this.kolekcja);

		for (T t : arrays)
			returnList.kolekcja.add(t);

		return returnList;

	}

	@Override
	public boolean add(Object obj) {
		kolekcja.add((T) obj);
		return true;
	}

	public Collection<T> getColl() {
		return kolekcja;
	}

	public XList<T> diff(Collection<T> col) {
		Collection<T> cpCol = new ArrayList<>();
		cpCol.addAll(this.kolekcja);
		
		if (col instanceof XList) {
			cpCol.removeAll(((XList) col).getColl());
		} else {
			cpCol.removeAll(col);
		}

	    XList<T> returnList = new XList<>();
	    returnList.kolekcja = cpCol;
	    return returnList;
	}

	public XList<T> unique() {
	    XList<T> returnList = new XList<>();
	    returnList.kolekcja = this.kolekcja.stream().distinct().collect(Collectors.toList());
	    return returnList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<List<T>> combine() {
	
	    List<T> listIterate = new ArrayList<>(this.kolekcja);
	    List<List<T>> lista2 = new ArrayList();
	
	    for (int i = 0; i < listIterate.size(); i++){
	        List<T> tmp = Arrays.asList(listIterate.get(i));
	        lista2.add(tmp);
	    }
	
	    return computeCombinationsOriginal(lista2);
	}
	
	
	public static <T> List<List<T>> computeCombinationsOriginal(List<List<T>> lists) {
	    List<List<T>> combinations = new ArrayList<>();
	    for (List<T> list : lists) {
	        List<List<T>> extraColumnCombinations = new ArrayList<>();
	        for (T element : list) {
	            if (combinations.isEmpty()) {
	                extraColumnCombinations.add(Arrays.asList(element));
	            } else {
	                for (List<T> productList : combinations) {
	                    List<T> newProductList = new ArrayList<>(productList);
	                    newProductList.add(element);
	                    extraColumnCombinations.add(newProductList);
	                }
	            }
	        }
	        combinations = extraColumnCombinations;
	    }
	    return combinations;
	}
	
	
	public static <T> List<List<T>> appendElements(List<List<T>> combinations, List<T> extraElements) {
	    return combinations.stream().flatMap(oldCombination
	            -> extraElements.stream().map(extra -> {
	        List<T> combinationWithExtra = new ArrayList<>(oldCombination);
	        combinationWithExtra.add(extra);
	        return combinationWithExtra;
	        
	    })).collect(Collectors.toList());
	}
	
	
	private List<String> combineTwoLists(List<String> list1, List<String> list2) {
	    List<String> result = new ArrayList<String>();
	    StringBuilder sb = new StringBuilder();
	    for (String s1 : list1) {
	        for (String s2 : list2) {
	            sb.setLength(0);
	            sb.append(s1).append(' ').append(s2);
	            result.add(sb.toString());
	        }
	    }
	    return result;
	}
	
	public void forEachWithIndex(BiConsumer<T, Integer> bi) {
	    listTMP = new ArrayList<>();
	    listTMP.addAll(this.kolekcja);
	
	    for (int i = 0; i < listTMP.size(); i++) {
	        bi.accept(listTMP.get(i), i);
	
	    }
	
	    this.kolekcja = xlist;
	
	}
	
	@Override
	public Object set(int index, Object obj) {
	    
	    xlist.add(index, (T) obj);
	    return obj;
	}
	
	@Override
	public boolean remove(Object obj) {
	
	    this.kolekcja.remove(obj);
	    listTMP.remove(obj);
	    return true;
		}
	
	 public static List<List<Object>> combine( List<Object> l1, List<Object> l2, List<Object>... moreLists ) {

	        List<List<Object>> cp = new ArrayList<>();

	        for ( Object o1 : l1 ) {
	            for ( Object o2 : l2 ) {
	                List<Object> c = new ArrayList<>();
	                c.add( o1 );
	                c.add( o2 );
	                cp.add( c );
	            }
	        }

	        for ( List<Object> other : moreLists ) {

	            List<List<Object>> newCp = new ArrayList<>();

	            for ( List<Object> ocp : cp ) {
	                for ( Object oo : other ) {
	                    List<Object> newList = new ArrayList<>();
	                    newList.addAll( ocp );
	                    newList.add( oo );
	                    newCp.add( newList );
	                }
	            }

	            cp = newCp;

	        }

	        return cp;

	    }


}