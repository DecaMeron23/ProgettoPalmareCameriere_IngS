package logico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import classi.dataBase.DataService;
import classi.enumerativi.StatoTavolo;
import classi.menu.Componente;
import classi.menu.Piatto;
import classi.ordine.Ordine;
import classi.ordine.PiattoOrdinato;
import classi.tavolo.Tavolo;

class LogicoTest {

	@Test
	void testOccupaTavolo() {
		Tavolo tavoloTest = new Tavolo(999999999, 5, StatoTavolo.LIBERO);
		DataService.inserisciTavolo(tavoloTest);

		int numeroCoperti1 = 2;
		int numeroCoperti2 = 8;
		int numeroCoperti3 = 0;

		try {
			Logico.occupaTavolo(tavoloTest, numeroCoperti1);
		} catch (Exception e) {
		}

		Tavolo tavoloTest2 = DataService.getTavolo(tavoloTest);
		assertTrue(tavoloTest2.getStato().equals(StatoTavolo.OCCUPATO));

		DataService.aggiornaStatoTavolo(tavoloTest, StatoTavolo.LIBERO);

		assertThrows(Exception.class, () -> {
			Logico.occupaTavolo(tavoloTest, numeroCoperti2);
		});

		DataService.aggiornaStatoTavolo(tavoloTest, StatoTavolo.LIBERO);

		assertThrows(Exception.class, () -> {
			Logico.occupaTavolo(tavoloTest, numeroCoperti3);
		});

		DataService.eliminaTavolo(tavoloTest);

	}

	@Test
	void testInviaOrdine() {

		Piatto pTest1 = new Piatto("1", 1);
		Piatto pTest2 = new Piatto("2", 2);
		Piatto pTest3 = new Piatto("3", 3);

		Componente compTest = new Componente("Test");

		DataService.inserisciPiatto(pTest1, compTest);
		DataService.inserisciPiatto(pTest2, compTest);
		DataService.inserisciPiatto(pTest3, compTest);

		PiattoOrdinato p1 = new PiattoOrdinato(pTest1, 1);
		PiattoOrdinato p2 = new PiattoOrdinato(pTest2, 2);
		PiattoOrdinato p3 = new PiattoOrdinato(pTest3, 2);

		List<PiattoOrdinato> listaPiatti = new ArrayList<PiattoOrdinato>();

		listaPiatti.add(p1);
		listaPiatti.add(p2);
		listaPiatti.add(p3);

		Tavolo tavoloTest = new Tavolo(999999, 2, StatoTavolo.LIBERO);

		try {
			Logico.aggiungiTavolo(tavoloTest);
			Logico.occupaTavolo(tavoloTest, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Logico.inviaOrdine(listaPiatti, tavoloTest);

		List<Ordine> ordini = DataService.getOrdini(tavoloTest);

		assertTrue(ordini.size() == 1);

		List<PiattoOrdinato> listaPiattiOttenuti = ordini.get(0).getListaPiattiOrdinati();

		assertTrue(listaPiattiOttenuti.size() == 3);

		assertTrue(listaPiattiOttenuti.get(0).equals(p1));
		assertTrue(listaPiattiOttenuti.get(1).equals(p2));
		assertTrue(listaPiattiOttenuti.get(2).equals(p3));

		DataService.eliminaTavolo(tavoloTest);

		DataService.eliminaPiatto(pTest1, compTest);
		DataService.eliminaPiatto(pTest2, compTest);
		DataService.eliminaPiatto(pTest3, compTest);

	}

	@Test
	void testPagaTavolo() {

		Piatto pTest1 = new Piatto("1", 1);
		Piatto pTest2 = new Piatto("2", 2);
		Piatto pTest3 = new Piatto("3", 3);

		Componente compTest = new Componente("Test");

		DataService.inserisciPiatto(pTest1, compTest);
		DataService.inserisciPiatto(pTest2, compTest);
		DataService.inserisciPiatto(pTest3, compTest);

		PiattoOrdinato p1 = new PiattoOrdinato(pTest1, 1);
		PiattoOrdinato p2 = new PiattoOrdinato(pTest2, 2);
		PiattoOrdinato p3 = new PiattoOrdinato(pTest3, 2);

		List<PiattoOrdinato> listaPiatti = new ArrayList<PiattoOrdinato>();

		listaPiatti.add(p1);
		listaPiatti.add(p2);
		listaPiatti.add(p3);

		Tavolo tavoloTest = new Tavolo(999999, 2, StatoTavolo.LIBERO);

		try {
			Logico.aggiungiTavolo(tavoloTest);
			Logico.occupaTavolo(tavoloTest, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Logico.inviaOrdine(listaPiatti, tavoloTest);

		try {
			Logico.pagaTavolo(tavoloTest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Tavolo tavoloTest2 = DataService.getTavolo(tavoloTest);

		assertThrows(Exception.class, () -> {
			Logico.pagaTavolo(tavoloTest2);
		});

		DataService.eliminaTavolo(tavoloTest);

		DataService.eliminaPiatto(pTest1, compTest);
		DataService.eliminaPiatto(pTest2, compTest);
		DataService.eliminaPiatto(pTest3, compTest);

	}

	@Test
	void testTavoloPulito() {
		Tavolo tavoloTest = new Tavolo(99999, 52, StatoTavolo.DA_PULIRE);

		try {
			Logico.aggiungiTavolo(tavoloTest);
			Logico.tavoloPulito(tavoloTest);
		} catch (Exception e) {
		}

		assertTrue(DataService.getTavolo(tavoloTest).getStato().equals(StatoTavolo.LIBERO));

		assertThrows(Exception.class, () -> {
			Logico.tavoloPulito(tavoloTest);
		});

		try {
			Logico.eliminaTavolo(tavoloTest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertThrows(Exception.class, () -> {
			Logico.tavoloPulito(tavoloTest);
		});

	}

	@Test
	void testAggiungiTavolo() {
		Tavolo tavoloTest = new Tavolo(99999, 52, StatoTavolo.DA_PULIRE);

		try {
			Logico.aggiungiTavolo(tavoloTest);
		} catch (Exception e) {
		}

		Tavolo tavoloAttuale = DataService.getTavolo(tavoloTest);
		assertTrue(tavoloTest.equals(tavoloAttuale));
		assertEquals(tavoloTest.getNumPostiMassimi(), tavoloAttuale.getNumPostiMassimi());
		assertEquals(tavoloTest.getStato(), tavoloAttuale.getStato());

		assertThrows(Exception.class, () -> {
			Logico.aggiungiTavolo(tavoloTest);
		});
	}

	@Test
	void testModificaTavolo() {
		Tavolo tavoloTest1 = new Tavolo(99999, 52, StatoTavolo.DA_PULIRE);
		Tavolo tavoloTest2 = new Tavolo(99999, 50, StatoTavolo.DA_PULIRE);
		Tavolo tavoloTest3 = new Tavolo(9999, 52, StatoTavolo.DA_PULIRE);

		try {
			Logico.aggiungiTavolo(tavoloTest1);
			Logico.aggiungiTavolo(tavoloTest3);
			Logico.modificaTavolo(tavoloTest1, tavoloTest2);
		} catch (Exception e) {
		}

		Tavolo tavoloAttuale = DataService.getTavolo(tavoloTest2);

		assertEquals(99999, tavoloAttuale.getNome());
		assertEquals(50, tavoloAttuale.getNumPostiMassimi());
		assertEquals(StatoTavolo.DA_PULIRE, tavoloAttuale.getStato());

		assertThrows(Exception.class, () -> {
			Logico.modificaTavolo(tavoloTest2, tavoloTest3);
		});

		try {
			Logico.eliminaTavolo(tavoloTest2);
			Logico.eliminaTavolo(tavoloTest3);
		} catch (Exception e) {
		}
	}

	@Test
	void testEliminaTavolo() {
		Tavolo tavoloTest1 = new Tavolo(99999, 52, StatoTavolo.DA_PULIRE);

		try {
			Logico.aggiungiTavolo(tavoloTest1);
		} catch (Exception e) {
		}

		try {
			Logico.eliminaTavolo(tavoloTest1);
		} catch (Exception e) {
		}

		Tavolo tavoloNull = DataService.getTavolo(tavoloTest1);

		assertNull(tavoloNull);

		assertThrows(Exception.class, () -> Logico.eliminaTavolo(tavoloTest1));

	}

	@Test
	void testModificaComponente() {
		Componente componenteTest1 = new Componente("Test1");
		Componente componenteTest2 = new Componente("Test2");
		Componente componenteTest3 = new Componente("Test3");

		try {
			Logico.aggiungiComponente(componenteTest1);
			Logico.aggiungiComponente(componenteTest3);
			Logico.modificaComponente(componenteTest1, componenteTest2);
		} catch (Exception e) {
		}

		Componente componenteAttuale = DataService.getComponente(componenteTest2);

		assertTrue(componenteTest2.equals(componenteAttuale));

		assertThrows(Exception.class, () -> Logico.modificaComponente(componenteTest2, componenteTest3));

		try {
			Logico.eliminaCompondente(componenteTest2);
			Logico.eliminaCompondente(componenteTest3);
		} catch (Exception e) {
		}
	}

	@Test
	void testEliminaCompondente() {
		Componente componenteTest1 = new Componente("Test1");

		try {
			Logico.aggiungiComponente(componenteTest1);
			Logico.eliminaCompondente(componenteTest1);
		} catch (Exception e) {
		}

		Componente componenteAttuale = DataService.getComponente(componenteTest1);

		assertNull(componenteAttuale);

		assertThrows(Exception.class, () -> Logico.eliminaCompondente(componenteAttuale));

	}

	@Test
	void testAggiungiComponente() {
		Componente componenteTest1 = new Componente("Test1");

		try {
			Logico.aggiungiComponente(componenteTest1);
		} catch (Exception e) {
		}

		Componente componenteAttuale = DataService.getComponente(componenteTest1);

		assertTrue(componenteTest1.equals(componenteAttuale));

		assertThrows(Exception.class, () -> Logico.aggiungiComponente(componenteTest1));

		try {
			Logico.eliminaCompondente(componenteTest1);
		} catch (Exception e) {
		}

	}

	@Test
	void testEliminaPiatto() {
		Componente compTest = new Componente("Test");

		Piatto piattoTest1 = new Piatto("Test", 2);

		try {
			Logico.aggiungiPiatto(piattoTest1, compTest);
			Logico.eliminaPiatto(piattoTest1, compTest);
		} catch (Exception e) {
		}

		Piatto piattoAttuale = DataService.getPiatto(piattoTest1, compTest);

		assertNull(piattoAttuale);

		assertThrows(Exception.class, () -> Logico.eliminaPiatto(piattoTest1, compTest));

	}

	@Test
	void testAggiungiPiatto() {
		Componente compTest = new Componente("Test");

		Piatto piattoTest1 = new Piatto("Test", 2);

		try {
			Logico.aggiungiPiatto(piattoTest1, compTest);
		} catch (Exception e) {
		}

		Piatto piattoAttuale = DataService.getPiatto(piattoTest1, compTest);

		assertTrue(piattoTest1.equals(piattoAttuale));

		assertThrows(Exception.class, () -> Logico.aggiungiPiatto(piattoTest1, compTest));

		try {
			Logico.eliminaPiatto(piattoTest1, compTest);
		} catch (Exception e) {
		}

	}

	@Test
	void testModificaPiatto() {
		Componente compTest = new Componente("Test");

		Piatto piattoTest1 = new Piatto("Test", 2);
		Piatto piattoTest2 = new Piatto("Test", 1);
		Piatto piattoTest3 = new Piatto("Test3", 2);

		try {
			Logico.aggiungiPiatto(piattoTest1, compTest);
			Logico.aggiungiPiatto(piattoTest3, compTest);
			
			Logico.modificaPiatto(piattoTest1, piattoTest2, compTest);
		} catch (Exception e) {
		}
		
		Piatto piattoAttuale = DataService.getPiatto(piattoTest2, compTest);


		assertEquals(piattoTest1.getNome() , piattoAttuale.getNome());
		assertEquals(1 , piattoAttuale.getPrezzo());

		assertThrows(Exception.class, () -> Logico.modificaPiatto(piattoTest2, piattoTest3,  compTest));

		try {
			Logico.eliminaPiatto(piattoTest2, compTest);
			Logico.eliminaPiatto(piattoTest3, compTest);
		} catch (Exception e) {
		}

	}

	@Test
	void testIncrementaPrecendeza() {
		Componente compTest1 = new Componente("Test1");
		Componente compTest2 = new Componente("Test2");
		
		try {
			Logico.aggiungiComponente(compTest1);
			Logico.aggiungiComponente(compTest2);
		} catch (Exception e) {
		}
		
		Componente compAttuale1 = DataService.getComponente(compTest1);
		Componente compAttuale2 = DataService.getComponente(compTest2);
		
		int precedenza1 = compAttuale1.getPrecendenza();
		int precedenza2 = compAttuale2.getPrecendenza();
		
		Logico.incrementaPrecendeza(compTest2);
		
		compAttuale1 = DataService.getComponente(compTest1);
		compAttuale2 = DataService.getComponente(compTest2);
		
		assertEquals(precedenza1 + 1  , compAttuale1.getPrecendenza());
		assertEquals(precedenza2 - 1  , compAttuale2.getPrecendenza());
	
		try {
			Logico.eliminaCompondente(compAttuale1);
			Logico.eliminaCompondente(compAttuale2);
		} catch (Exception e) {
		}
		
		// test per verificare la precedenza in cima alla lista
		List <Componente> lista = DataService.getComponenti();
		
		// eliminiamo temporaneamente tutte le componenti
		for (Componente c : lista) {
			DataService.eliminaComponente(c);
		}
		
		compTest1 = new Componente("Test");
		
		try {
			Logico.aggiungiComponente(compTest1);
		} catch (Exception e) {
		}
		
		precedenza1 = DataService.getComponente(compTest1).getPrecendenza();
		
		Logico.incrementaPrecendeza(compTest1);
		
		// precedenza invariata
		assertEquals(precedenza1, DataService.getComponente(compTest1).getPrecendenza());
		
		try {
			Logico.eliminaCompondente(compTest1);
		} catch (Exception e) {
		}
		
		for (Componente c : lista) {
			try {
				Logico.aggiungiComponente(c);
			} catch (Exception e) {
			}
		}
		
	}

	@Test
	void testDecrementaPrecendeza() {
		Componente compTest1 = new Componente("Test1");
		Componente compTest2 = new Componente("Test2");
		
		try {
			Logico.aggiungiComponente(compTest1);
			Logico.aggiungiComponente(compTest2);
		} catch (Exception e) {
		}
		
		Componente compAttuale1 = DataService.getComponente(compTest1);
		Componente compAttuale2 = DataService.getComponente(compTest2);
		
		int precedenza1 = compAttuale1.getPrecendenza();
		int precedenza2 = compAttuale2.getPrecendenza();
		
		Logico.decrementaPrecendeza(compTest1);
		
		compAttuale1 = DataService.getComponente(compTest1);
		compAttuale2 = DataService.getComponente(compTest2);
		
		assertEquals(precedenza1 + 1  , compAttuale1.getPrecendenza());
		assertEquals(precedenza2 - 1  , compAttuale2.getPrecendenza());
	
		
		precedenza1 = DataService.getComponente(compTest1).getPrecendenza();
		
		Logico.decrementaPrecendeza(compTest1);
		
		int precedenzaAttuale1 = DataService.getComponente(compTest1).getPrecendenza();
		
		assertEquals(precedenza1, precedenzaAttuale1);		
	
		try {
			Logico.eliminaCompondente(compAttuale1);
			Logico.eliminaCompondente(compAttuale2);
		} catch (Exception e) {
		}
		
	}

	@Test
	void testCalcolaPrezzoCoperto() {
		double prezzoCopertoVecchio = DataService.getPrezzoCoperto();
		double prezzoCopertoTest = 4;
		
		Logico.setPrezzoCoperto(prezzoCopertoTest);
		
		Tavolo tavolo = new Tavolo(9999, 50, StatoTavolo.LIBERO);

		try {
			Logico.aggiungiTavolo(tavolo);
			Logico.occupaTavolo(tavolo, 10);
		} catch (Exception e) {
		}
		
		assertEquals(prezzoCopertoTest * 10, Logico.calcolaPrezzoCoperto(tavolo));

		try {
			Logico.eliminaTavolo(tavolo);
		} catch (Exception e) {
		}
		
		Logico.setPrezzoCoperto(prezzoCopertoVecchio);
		
	}

	@Test
	void testSetPrezzoCoperto() {
		double prezzoCopertoVecchio = DataService.getPrezzoCoperto();
		double prezzoCopertoTest = 4;
		
		Logico.setPrezzoCoperto(prezzoCopertoTest);
		
		assertEquals(prezzoCopertoTest, DataService.getPrezzoCoperto());
		
		Logico.setPrezzoCoperto(prezzoCopertoVecchio);
		
	}

}
