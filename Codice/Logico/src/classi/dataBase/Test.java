package classi.dataBase;

import classi.enumerativi.StatoTavolo;
import classi.tavolo.Tavolo;

public class Test {

	
	public static void main(String[] args) {
		
		DataService.eliminaTavolo(new Tavolo(5, 4, StatoTavolo.LIBERO, null));
		
	}
}
