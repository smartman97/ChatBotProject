package chat.view;

import chat.controller.ChatController;
import chat.view.ChatPanel;
import javax.swing.JFrame;

/**
 * 
 * @author Colm Laro
 * @version 1.2 11/09/2015 Added a panel to the frame.
 *
 */

public class ChatFrame extends JFrame
{
	private ChatController baseController;
	private ChatPanel basePanel;
	
	/**
	 * Initilizes the objects for the class
	 * @param baseController
	 */
	public ChatFrame(ChatController baseController)
	{
		this.baseController = baseController;
		basePanel = new ChatPanel(baseController);
		setupFrame();
	}
	
	/**
	 * Sets specific parameters for the JFrame.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel); //Must be the first line of setupFrame() -Installs panel in the frame.
		this.setSize(700, 400);			//Fine a good size for the application.
		this.setTitle("Caliban, the simple chatbot");
		this.setResizable(false);		//Advisable not required.
		this.setVisible(true);			//Must be the last line of setupFrame()
	}
	
	/**
	 * Returns the base controller.
	 * @return
	 */
	public ChatController getBaseController()
	{
		return baseController;
	}
}
