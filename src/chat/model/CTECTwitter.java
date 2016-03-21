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
			chatbotTwitter
					.updateStatus("This chatBot is now Tweeting for Colm #APCSRocks @CTECNow Thanks @cscheerleader & @codyhenrichsen!");
		}
		catch (TwitterException error)
		{
			baseController.handleErrors(error.getErrorMessage());
		}
	}

	public void loadTweets(String twitterHandle) throws TwitterException
	{
		statusList.clear();
		tweetTexts.clear();

		Paging statusPage = new Paging(1, 200);
		int page = 1;

		while (page <= 10)
		{
			statusPage.setPage(page);
			statusList.addAll(chatbotTwitter.getUserTimeline(twitterHandle,
					statusPage));
			page++;
		}

		for (Status currentStatus : statusList)
		{
			String[] tweetText = currentStatus.getText().split(" ");
			for (String word : tweetText)
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
		for (int i = 0; i < currentString.length(); i++)
		{
			if (punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}

		return scrubbedString;
	}

	@SuppressWarnings("unchecked")
	private List removeCommonEnglishWords(List<String> tweetTexts)
	{
		String[] boringWords = importWordsToArray();

		for (int count = 0; count < tweetTexts.size(); count++)
		{
			for (int removeSpot = 0; removeSpot < boringWords.length; removeSpot++)
			{
				if (tweetTexts.get(count).equalsIgnoreCase(
						boringWords[removeSpot]))
				{
					tweetTexts.remove(count);
					count--;
					removeSpot = boringWords.length; // Exit the inner loop
				}
			}
		}
		return tweetTexts;
	}

	private void removeEmptyText()
	{
		for (int spot = 0; spot < tweetTexts.size(); spot++)
		{
			if (tweetTexts.get(spot).equals(""))
			{
				tweetTexts.remove(spot);
				spot--; // if we fail we have to subtract to move over
			}
		}
	}

	private String[] importWordsToArray()
	{
		String[] boringWords;
		int wordCount = 0;

		Scanner wordFile = new Scanner(getClass().getResourceAsStream("commonWords.txt"));
		while (wordFile.hasNext())
		{
			wordCount++;
			wordFile.next();
		}

		wordFile = new Scanner(getClass().getResourceAsStream("commonWords.txt"));
		boringWords = new String[wordCount];
		int boringWordCount = 0;
		while (wordFile.hasNext())
		{
			boringWords[boringWordCount] = wordFile.next();
			boringWordCount++;
		}
		wordFile.close();

		return boringWords;
	}

	private void removeTwitterUserNamesFromList(List<String> tweetTexts)
	{
		for (int wordCount = 0; wordCount < tweetTexts.size(); wordCount++)
		{
			if (tweetTexts.get(wordCount).length() >= 1
					&& tweetTexts.get(wordCount).charAt(0) == '@')
			{
				tweetTexts.remove(wordCount);
				wordCount--;
			}
		}
	}

	public String topResults()
	{
		String tweetResults = "";

		int topWordLocation = 0;
		int topCount = 0;
		int wordUseCount = 0;

		for (int index = 0; index < tweetTexts.size(); index++)
		{
			wordUseCount = 0;

			for (int spot = index + 1; spot < tweetTexts.size(); spot++)
			{
				if (tweetTexts.get(index).equals(tweetTexts.get(spot)))
				{
					wordUseCount++;
				}
				if (wordUseCount > topCount)
				{
					topCount = wordUseCount;
					topWordLocation = index;
				}
			}
		}

		tweetResults = "The top word used is: "
				+ tweetTexts.get(topWordLocation) + " and it was used "
				+ topCount + " times!";
		return tweetResults;
	}
}
