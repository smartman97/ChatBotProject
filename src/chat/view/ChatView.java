package chat.view;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * A popup display class
 * @author Colm Laro
 * @version 1.3 11/5/15 Added icons to popups
 */
public class ChatView
{
	private String windowMessage;
	private ImageIcon chatIcon;
	
	public ChatView()
	{
		windowMessage = "Caliban ChatBot";
		chatIcon = new ImageIcon (getClass().getResource("images/chatbot.png"));
	}
	
	/**
	 * Displays the supplied text as a popup window
	 * @param wordsFromSomewhere The text to be displayed.
	 */
	public void showResponse(String wordsFromSomewhere)
	{
		JOptionPane.showMessageDialog(null, wordsFromSomewhere, windowMessage, JOptionPane.INFORMATION_MESSAGE,chatIcon);
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
		
		answer = JOptionPane.showInputDialog(null, stuff, windowMessage, JOptionPane.PLAIN_MESSAGE, chatIcon, null, "Answer here.").toString();
		
		return answer;
	}
}