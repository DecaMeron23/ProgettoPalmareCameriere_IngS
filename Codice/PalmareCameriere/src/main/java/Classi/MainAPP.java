package Classi;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import GUI.GUIMain;

public class MainAPP {

	public static void main(String[] args) {
		
		//lista Tavoli la prendiamo da DB;
		final List<Tavolo> lista_tavoli = new ArrayList<Tavolo>();
		
		lista_tavoli.add(new Tavolo(1, 4));
		lista_tavoli.add(new Tavolo(6, 5));
		lista_tavoli.add(new Tavolo(2, 6));
		lista_tavoli.add(new Tavolo(4, 4));
		lista_tavoli.add(new Tavolo(5, 6));
		lista_tavoli.add(new Tavolo(3, 10));
		lista_tavoli.add(new Tavolo(7, 20));
		lista_tavoli.add(new Tavolo(8, 4));
		lista_tavoli.add(new Tavolo(9, 5));
		lista_tavoli.add(new Tavolo(10, 4));
		lista_tavoli.add(new Tavolo(11, 2));
		lista_tavoli.add(new Tavolo(13, 2));
		lista_tavoli.add(new Tavolo(12, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(16, 2));
		lista_tavoli.add(new Tavolo(14, 2));
		lista_tavoli.add(new Tavolo(15, 2));
		lista_tavoli.add(new Tavolo(17, 2));
		lista_tavoli.add(new Tavolo(17, 2));
		
		List<Piatto> listaPiatto1 = new ArrayList<Piatto>();
		
		listaPiatto1.add(new Piatto("Pasta al sugo", 40));
		listaPiatto1.add(new Piatto("tagliatelle al Ragu", 25));
		listaPiatto1.add(new Piatto("Pasta alla carbonara", 25));
		listaPiatto1.add(new Piatto("Lasagne", 25));
		listaPiatto1.add(new Piatto("Pasta al pesto", 25));
		listaPiatto1.add(new Piatto("Spagetti alle Vongole", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		listaPiatto1.add(new Piatto("Ramen", 25));
		
		List<Piatto> listaPiatto2 = new ArrayList<Piatto>();
		
		listaPiatto2.add(new Piatto("Cotoletta e patatine", 470));
		listaPiatto2.add(new Piatto("Prosciutto e Melone", 254));
		listaPiatto2.add(new Piatto("Insalata", 254));
		listaPiatto2.add(new Piatto("Pollo al Limone", 254));
		listaPiatto2.add(new Piatto("Spiedini di carne", 254));
		listaPiatto2.add(new Piatto("Polenta", 254));
		listaPiatto2.add(new Piatto("Coniglio", 254));

		final List<Componente> componenti = new ArrayList<Componente>();
		
		componenti.add(new Componente("Primo", listaPiatto1));
		componenti.add(new Componente("Secondo", listaPiatto2));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain frame = new GUIMain(lista_tavoli , componenti);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
