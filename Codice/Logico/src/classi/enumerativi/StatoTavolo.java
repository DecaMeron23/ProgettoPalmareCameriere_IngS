/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package classi.enumerativi;

/**
 * Stato Tavolo tipo enumerativo per la gestione dei tavoli.
 */
public enum StatoTavolo {

	/** Tavolo Libero. */
	LIBERO,

	/** Tavolo Occupato. */
	OCCUPATO,

	/** Tavolo Da Pulire. */
	DA_PULIRE;

	public int getInt() {
		return switch (this) {
		case LIBERO -> 0;
		case OCCUPATO -> 1;
		case DA_PULIRE -> 2;
		};
	}
	
	public static StatoTavolo getStato(int statoInt) {
		return switch (statoInt) {
		case 0 -> LIBERO;
		case 1 -> OCCUPATO;
		case 2 -> DA_PULIRE;
		default -> throw new IllegalArgumentException("Unexpected value: " + statoInt);		
		};
	}
}