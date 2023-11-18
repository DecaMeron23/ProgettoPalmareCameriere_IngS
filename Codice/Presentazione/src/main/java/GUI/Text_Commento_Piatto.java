package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

public class Text_Commento_Piatto extends JTextArea {

	private static final long serialVersionUID = -7713478503984950541L;
	private String commento_iniziale;

	public Text_Commento_Piatto() {
		this("");
	}

	public Text_Commento_Piatto(String commento_iniziale) {
		super(commento_iniziale);
		this.commento_iniziale = commento_iniziale;
		addMouseListener(new Action_click_text_field());
	}

	public String getText() {
		if (commento_iniziale.equals(super.getText())) {
			return "";
		} else {
			return super.getText();
		}
	}
	
	private boolean is_commento_iniziale() {
		return commento_iniziale.equals(super.getText());
	}

	class Action_click_text_field extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (is_commento_iniziale()) {
				setText("");
			}
		}
	}

	public void reset() {
		setText(commento_iniziale);
	}
}
