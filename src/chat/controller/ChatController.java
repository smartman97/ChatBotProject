package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatFrame;
import chat.view.ChatView;

/**
 * Application controller for the ChatBot project.
 * @author clar5254
 *@version 1.2 10/23/15 
 */

public class ChatController
{
	private ChatView myDisplay;
	private Chatbot myBot;
	private ChatFrame baseFrame;

	/**
	 * Contructor that creates all the objects for the class
	 */
	public ChatController()
	{
		myDisplay = new ChatView();
		String userName = myDisplay.grabAnswer("What is your name?");
		myBot = new Chatbot(userName);
		baseFrame = new ChatFrame(this);
	}
	
	/**
	 * Method that was used for starting the popups. No longer needed 
	 */
	public void start()
	{
		myDisplay.showResponse("Hello " + myBot.getUserName() + ",\nMy name is Caliban.");
		//chat();
	}
	
	/**
	 * Method that stores input from the user and shows response from computer
	 */
	private void chat()
	{
		String conversation = myDisplay.grabAnswer("What would you like to talk about today?");
		while(myBot.lengthChecker(conversation))
		{
			conversation = myBot.processConversation(conversation);
			conversation = myDisplay.grabAnswer(conversation);
		}
	}
	
	/**
	 * Takes user input and allows the GUI to see it
	 * @param conversation
	 * @return
	 */
	public String userToChatbot(String conversation)
	{
		String response = "";
		
		if(myBot.quitChecker(conversation))
		{
			shutDown();
		}
		
		response = myBot.processConversation(conversation);
		return response;
	}
	
	/**
	 * Ends the program when called
	 */
	private void shutDown()
	{
		myDisplay.showResponse("Goodbye, " + myBot.getUserName());
		System.exit(0);
	}
	
	/**
	 * Returns the chatview.
	 * @return
	 */
	public ChatView getMyDisplay()
	{
		return myDisplay;
	}

	/**
	 * Returns the chatbot.
	 * @return
	 */
	public Chatbot getMyBot()
	{
		return myBot;
	}

	
	/**
	 * Returns the chatframe.
	 */
	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}
}
