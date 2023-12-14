/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

/**
 * Class Barra Menu.
 */
class BarraMenu extends JMenuBar {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 5682216914516074221L;

	JButton btnTavoli;
	JButton btnImpostazioni;
	
	/**
	 * costruttore della barra menu.
	 * @param mainFrame Il MainFrame
	 */
	BarraMenu(final MainFrame mainFrame) {

		btnTavoli = new JButton("Tavoli");
		btnImpostazioni = new JButton("Impostazioni");


		ActionListener listenerTavoli = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 mainFrame.paintPanelTavoli();
			}
		};

		ActionListener listenerImpostazioni = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.openImpostazioni();
			}
		};
		
		btnImpostazioni.addActionListener(listenerImpostazioni);
		btnTavoli.addActionListener(listenerTavoli);

		setLayout(new BorderLayout());
		add(btnTavoli, BorderLayout.WEST);
		add(btnImpostazioni, BorderLayout.EAST);

	}
}
