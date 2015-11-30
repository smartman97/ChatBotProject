package chat.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import chat.controller.ChatController;
import chat.view.*;;


public class ChatControllerTest
{
	private ChatController testController;

	@Before
	public void setUp() throws Exception
	{
		testController = new ChatController();
	}

	@After
	public void tearDown() throws Exception
	{
		testController = null;
	}

	@Test
	public void testChatController()
	{
		assertNotNull("Data member not initialized", testController.getMyBot());
		assertNotNull("Data member not initialized", testController.getMyDisplay());
		assertTrue("Wrong display type", (testController.getMyDisplay() instanceof ChatView));
		assertTrue("Wrong Frame type",(testController.getBaseFrame() instanceof ChatFrame));
		assertSame("wrong controller", testController, testController.getBaseFrame().getBaseController());
	}
	
	
	

}
