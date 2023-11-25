package classi.dataBase;

public class Test {

	public static void main(String[] args) {
		DataSerivce dataSerivce = new DataSerivce();
		
		dataSerivce.cancellaTavolo(1);
		dataSerivce.cancellaTavolo(2);
		dataSerivce.cancellaTavolo(3);
		dataSerivce.inserisciTavolo(2, 4);
		dataSerivce.inserisciTavolo(3, 6);
		dataSerivce.inserisciTavolo(1, 4);
	}
}
