package mainPackage;

import java.util.List;

import classi.tavolo.Tavolo;
import mainPackage.interfacce.Add_Interface;
import mainPackage.interfacce.Get_Interface;

/**
 * Classe che serve ad aggiungere elementi al data base
 */
public class ModificheDB implements Add_Interface , Get_Interface{

	public static final String ALL = "ALL";

	@Override
	public List<Tavolo> get_tavoli(String constraints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tavolo> get_componenti(String constraints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tavolo> get_piatto(String constraints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean aggiungi_tavolo(String str_tavolo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aggiungi_componente(String str_tavolo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aggiungi_piatto(String str_tavolo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
