package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

import util.textToSpeech;

public class SelectionTextPane extends JTextArea {
	
	//KEY BINDINGS: w to advance to the next word
	//				s to advance to the next sentance

	private JScrollPane helpScroll;
	private String words[];
	private String sentences[];
	private int cursor;
	private DefaultHighlighter.DefaultHighlightPainter highlightPainter;

	public SelectionTextPane(String text) {
		
		//TODO: We need to tell the user instructions (audio instructions) on how to advance in the help.
		
		helpScroll = new JScrollPane(this,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setText(text);
		this.setEditable(false);
		this.setWrapStyleWord(true);
		this.setLineWrap(true);
		setKeyBindings();
		cursor = 0;
		words = getText().split(" \n\r\t");
		sentences = getText().split("[.!?]");
		highlightPainter = new DefaultHighlightPainter(Color.RED);
	}
	
	//sets up key bindings for advancing through the string.
	private void setKeyBindings() {
		getInputMap().put(KeyStroke.getKeyStroke('w'), "moveWordRight");
		getActionMap().put("moveWordRight", new SelectNextWordAction());

		getInputMap().put(KeyStroke.getKeyStroke('s'), "moveSentanceRight");
		getActionMap().put("moveSentanceRight", new SelectNextSentanceAction());
	}

	private class SelectNextWordAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {

			String string = SelectionTextPane.this.getText();
			while(string.charAt(cursor) == '\n') cursor++;
			int newlineIndex = string.indexOf('\n', cursor);
			int spaceIndex = string.indexOf(' ', cursor);
			int endWord;
			if (newlineIndex < spaceIndex) endWord = newlineIndex;
			else endWord = spaceIndex;
			if (endWord != -1) {
				SelectionTextPane.this.getHighlighter().removeAllHighlights();
				try {
					textToSpeech.getInstance().speakNow(string.substring(cursor, endWord));
					SelectionTextPane.this.getHighlighter().addHighlight(
							cursor, endWord, highlightPainter);
					cursor = endWord + 1;
					
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				SelectionTextPane.this.getHighlighter().removeAllHighlights();
				try {
					SelectionTextPane.this.getHighlighter().addHighlight(
							cursor, string.length(), highlightPainter);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
			SelectionTextPane.this.setCaretPosition(cursor);
		}
	}

	private class SelectNextSentanceAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {

			String string = SelectionTextPane.this.getText();
			int indexPeriod = string.indexOf('.', cursor);
			int indexExclaimation = string.indexOf('!', cursor);
			int indexQuestionMark = string.indexOf('?', cursor);
			if (indexPeriod == -1 && indexExclaimation == -1
					&& indexQuestionMark == -1) {
				SelectionTextPane.this.getHighlighter().removeAllHighlights();
				try {
					SelectionTextPane.this.getHighlighter().addHighlight(
							cursor, string.length(), highlightPainter);
					textToSpeech.getInstance().speakNow(string.substring(cursor, string.length()));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			} else {
				int[] indices = new int[] { indexPeriod, indexExclaimation,
						indexQuestionMark };
				int endSentence = min(indices);
				SelectionTextPane.this.getHighlighter().removeAllHighlights();
				try {
					SelectionTextPane.this.getHighlighter().addHighlight(
							cursor, endSentence, highlightPainter);
					textToSpeech.getInstance().speakNow(string.substring(cursor, endSentence));
					cursor = endSentence + 1;
					
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			SelectionTextPane.this.setCaretPosition(cursor);
		}
	}

	public static int min(int[] values) {
		int min = Integer.MAX_VALUE;
		for (int value : values) {
			if (value < min && value >= 0)
				min = value;
		}
		return min;
	}
}
