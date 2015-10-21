package chat.view;

import javax.swing.JOptionPane;
/**
 * A popup display class
 * @author Colm Laro
 * @version 1 10/21/15
 */
public class ChatView
{
	/**
	 * Displays the supplied text as a popup window
	 * @param wordsFromSomewhere The text to be displayed.
	 */
	public void showResponse(String wordsFromSomewhere)
	{
		JOptionPane.showMessageDialog(null, wordsFromSomewhere);
	}
	/**
	 * Displays a popup with a field to type in a response.
	 * It returns the answer to the supplied question as a String.
	 * @param stuff A question to be displayed in the popup window.
	 * @return The user's input as a String data type.
	 */
	public String grabAnswer(String stuff)
	{
		String answer = "";
		
		answer = JOptionPane.showInputDialog(null, stuff);
		
		return answer;
	}
}