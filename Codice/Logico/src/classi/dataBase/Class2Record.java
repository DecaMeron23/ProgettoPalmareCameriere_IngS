
package classi.dataBase;

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
 * Classe Class2Record. Serve per trasformare le classi che utilizziamo in record da salvare nel DB
 */
class Class2Record {

	/**
	 * Metodo per i Tavoli
	 *
	 * @param tavolo il tavolo da trasformare
	 * @return Il record del tavolo
	 */
	public static TavoloRecord tavolo(Tavolo tavolo) {
		int stato = tavolo.getStato().getInt();
		return new TavoloRecord(tavolo.getNome(), tavolo.getNumPostiMassimi(), stato );
	}

	/**
	 * @param p il piatto da trasformare
	 * @return il piatto record
	 */
	public static PiattoRecord piatto(Piatto p , Componente c) {
		return new PiattoRecord(p.getNome(), p.getPrezzo(), c.getNome());
	}

	/**
	 * @param c la componente da trasformare
	 * @return la componente record
	 */
	public static ComponenteRecord componente(Componente c) {
		return new ComponenteRecord(c.getNome());
	}

	/**
	 * @param o l'ordine da trasformare
	 * @return il record dell'ordine
	 */
	public static OrdineRecord ordine(Ordine o , Tavolo t) {
		return new OrdineRecord(o.getNumeroOrdine(), t.getNome() , o.getCounter());
	}

	public static PiattoOrdinatoRecord piattoOrdinato(PiattoOrdinato piatto , Ordine o , Tavolo t) {
		return new PiattoOrdinatoRecord(piatto.getIdPiatto(), o.getNumeroOrdine(), t.getNome() , piatto.getCommento() , piatto.getQuantita() , piatto.getPiatto().getNome());
	}

	/**
	 * @param r il resoconto del tavolo da trasformare 
	 * @param t il tavolo a cui facciamo riferimento
	 * @return il resoconto del tavolo
	 */
	public static ResocontoTavoloRecord resocontoTavolo(ResocontoTavolo r, Tavolo t) {
		return new ResocontoTavoloRecord(t.getNome(), r.getNumCoperti(), r.getCounter());
	}
	
}
