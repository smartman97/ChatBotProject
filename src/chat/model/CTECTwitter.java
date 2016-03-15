package chat.model;

import java.util.ArrayList;

import twitter4j.*;
import chat.controller.ChatController;
import java.util.List;

public class CTECTwitter
{
	private ArrayList<Status> statusList;
	private ArrayList<String> tweetTexts;
	private Twitter chatbotTwitter;
	private ChatController baseController;
	
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		statusList = new ArrayList<Status>();
		tweetTexts = new ArrayList<String>();
		chatbotTwitter = TwitterFactory.getSingleton();
	}
	
	public void sentTweet(String tweet)
	{
		try
		{
		chatbotTwitter.updateStatus("This chatBot is now Tweeting for Colm #APCSRocks @CTECNow Thanks @cscheerleader & @codyhenrichsen!");
		}
		catch(TwitterException error)
		{
			baseController.handleErrors(error.getErrorMessage());
		}
	}
	
	public String topResults(List<String> wordList)
	{
		return null;
	}
	
	public void loadTweets(String twitterHandle) throws TwitterException
	{
		Paging statusPage = new Paging(1, 200);
		int page = 1;
		
		while(page <= 10)
		{
			statusPage.setPage(page);
			statusList.addAll(chatbotTwitter.getUserTimeline(twitterHandle, statusPage));
			page++;
		}
		
		for(Status currentStatus : statusList)
		{
			String[] tweetText = currentStatus.getText().split(" ");
			for(String word : tweetText)
			{
				tweetTexts.add(removePunctuation(word).toLowerCase());
			}
		}
		removeCommonEnglishWords(tweetTexts);
		removeEmptyText();
	}
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ".,'?!;:/\"(){}^[]<>-_&";
		
		String scrubbedString = "";
		for(int i = 0; i < currentString.length(); i++)
		{
			if(punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		
		return scrubbedString;
	}
	
	@SuppressWarnings("unchecked")
	private void removeCommonEnglishWords(List<String> wordList)
	{
		String[] boringWords = importWordsToArray();
		
		for(int count = 0; count < tweetTexts.size(); count++)
		{
			for(int removeSpot = 0; removeSpot < boringWords.length; removeSpot++)
			{
				if(tweetTexts.get(count).equalsIgnoreCase(boringWords[removeSpot]))
				{
					tweetTexts.remove(count);
					count--;
					removeSpot = boringWords.length; //Exit the inner loop
				}
			}
		}
	}
	
	private void removeEmptyText()
	{
		for(int spot = 0; spot < tweetTexts.size(); spot++)
		{
			if(tweetTexts.get(spot).equals(""))
			{
				tweetTexts.remove(spot);
				spot--; //if we fail we have to subtract to move over
			}
		}
	}
}
