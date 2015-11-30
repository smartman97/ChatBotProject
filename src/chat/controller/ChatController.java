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

	public ChatController()
	{
		myDisplay = new ChatView();
		String userName = myDisplay.grabAnswer("What is your name?");
		myBot = new Chatbot(userName);
		baseFrame = new ChatFrame(this);
	}
	
	public void start()
	{
		myDisplay.showResponse("Hello " + myBot.getUserName() + ",\nMy name is Caliban.");
		
		chat();
	}
	
	private void chat()
	{
		String conversation = myDisplay.grabAnswer("What would you like to talk about today?");
		while(myBot.lengthChecker(conversation))
		{
			conversation = myBot.processConversation(conversation);
			conversation = myDisplay.grabAnswer(conversation);
		}
	}
	
	private void shutDown()
	{
		myDisplay.showResponse("Goodbye, " + myBot.getUserName());
		System.exit(0);
	}
	
	public ChatView getMyDisplay()
	{
		return myDisplay;
	}

	public Chatbot getMyBot()
	{
		return myBot;
	}

	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}
}
