
package chat.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ChatbotTest.class, 
	ChatControllerTest.class, 
	ChatFrameTest.class, 
	ChatPanelTest.class })
public class ChatTests
{

}
