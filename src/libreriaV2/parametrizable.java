package libreriaV2;

import java.util.ArrayList;
import java.util.List;

public interface parametrizable<T> {
	public final String developer="Andy god";
	public final long id=1750536367;
	public final String copyright="UPS-PA"; 
	
	
	public static String credits() {
		return String.format("Desarrollado por: %s ; I.D: %d ; Copyright: %s",developer,id,copyright);
	}
	public default void listElements(List<T>elements) {
		for(int i =0;i<elements.size();i++) {
			System.out.println((i+1)+".-"+elements.get(i).toString());
		}
	}
	public default double average(double...values) {
		double value=0.0;
		for(double v:values) {
			value+=v;
		}
		return value/values.length;
	}
}
