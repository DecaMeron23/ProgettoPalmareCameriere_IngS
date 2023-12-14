
package classi.dataBase;

import classi.tavolo.Tavolo;
import model.generated.tables.records.TavoloRecord;

/**
 * Classe Class2Record.
 */
class Class2Record {

	/**
	 * Tavolo.
	 *
	 * @param tavolo il tavolo
	 * @return Il record del tavolo
	 */
	public static TavoloRecord tavolo(Tavolo tavolo) {
		int stato = tavolo.getStato().getInt();
		return new TavoloRecord(tavolo.getNome(), tavolo.getNumPostiMassimi(), stato );
	}

	
	
}
