package chat.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
	private void removeCommonEnglishWords(List<String> tweetTexts)
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
	
	private String[] importWordsToArray()
	{
		String[] boringWords;
		int wordCount = 0;
		try
		{
			Scanner wordFile = new Scanner(new File("commonWords.txt"));
			while(wordFile.hasNext())
			{
				wordCount++;
				wordFile.next();
			}
			wordFile.reset();
			boringWords = new String[wordCount];
			int boringWordCount = 0;
			while(wordFile.hasNext())
			{
				boringWords[boringWordCount] = wordFile.next();
				boringWordCount++;
			}
			wordFile.close();
		}
		catch (FileNotFoundException e)
		{
			return new String[0];
		}
		return boringWords;
	}
}
