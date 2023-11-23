/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package main;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;

/**
 * Class Barra Menu.
 */
class BarraMenu extends JMenuBar {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 5682216914516074221L;

	/**
	 * costruttore della barra menu.
	 *
	 * @param listenerTavolo l'action listener per il pulsante tavoli
	 * @param listenerImpostazioni l'action listener per il pulsante impostazioni
	 */
	BarraMenu(ActionListener listenerImpostazioni, ActionListener listenerTavolo) {

		JButton btnTavoli = new JButton("Tavoli");
		JButton btnImpostazioni = new JButton("Impostazioni");

		btnImpostazioni.addActionListener(listenerTavolo);
		btnTavoli.addActionListener(listenerImpostazioni);

		setLayout(new BorderLayout());
		add(btnTavoli, BorderLayout.WEST);
		add(btnImpostazioni, BorderLayout.EAST);

	}
}
