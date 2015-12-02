package chat.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import chat.controller.ChatController;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JButton submitButton;
	private JTextArea chatArea;
	private JTextField typingField;
	private JLabel promptLabel;
	private JLabel pictureLabel;
	private ImageIcon chatIcon;


	public JTextField getTypingField()
	{
		return typingField;
	}

	public ChatPanel(ChatController baseController)
	{
		chatIcon = new ImageIcon (getClass().getResource("images/chatbot.png"));
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatArea = new JTextArea(10,30);
		typingField = new JTextField(40);
		promptLabel = new JLabel("Hello, I'm Caliban");
		submitButton = new JButton("Press Button");
		pictureLabel = new JLabel(chatIcon);
		
		

		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.EAST, chatArea, -43, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, typingField, 145, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, typingField, -43, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, promptLabel, 161, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, promptLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatArea, 24, SpringLayout.EAST, promptLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, pictureLabel, -6, SpringLayout.NORTH, promptLabel);
		baseLayout.putConstraint(SpringLayout.EAST, pictureLabel, 0, SpringLayout.EAST, promptLabel);
		baseLayout.putConstraint(SpringLayout.EAST, submitButton, -43, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, typingField, -6, SpringLayout.NORTH, submitButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, submitButton, -25, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatArea, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatArea, -114, SpringLayout.SOUTH, this);
	}
	
	private void setupListeners()
	{
		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userText = typingField.getText();
				chatArea.append("\nUser: " + userText);
				typingField.setText("");
				String response = baseController.userToChatbot(userText);
				chatArea.append("\nChatbot: " + response);
			}
		});
	}

	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GREEN);
		this.add(chatArea);
		this.add(typingField);
		this.add(submitButton);
		this.add(promptLabel);
		this.add(pictureLabel);
		typingField.setToolTipText("Type here for the chatbot.");
		chatArea.setEnabled(false);
	}
}
