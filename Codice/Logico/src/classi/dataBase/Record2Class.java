package classi.dataBase;

import classi.tavolo.Tavolo;
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

	
	
}
