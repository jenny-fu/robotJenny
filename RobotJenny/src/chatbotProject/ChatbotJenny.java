package chatbotProject;

public class ChatbotJenny implements Topic {
	
	private String[] keywords;
	private String[] question;
	private String[] excuse;
	private String[] confessions;
	private String[] reject;
	private String goodbyeWord;
	private String secretWord;
	private boolean accepted;
	private boolean chatting;
	private Topic erik2;
	private Topic sam2;

	public ChatbotJenny() {
		String[] temp = {"date", "free", "hang out", "dating"};
		String[] temp2 = {"be my girlfriend", "be your boyfriend", "like you", "love you"};
		String[] temp3 = {"why not", "why", "why don't", "how come"};
		String[] temp4 = {"I have a lot of homework today.", "I have work.", "I have some family business I need to go to."};
		String[] temp5 = {"Sorry, I don't want to be in a relationship at the moment.", "I am not interested in you.", "I am not looking for a boyfriend right now..."};
		keywords = temp;
		confessions = temp2;
		question = temp3;
		excuse = temp4;
		reject = temp5;
		goodbyeWord = "bye";
		secretWord = "EXO";
		erik2 = new ChatbotErik();
		sam2 = new ChatbotSam();
	}

	public boolean isTriggered(String response) {
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0)
				return true;
		}
		
		for(int j = 0; j < confessions.length; j++) {
			if(ChatbotMain.findKeyword(response, confessions[j], 0) >= 0)
				return true;
		}
		return false;
	}
	
	public boolean questioned(String response) {
		for(int i = 0; i < question.length; i++) {
			if(ChatbotMain.findKeyword(response, question[i], 0) >= 0) {
				return true;
			}
		}	
		return false;
	}
	
	public boolean confessed(String response) {
		for(int i = 0; i < confessions.length; i++) {
			if(ChatbotMain.findKeyword(response, confessions[i], 0) >= 0) {
				return true;
			}
		}	
		return false;
	}
//getComplimentScore 8-11;
	public void startChatting(String response) {
		for(int j = 0; j < confessions.length; j++) {
			if(ChatbotMain.findKeyword(response, confessions[j], 0) >= 0)
				ChatbotMain.print("Oh...");
		}
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0)
				ChatbotMain.print("No, sorry.");
		}
		chatting = true;
		while(chatting) {
			response = ChatbotMain.getInput();
			if(erik2.isTriggered(response)) {
				chatting = false;
				erik2.startChatting(response);
			}
			else if(sam2.isTriggered(response)) {
				chatting = false;
				sam2.startChatting(response);
			}else
			if(questioned(response)) {
				int idx = (int) Math.floor(Math.random() * excuse.length);
				ChatbotMain.print(excuse[idx]);
			}else
				if(confessed(response)) {
					int index = (int) Math.floor(Math.random() * reject.length);
					ChatbotMain.print(reject[index]);
				}else
					if(ChatbotMain.findKeyword(response, goodbyeWord, 0) >= 0) {
						chatting = false;
						ChatbotMain.chatbot.startTalkingAgain();
					}else
						if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
							ChatbotMain.print("OMG that's my favorite thing ever! Are you free this week?");
							accepted = true;
							while(accepted) {
		///////////////////////////////////////////////////////////////////						
							}
						}else
							ChatbotMain.print("Huh. I don't really get you. Tell me something else.");
		}
	}

}
