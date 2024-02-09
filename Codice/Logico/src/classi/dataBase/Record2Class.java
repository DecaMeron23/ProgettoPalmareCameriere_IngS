package classi.dataBase;

import java.util.List;

import classi.enumerativi.StatoTavolo;
import classi.menu.Componente;
import classi.menu.Piatto;
import classi.ordine.Ordine;
import classi.ordine.PiattoOrdinato;
import classi.tavolo.ResocontoTavolo;
import classi.tavolo.Tavolo;
import model.generated.tables.records.ComponenteRecord;
import model.generated.tables.records.OrdineRecord;
import model.generated.tables.records.PiattoOrdinatoRecord;
import model.generated.tables.records.PiattoRecord;
import model.generated.tables.records.ResocontoTavoloRecord;
import model.generated.tables.records.TavoloRecord;

/**
 *  Class Record2Class.
 */
class Record2Class {

	/**
	 * Tavolo.
	 *
	 * @param tavoloRecord il record del tavolo
	 * @return il tavolo
	 */
	public static Tavolo tavolo(TavoloRecord tavoloRecord , ResocontoTavolo resconto) {
		return new Tavolo(tavoloRecord.getNome(), tavoloRecord.getPostiMassimi(), StatoTavolo.getStato(tavoloRecord.getStato()));
	}

	/**
	 * Piatto
	 * 
	 * @param piattoRecord il record del piatto
	 * @return il piatto
	 */
	public static Piatto piatto(PiattoRecord piattoRecord) {
		return new Piatto(piattoRecord.getNome(), piattoRecord.getPrezzo());
	}

	/**
	 * Componente
	 *
	 * @param componenteRecord la componente record da trasformare
	 * @return la componente
	 */
	public static Componente componente(ComponenteRecord componenteRecord) {
		return new Componente(componenteRecord.getNome() , null , componenteRecord.getPrecedenza());
	}

	/**
	 * @param resocontoTavoloRecord il resoconto del tavolo in formato Record
	 * @param listaOrdini la lista dei ordini
	 * @param prezzoCoperto il prezzo attuale del coperto
	 * @return il resoconto del tavolo
	 */
	public static ResocontoTavolo resocontoTavolo(ResocontoTavoloRecord resocontoTavoloRecord , List<Ordine> listaOrdini , double prezzoCoperto) {
		ResocontoTavolo.setCoperto(prezzoCoperto);
		return new ResocontoTavolo(resocontoTavoloRecord.getNumCoperti() , listaOrdini , resocontoTavoloRecord.getCounterOrdini());
	}

	/**
	 * @param ordineRecord l'ordine record da trasformare
	 * @param piatti la lista dei piatti dell'ordine
	 * @return l'ordine
	 */
	public static Ordine ordine(OrdineRecord ordineRecord , List<PiattoOrdinato> piatti) {
		return new Ordine(piatti, ordineRecord.getNumOrdine());
	}

	public static PiattoOrdinato piattoOrdinato(PiattoOrdinatoRecord piattoOrdinato , Piatto piatto) {
		
		return new PiattoOrdinato(piattoOrdinato.getCommento(), piatto, piattoOrdinato.getNumPiatto() , piattoOrdinato.getOccorrenze());
	}
	
}
