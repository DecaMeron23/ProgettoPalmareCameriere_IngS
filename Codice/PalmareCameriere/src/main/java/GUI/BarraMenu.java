package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar {

	private static final long serialVersionUID = 5682216914516074221L;

	private final GUIMain gui;

	public BarraMenu(GUIMain guiMain) {
		gui = guiMain;

		JButton btn_tavoli = new JButton("Tavoli");
		JButton btn_impostazioni = new JButton("Impostazioni");

		btn_impostazioni.addActionListener(new Azione_Btn_Impostazioni());
		btn_tavoli.addActionListener(new Azione_Btn_Tavoli());

		setLayout(new BorderLayout());
		add(btn_tavoli, BorderLayout.WEST);
		add(btn_impostazioni, BorderLayout.EAST);

	}

	/**
	 * Classe che implementa Action Listener per il pulsante tavoli
	 */
	private class Azione_Btn_Tavoli implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.repaint_panel_tavoli();
		}

	}

	/**
	 * Classe che implementa Action Listener per il pulsante impostazioni
	 */
	private class Azione_Btn_Impostazioni implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.open_impostazioni();
		}
	}

}
