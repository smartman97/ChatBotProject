package chat.model;

import java.util.ArrayList;

/**
 * @author Colm Laro
 * @version 1.2 10/23/15
 */
public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private String userName;
	private String content;
	private int topicCounter;

	/**
	 * Creates an instance of the Chatbot with the supplied username.
	 * @param userName
	 * The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		this.memesList = new ArrayList<String>();
		this.politicalTopicList = new ArrayList<String>();
		this.userName = userName;
		this.content = "Sports";
		topicCounter = 0;

		buildMemesList();
		buildPoliticalTopicsList();
	}

	/**
	 * Builds the array list of memes.
	 */
	private void buildMemesList()
	{
		// me gusta, troll, what if i, spoder, doge, BLB, cute animals, pepe,
		// aliens, unhelpful teacher.
		this.memesList.add("me gusta");
		this.memesList.add("troll");
		this.memesList.add("what if i told you");
		this.memesList.add("spoderman");
		this.memesList.add("doge");
		this.memesList.add("bad luck brian");
		this.memesList.add("cute animals");
		this.memesList.add("pepe");
		this.memesList.add("aliens");
		this.memesList.add("unhelpful teacher");
	}
	/**
 	* Builds the array list of political topics.
 	*/
	private void buildPoliticalTopicsList()
	{
		this.politicalTopicList.add("Obama");
		this.politicalTopicList.add("Trump");
		this.politicalTopicList.add("Clinton");
		this.politicalTopicList.add("Biden");
		this.politicalTopicList.add("Carson");
		this.politicalTopicList.add("Rubio");
		this.politicalTopicList.add("Fiorina");
		this.politicalTopicList.add("Sanders");
		this.politicalTopicList.add("vote");
		this.politicalTopicList.add("11/8/2016");
		this.politicalTopicList.add("election");
		this.politicalTopicList.add("democrat");
		this.politicalTopicList.add("republican");
		this.politicalTopicList.add("liberal");
		this.politicalTopicList.add("conservative");
	}

	/**
	 * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true.
	 * @param currentInput
	 * @return A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;

		if (currentInput != null)
		{
			if (currentInput.length() >= 1)
			{
				hasLength = true;
			}
		}

		return hasLength;
	}

	/**
	 * Checks if the supplied String matches the content area for this Chatbot
	 * instance.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether it matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean hasContent = false;

		if (currentInput.toLowerCase().contains(content.toLowerCase()))
		{
			hasContent = true;
		}

		return hasContent;
	}

	/**
	 * Checks if supplied String matches ANY of the topics in the
	 * politicalTopicsList. Returns true if it does find a match and false if it does not match.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether the String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		Boolean isTopic = false;
		
		for(String currentTopic: politicalTopicList)
		{
			if(currentInput.toLowerCase().contains(currentTopic.toLowerCase()))
			{
				isTopic = true;
			}
		}
		
		return isTopic;
	}
	
	public boolean byeChecker(String currentInput)
	{
		Boolean isBye = false;
		
		if(currentInput.toLowerCase().equals("goodbye"))
		{
			isBye = true;
		}
				
		return isBye;
	}

	/**
	 * Checks to see that the supplied String value is in the current memesList variable.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether the supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean hasMeme = false;
		
		for(String currentMeme: memesList)
		{
			if(currentInput.toLowerCase().contains(currentMeme.toLowerCase()))
			{
				hasMeme = true;
			}
		}
		
		return hasMeme;
	}
	
	public boolean keyboardMashChecker(String currentInput)
	{
		boolean isMash = false;
		
		if(currentInput.contains("sdf") || currentInput.contains("dfg") || currentInput.contains("cvb") || currentInput.contains(",./"))
		{
			isMash = true;
		}
		else if(currentInput.contains("derf") || currentInput.contains("S.D.F."))
		{
			isMash = false;
		}
		
		return isMash;
	}
	
	public boolean quitChecker(String currentInput)
	{
		boolean hasQuit = false;
		
		if(currentInput.contains("quit") || currentInput.contains("bye") || currentInput.contains("goodbye") || currentInput.contains("exit"))
		{
			hasQuit = true;
		}
		
		return hasQuit;
	}

	public String processConversation(String currentInput)
	{
		String nextConversation = "Oh, what else would you like to talk about?";
		int randomTopic = (int) (Math.random() * 6); // generates a random number between 0 and 4.
		
		switch(randomTopic)
		{
		case 0:
			if(memeChecker(currentInput))
			{
				nextConversation = "That is very popular meme this year.\nWhat else is on your mind?";
			}
			else
			{
				nextConversation = "You don't want to talk about memes??";
			}
			break;
		case 1:
			if(politicalTopicChecker(currentInput))
			{
				nextConversation = "I'm not a fan of politics seeing as I'm a robot.\nWhat else are you thinking of?";
			}
			else
			{
				nextConversation = "I'm very glad that you didn't talk about politics!";
			}
			break;
		case 2:
			if(contentChecker(currentInput))
			{
				nextConversation = "Wow you like sports too? My favorite team is the patriots!\nWhat is your favorite team?";
			}
			else
			{
				nextConversation = "What we should really be talking about are sports.";
			}
			break;
		case 3:
			if(currentInput.length() > 20)
			{
				nextConversation = "Wow you are quite the talker there...";
			}
			else
			{
				nextConversation = "You don't talk much do you...";
			}
			break;
		case 4:
			if(keyboardMashChecker(currentInput))
			{
				nextConversation = "Well that didn't make any sense.";
			}
			else
			{
				nextConversation = "Wow! You can actually speak english.";
			}
			break;
		case 5:
			nextConversation = "On an off note I am only a month old! How about you?";
			break;
		default:
			nextConversation = "I regret to inform you the universe just ended... :(";
			break;
		}
		
		return nextConversation;
	}
	
	/**
	 * Returns the username of this Chatbot instance.
	 * @return The username of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Returns the content area for this Chatbot instance.
	 * @return The content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * Getter method for the memesList object.
	 * @return The reference to the meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}

	/**
	 * Getter method for the politicalTopicList object.
	 * @return The reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicList;
	}

	/**
	 * Updates the content area for this Chatbot instance.
	 * @param content The updated value for the content area.
	 */
	public void setContent(String content)
	{

	}
}