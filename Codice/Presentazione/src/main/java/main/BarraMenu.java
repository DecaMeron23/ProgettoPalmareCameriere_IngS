package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar {

	private static final long serialVersionUID = 5682216914516074221L;

	private final MainFrame gui;

	public BarraMenu(MainFrame guiMain) {
		gui = guiMain;

		JButton btnTavoli = new JButton("Tavoli");
		JButton btnImpostazioni = new JButton("Impostazioni");

		btnImpostazioni.addActionListener(new AzioneBtnImpostazioni());
		btnTavoli.addActionListener(new AzioneBtnTavoli());

		setLayout(new BorderLayout());
		add(btnTavoli, BorderLayout.WEST);
		add(btnImpostazioni, BorderLayout.EAST);

	}

	/**
	 * Classe che implementa Action Listener per il pulsante tavoli
	 */
	private class AzioneBtnTavoli implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.repaintPanelTavoli();
		}

	}

	/**
	 * Classe che implementa Action Listener per il pulsante impostazioni
	 */
	private class AzioneBtnImpostazioni implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.openImpostazioni();
		}
	}

}
