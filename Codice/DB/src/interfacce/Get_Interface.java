package interfacce;

import java.util.List;

import Classi.Tavolo;

public interface Get_Interface {

	public List<Tavolo> get_tavoli(String constraints);
	
	public List<Tavolo> get_componenti(String constraints);
	
	public List<Tavolo> get_piatto(String constraints);
	
}
