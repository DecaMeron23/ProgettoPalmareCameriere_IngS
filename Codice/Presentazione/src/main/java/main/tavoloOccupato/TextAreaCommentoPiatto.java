package main.tavoloOccupato;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

public class TextAreaCommentoPiatto extends JTextArea {

	private static final long serialVersionUID = -7713478503984950541L;
	private String commentoIniziale;

	public TextAreaCommentoPiatto() {
		this("");
	}

	public TextAreaCommentoPiatto(String commentoIniziale) {
		super(commentoIniziale);
		this.commentoIniziale = commentoIniziale;
		addMouseListener(new ActionClickTextField());
	}

	public String getText() {
		if (commentoIniziale.equals(super.getText())) {
			return "";
		} else {
			return super.getText();
		}
	}
	
	private boolean isCommentoIniziale() {
		return commentoIniziale.equals(super.getText());
	}

	class ActionClickTextField extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (isCommentoIniziale()) {
				setText("");
			}
		}
	}

	public void reset() {
		setText(commentoIniziale);
	}
}
