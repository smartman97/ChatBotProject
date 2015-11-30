package chat.view;

import java.awt.Color;
import chat.controller.ChatController;
import javax.swing.*;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JButton submitButton;
	private JTextArea chatArea;
	private JTextField typingField;
	private JLabel promptLabel;

	public JTextField getTypingField()
	{
		return typingField;
	}

	public ChatPanel(ChatController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatArea = new JTextArea(10,30);
		typingField = new JTextField(30);
		promptLabel = new JLabel("Chat with me");
		submitButton = new JButton("Press Button");

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
		this.setLayout(baseLayout);
		this.setBackground(Color.MAGENTA);
		this.add(chatArea);
		this.add(typingField);
		this.add(submitButton);
		this.add(promptLabel);
		typingField.setToolTipText("Type here for the chatbot.");
		chatArea.setEnabled(false);
	}
}
