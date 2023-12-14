package classi.dataBase;

import classi.tavolo.Tavolo;

public class Test {

	public static void main(String[] args) {
		DataSerivce dataSerivce = new DataSerivce();
		
		dataSerivce.inserisciTavolo(new Tavolo(1, 2));
		dataSerivce.inserisciTavolo(new Tavolo(6, 7));
		dataSerivce.inserisciTavolo(new Tavolo(8, 1));
	}
}
