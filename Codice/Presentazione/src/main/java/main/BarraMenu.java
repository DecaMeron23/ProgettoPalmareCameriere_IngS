/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Class Barra Menu.
 */
class BarraMenu extends JMenuBar {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 5682216914516074221L;

	/**
	 * costruttore della barra menu.
	 * @param mainFrame Il MainFrame
	 */
	BarraMenu(final MainFrame mainFrame) {
			
		//creiamo i pulsanti
		JButton btnTavoli = new JButton("Tavoli");
		JButton btnImpostazioni = new JButton("Impostazioni");
		JButton btnChiudi = new JButton("Chiudi");

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
		
		ActionListener listenerChiudi = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.setEnabled(false);
				
				String titolo = "Conferma Chiusura";
				String frase = "Sicuro di voler uscire?";
				
				ActionListener listenerSi = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JDialog frame = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
						frame.dispose();
						mainFrame.dispose();
					}
				};
				
				ActionListener listenerNo = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//riattiviamo il main frame
						mainFrame.setEnabled(true);
						JDialog frame = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
						frame.dispose();
						mainFrame.toFront();
					}
				};
				
				WindowAdapter windowAdapter = new WindowAdapter() {
					
					@Override
					public void windowClosed(WindowEvent e) {
						super.windowClosed(e);
						//riattiviamo il main frame
						mainFrame.setEnabled(true);
						mainFrame.toFront();
					}
				};
				
				new FrameConfermaScelta(titolo, frase, listenerSi , listenerNo, windowAdapter);
			}
		};
		
		JPanel panelEAST = new JPanel(new FlowLayout(FlowLayout.RIGHT , 10 , 0));
		
		panelEAST.add(btnImpostazioni);
		panelEAST.add(btnChiudi);
		
		btnImpostazioni.addActionListener(listenerImpostazioni);
		btnTavoli.addActionListener(listenerTavoli);
		btnChiudi.addActionListener(listenerChiudi);
		
		setLayout(new BorderLayout());
		
		add(btnTavoli, BorderLayout.WEST);
		add(panelEAST, BorderLayout.EAST);

	}
}
