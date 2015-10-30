package chat.controller;

import chat.model.Chatbot;
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
	
	public ChatController()
	{
		myDisplay = new ChatView();
		String userName = myDisplay.grabAnswer("What is your name?");
		myBot = new Chatbot(userName);
	}
	
	public void start()
	{
		myDisplay.showResponse("Hello " + myBot.getUserName() + "\nMy name is Caliban.");
		
		chat();
	}
	
	private void chat()
	{
		String conversation = myDisplay.grabAnswer("What would you like to talk about today?");
		while(myBot.lengthChecker(conversation))
		{
			if(myBot.contentChecker(conversation))
			{
				myDisplay.showResponse("Wow I can't believe you are interested in " + myBot.getContent() + "!\nMy favorite team is the Patriots!");
			}
			else if(myBot.memeChecker(conversation))
			{
				myDisplay.showResponse("What a lame meme... :( You loser!!");
			}
			else if(myBot.politicalTopicChecker(conversation))
			{
				myDisplay.showResponse("I don't care about polictics since I'm a robot.");
			}
			else
			{
				myDisplay.showResponse(conversation + "?\nI'm not sure what you mean by that.");
			}
			conversation = myDisplay.grabAnswer("What else is on your mind?");
		}
	}
	
	private void shutDown()
	{
		
	}
}
