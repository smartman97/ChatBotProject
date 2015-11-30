package chat.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import chat.view.*;
import chat.controller.ChatController;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChatPanelTest
{
	private ChatPanel basePanel;
	
	@Before
	public void setUp() throws Exception
	{
		basePanel = new ChatPanel(new ChatController());
	}

	@After
	public void tearDown() throws Exception
	{
		basePanel = null;
	}

	@Test
	public void testChatPanel()
	{
		assertTrue("Layout issues", !(basePanel.getLayout() instanceof java.awt.FlowLayout));
		
	}
	
	@Test
	public void testSetupPanel()
	{
		assertTrue("Tip/Hint not set", basePanel.getTypingField().getToolTipText().length() > 4);
		assertTrue("Incorrect layout manager", basePanel.getLayout() instanceof javax.swing.SpringLayout);
		assertTrue("Minimum number of components not met", basePanel.getComponents().length >= 4);
	}
	
	@Test
	public void testSetupListeners()
	{
		for(Component currentGUIComponent : basePanel.getComponents())
		{
			if(currentGUIComponent instanceof JButton)
			{
				assertNotNull("No listener attached to button", ((JButton) currentGUIComponent).getActionListeners()[0]);
				assertTrue("Correct listener attached to button",((JButton) currentGUIComponent).getActionListeners()[0] instanceof ActionListener);
			}
		}
	}
}
