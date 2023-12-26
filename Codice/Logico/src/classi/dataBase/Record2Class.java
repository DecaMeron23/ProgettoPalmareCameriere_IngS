package classi.dataBase;

import java.util.List;

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
	public static Tavolo tavolo(TavoloRecord tavoloRecord) {
		return new Tavolo(tavoloRecord.getNome(), tavoloRecord.getPostiMassimi());
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
		return new Componente(componenteRecord.getNome());
	}

	/**
	 * @param resocontoTavoloRecord il resoconto del tavolo record da trasformare
	 * @return il resoconto del tavolo
	 */
	public static ResocontoTavolo resocontoTavolo(ResocontoTavoloRecord resocontoTavoloRecord) {
		return new ResocontoTavolo(resocontoTavoloRecord.getNumCoperti());
	}

	/**
	 * @param ordineRecord l'ordine record da trasformare
	 * @param piatti la lista dei piatti dell'ordine
	 * @return l'ordine
	 */
	public static Ordine ordine(OrdineRecord ordineRecord , List<PiattoOrdinato> piatti) {
		return new Ordine(piatti, ordineRecord.getNumOrdine());
	}

	public static PiattoOrdinato piattoOrdinato(PiattoOrdinatoRecord pOrdinato , Piatto piatto) {
		
		return new PiattoOrdinato(pOrdinato.getCommento(), piatto, pOrdinato.getNumPiatto());
	}
	
}
