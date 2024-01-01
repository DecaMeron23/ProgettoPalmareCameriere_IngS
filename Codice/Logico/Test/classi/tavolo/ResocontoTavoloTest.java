package classi.tavolo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import classi.dataBase.DataService;
import classi.menu.Piatto;
import classi.ordine.Ordine;
import classi.ordine.PiattoOrdinato;

class ResocontoTavoloTest {

	@Test
	void testGetPrezzoTotale() {
		
		int numeroCoperti = 3;
		
		List<PiattoOrdinato> piattiOridine1 = new ArrayList<PiattoOrdinato>();
		piattiOridine1.add(new PiattoOrdinato(new Piatto("", 1), 1));
		piattiOridine1.add(new PiattoOrdinato(new Piatto("", 2), 2));
		piattiOridine1.add(new PiattoOrdinato(new Piatto("", 3), 2));
		piattiOridine1.add(new PiattoOrdinato(new Piatto("", 4), 2));
		
		List<PiattoOrdinato> piattiOridine2 = new ArrayList<PiattoOrdinato>();
		piattiOridine2.add(new PiattoOrdinato(new Piatto("", 5), 1));
		piattiOridine2.add(new PiattoOrdinato(new Piatto("", 4), 2));
		piattiOridine2.add(new PiattoOrdinato(new Piatto("", 3), 2));
		piattiOridine2.add(new PiattoOrdinato(new Piatto("", 2), 2));
		
		List<PiattoOrdinato> piattiOridine3 = new ArrayList<PiattoOrdinato>();
		piattiOridine3.add(new PiattoOrdinato(new Piatto("", 1), 1));
		piattiOridine3.add(new PiattoOrdinato(new Piatto("", 2), 2));
		piattiOridine3.add(new PiattoOrdinato(new Piatto("", 3), 2));
		piattiOridine3.add(new PiattoOrdinato(new Piatto("", 4), 2));
		
		Ordine ordine1 = new Ordine(piattiOridine1, 0);
		Ordine ordine2 = new Ordine(piattiOridine2, 0);
		Ordine ordine3 = new Ordine(piattiOridine3, 0);
		
		
		List<Ordine> ordini = new ArrayList<Ordine>();
		
		ordini.add(ordine1);
		ordini.add(ordine2);
		ordini.add(ordine3);
		
		ResocontoTavolo resocontoTavolo = new ResocontoTavolo(numeroCoperti , ordini , 0);
		
		double prezzo = resocontoTavolo.getPrezzoTotale();
		
		
		double prezzoCoperto = DataService.getPrezzoCoperto() * numeroCoperti;
		double prezzoAspettato = prezzoCoperto + 1 + 5 + 1 + (2+3+4+4+3+2+2+3+4) * 2;
		
		assertEquals(prezzoAspettato, prezzo);
		
	}

}
