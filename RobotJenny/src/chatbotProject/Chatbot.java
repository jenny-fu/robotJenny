package chatbotProject;


public class Chatbot {
	private String userName;
	private String user;
	private Topic jenny;
	private Topic sam;
	private Topic erik;
	private boolean chatting;

	public Chatbot() {
		jenny = new ChatbotJenny();
		sam = new ChatbotSam();
		erik = new ChatbotErik();
		chatting = true;
	}

	public void startTalking() {
		ChatbotMain.print("Hi my name is Jenny! What's your name?");
		userName = ChatbotMain.getInput();
		user = ChatbotSam.getUser(userName,0);
		chatting = true;
		ChatbotMain.print("Hey " + user + ", what do you want to talk about?");
		while(chatting) {
			String response = ChatbotMain.getInput();
			if(getJenny().isTriggered(response)) {
				chatting = false;
				jenny.startChatting(response);
			}
			else if(sam.isTriggered(response)) {
					chatting = false;
					sam.startChatting(response);
			}
			else if(erik.isTriggered(response)) {
						chatting = false;
						erik.startChatting(response);
			}
			else
				ChatbotMain.print("I'm sorry. I don't understand.\nTry something else.");
		}
	}
	public void startTalkingAgain() {
		ChatbotMain.print("Say something if you want to talk again.");
		chatting = true;
		while(chatting) {
			ChatbotMain.print("What do you want to talk about?");
			String response = ChatbotMain.getInput();
			if(getJenny().isTriggered(response)) {
				chatting = false;
				jenny.startChatting(response);
			}
			else if(sam.isTriggered(response)) {
					chatting = false;
					sam.startChatting(response);
			}
			else if(getErik().isTriggered(response)) {
						chatting = false;
						erik.startChatting(response);
			}
			else
				ChatbotMain.print("I'm sorry. I don't understand.");
		}
	}

	public Topic getErik() {
		return erik;
	}
	public Topic getJenny() {
		return jenny;
	}
	public Topic getSam() {
		return sam;
	}
}
