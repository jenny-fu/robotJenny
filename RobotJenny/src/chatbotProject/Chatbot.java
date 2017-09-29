package chatbotProject;


public class Chatbot {
	private String userName;
	private Topic jenny;
	private boolean chatting;

	public Chatbot() {
		jenny = new ChatbotJenny();
		userName = "unknown user";
		chatting = true;
	}
	
	public void startTalking() {
		ChatbotMain.print("Welcome to our chatbot! What is your name?");
		userName = ChatbotMain.getInput();
		chatting = true;
		while(chatting) {
			ChatbotMain.print("What do you want to talk about?");
			String response = ChatbotMain.getInput();
			if(jenny.isTriggered(response)) {
				chatting = false;
				jenny.startChatting(response);
			}
			else
				ChatbotMain.print("I'm sorry. I don't understand.");
		}
	}

}
