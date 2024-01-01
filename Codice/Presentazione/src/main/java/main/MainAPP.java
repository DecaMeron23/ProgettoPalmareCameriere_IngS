/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package main;

import mainPackage.CreateDB;

public class MainAPP { // NO_UCD (unused code)

	public static void main(String[] args) {

		if(!CreateDB.existDB()) {
			CreateDB.main(args);			
		}
		
		new MainFrame();
	}
	
}	
