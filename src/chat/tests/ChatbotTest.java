package chat.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import chat.model.Chatbot;

/**
 * Class designed for testing the Chatbot object with JUnit. 
 * We will be developing tests for this as a class.
 * Each additional test will be a version decimal update.
 * @author CodyH
 * @version 1.0
 */
public class ChatbotTest
{
	private Chatbot sampleBot;
	private String userName;

	@Before
	public void setUp() throws Exception
	{
		userName = "test";
		sampleBot = new Chatbot(userName);
	}

	@After
	public void tearDown() throws Exception
	{
		userName = null;
		sampleBot = null;
	}

	@Test
	public void testChatbot()
	{
		assertNotNull("The memesList is not present",sampleBot.getMemesList());
		assertNotNull("The politicalTopicsList is not present", sampleBot.getPoliticalTopicList());
		assertSame("The username is the same object", userName, sampleBot.getUserName());
	}

	@Test
	public void testLengthChecker()
	{
		assertFalse("Correct zero length check",sampleBot.lengthChecker(""));
		assertFalse("Correct null check", sampleBot.lengthChecker(null));
		assertTrue("Correct length check", sampleBot.lengthChecker("       "));
	}

	@Test
	public void testContentChecker()
	{
		String content = "some words";
		sampleBot.setContent(content);
		assertFalse("Check blank failed", sampleBot.contentChecker(" "));
		assertFalse("Check partial failed", sampleBot.contentChecker("words"));
		assertFalse("Check partial failed", sampleBot.contentChecker("some"));
		assertTrue("Check match failed", sampleBot.contentChecker(content));
		assertTrue("Check match plus failed", sampleBot.contentChecker("content " + content));
		assertTrue("Opposite check match failed", sampleBot.contentChecker(content + " other content"));
	}
	
	@Test
	public void testKeyboardMashChecker()
	{
		assertFalse("Mash incorrectly detected", sampleBot.keyboardMashChecker("S.D.F."));
		assertFalse("Mash incorrectly detected", sampleBot.keyboardMashChecker("derf"));
		assertTrue("Mash not detected", sampleBot.keyboardMashChecker("sdf"));
		assertTrue("Mash not detected", sampleBot.keyboardMashChecker("dfg"));
		assertTrue("Mash not detected", sampleBot.keyboardMashChecker("cvb"));
		assertTrue("Mash not detected", sampleBot.keyboardMashChecker(",./"));
	}

	@Test
	public void testPoliticalTopicChecker()
	{
		assertTrue("Topic check", sampleBot.getPoliticalTopicList().contains("Democrat"));
		assertTrue("Topic check", sampleBot.getPoliticalTopicList().contains("Republican"));
		assertTrue("Topic check", sampleBot.getPoliticalTopicList().contains("11/4/16"));
	}

	@Test
	public void testMemeChecker()
	{
		assertTrue("Topic check", sampleBot.getMemesList().contains("doge"));
		assertTrue("Topic check", sampleBot.getMemesList().contains("cute animals"));
	}
	
	@Test
	public void testQuitChecker()
	{
		assertFalse("False positive", sampleBot.quitChecker("exit"));
		assertTrue("False negative", sampleBot.quitChecker("quit"));
	}
	
	@Test
	public void testBuildMemesList()
	{
		assertTrue("Size check", sampleBot.getMemesList().size() >= 10);
		assertTrue("Topic check", sampleBot.getMemesList().contains("doge"));
		assertTrue("Topic check", sampleBot.getMemesList().contains("cute animals"));
	}
	
	@Test
	public void testBuildPoliticalTopicList()
	{
		assertTrue("Size check", sampleBot.getPoliticalTopicList().size() >= 10);
		assertTrue("Topic check", sampleBot.getPoliticalTopicList().contains("Democrat"));
		assertTrue("Topic check", sampleBot.getPoliticalTopicList().contains("Republican"));
		assertTrue("Topic check", sampleBot.getPoliticalTopicList().contains("11/4/16"));
	}

	@Test
	public void testGetUserName()
	{
		assertSame("Getters work", userName, sampleBot.getUserName());
	}

	@Test
	public void testGetContent()
	{
		String content = "topic area of interest";
		sampleBot.setContent(content);
		assertSame("Setters and Getters work", content, sampleBot.getContent());
	}

	@Test
	public void testGetMemesList()
	{
		assertNotNull("Getters work again", sampleBot.getMemesList());
	}

	@Test
	public void testGetPoliticalTopicList()
	{
		assertNotNull("Getters still work in Java", sampleBot.getPoliticalTopicList());
	}
	
	@Test
	public void testSetContent()
	{
		String oldContent = sampleBot.getContent();
		String content = "some other content";
		sampleBot.setContent(content);
		assertNotSame("Changed values", oldContent, sampleBot.getContent());
		assertSame("Setters work", content, sampleBot.getContent());
	}

}
