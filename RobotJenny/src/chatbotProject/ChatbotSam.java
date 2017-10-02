package chatbotProject;

public class ChatbotSam implements Topic{

	private String[] keywords;
	private String[] compliments;
	private String[] insults;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;
	
	public ChatbotSam() {
		String[] temp = {"pretty", "beautiful", "gorgeous", "sexy", "ugly", "fat", "disgusting"};
		keywords = temp;
		String[] temp2 = {"pretty", "beautiful", "gorgeous", "sexy"};
		compliments = temp2;
		String[] temp3 = {"ugly", "fat", "disgusting"};
		insults = temp3;
		goodbyeWord = "bye";
		secretWord = "exo";
	}
	public boolean isTriggered(String response) {
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >=0) {
				return true;
			}
		}
		return false;
	}
	public boolean isTriggeredCompliments(String response) {
		for(int i = 0; i < compliments.length; i++) {
			if(ChatbotMain.findKeyword(response, compliments[i], 0) >=0) {
				return true;
			}
		}
		return false;
	}
	public boolean isTriggeredInsults(String response) {
		for(int i = 0; i < insults.length; i++) {
			if(ChatbotMain.findKeyword(response, insults[i], 0) >=0) {
				return true;
			}
		}
		return false;
	}

	public void startChatting(String response) {
		ChatbotMain.print("That's an interesting thing to say to me.");
		chatting = true;
		while(chatting) {
			response = ChatbotMain.getInput();
			if(ChatbotMain.findKeyword(response, goodbyeWord, 0)>=0) {
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			}
			else if(ChatbotMain.findKeyword(response, secretWord, 0)>=0) {
				ChatbotMain.print("Oh my goodness! You guessed my favorite thing ever. We are friends now.");	
			}
			else if(isTriggeredCompliments(response)) {
				ChatbotMain.print("Why thank you very much.");
			}
			else if(isTriggeredInsults(response)) {
				ChatbotMain.print("Ay watch your mouth before I smack you.");
			}
			else {
				ChatbotMain.print("Huh. I don't really get you. Tell me something else");
			}
		}
	}

}
