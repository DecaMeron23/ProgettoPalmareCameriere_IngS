/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mainPackage.CreateDB;

public class MainAPP { // NO_UCD (unused code)

	public static void main(String[] args) {

		if(!CreateDB.existDB()) {
			CreateDB.main(args);			
		}
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		new MainFrame();
	}
	
}	
