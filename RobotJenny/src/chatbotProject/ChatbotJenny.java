package chatbotProject;

public class ChatbotJenny implements Topic {
	
	private String[] keywords;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;

	public ChatbotJenny() {
		String[] temp = {"food", "Internet", "entertainment"};
		keywords = temp;
		goodbyeWord = "bye";
		secretWord = "nanana";
	}

	public boolean isTriggered(String response) {
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0)
				return true;
		}
		return false;
	}

	public void startChatting(String response) {
		ChatbotMain.print("Hey! It sounds like you and I have a common interest! Let's talk some more!");
		chatting = true;
		while(chatting) {
			response = ChatbotMain.getInput();
			if(ChatbotMain.findKeyword(response, goodbyeWord, 0) >= 0) {
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			}else
				if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
					ChatbotMain.print("OMG you guessed my favorite thing ever. We are friends now!");
				}else
					ChatbotMain.print("Huh. I don't really get you. Tell me something else");
		}
	}

}
