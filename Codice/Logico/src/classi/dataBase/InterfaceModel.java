package classi.dataBase;

import java.util.List;

import classi.menu.Componente;
import classi.ordine.Ordine;
import classi.tavolo.Tavolo;

public interface InterfaceModel {

	public List<Tavolo> getListaTavoli();
	
	public List<Componente> getListaComponente();
	
	public List<Ordine> getListaOrdiniTavolo(Tavolo tavolo);
	
}
