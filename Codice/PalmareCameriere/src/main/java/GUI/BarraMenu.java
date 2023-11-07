package GUI;

import javax.swing.JButton;
import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar {

	private static final long serialVersionUID = 5682216914516074221L;

	public BarraMenu(JButton btn_indietro, JButton btn_impostazioni) {
		super();
		add(btn_indietro);
		add(btn_impostazioni);
		
	}

}
