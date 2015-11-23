package chat.view;

import chat.controller.ChatController;

import javax.swing.*;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private JButton firstButton;
	private JTextField firstTextField;
	private SpringLayout baseLayout;

	public ChatPanel(ChatController baseController)
	{
		this.baseController = baseController;

		baseLayout = new SpringLayout();
		firstButton = new JButton("Press Button");
		firstTextField = new JTextField("Type Here");

		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupListeners()
	{
		
	}

	private void setupLayout()
	{
		
	}

	private void setupPanel()
	{
		
	}
}
