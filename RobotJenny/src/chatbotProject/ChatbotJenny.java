package chatbotProject;

public class ChatbotJenny implements Topic {

	private String[] date;
	private String[] question;
	private String[] excuse;
	private String[] confessions;
	private String[] reject;
	private String goodbyeWord;
	private String secretWord;
	private boolean askDate;
	private boolean confess;
	private boolean accepted;
	private boolean chatting;
	private int resCount;

	public ChatbotJenny() {
		String[] temp = {"date", "free", "hang out", "dating"};
		String[] temp2 = {"be my girlfriend", "be your boyfriend", "like you", "love you"};
		String[] temp3 = {"why not", "why", "why don't", "how come"};
		String[] temp4 = {"I have a lot of homework today.", "I have work.", "I have some family business I need to go to."};
		String[] temp5 = {"Sorry, I don't want to be in a relationship at the moment.", "I am not interested in you.", "I am not looking for a boyfriend right now..."};
		date = temp;
		confessions = temp2;
		question = temp3;
		excuse = temp4;
		reject = temp5;
		goodbyeWord = "bye";
		secretWord = "EXO";
		resCount = 0;
		askDate = false;
		confess = false;
	}

	public boolean isTriggered(String response) {
		if(askedOut(response))
			return true;
		else
			if(confessed(response))
				return true;
		return false;
	}

	public boolean askedOut(String response){
		for(int i = 0; i < date.length; i++) {
			if(ChatbotMain.findKeyword(response, date[i], 0) >= 0) {
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
	
	public boolean questioned(String response) {
		for(int i = 0; i < question.length; i++) {
			if(ChatbotMain.findKeyword(response, question[i], 0) >= 0) {
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
		for(int i = 0; i < date.length; i++) {
			if(ChatbotMain.findKeyword(response, date[i], 0) >= 0)
				ChatbotMain.print("No, sorry.");
		}
		chatting = true;
		String responseBefore = "";
		while(chatting) {
			int index = (int) Math.floor(Math.random() * reject.length);
			int idx = (int) Math.floor(Math.random() * excuse.length);
			response = ChatbotMain.getInput();
			
			if(askedOut(response)) {
				askDate = true;
				confess = false;
			}
			if(confessed(response)) {
				confess = true;
				askDate = false;
			}
			
			if(responseBefore.toLowerCase().equals(response.toLowerCase())) {
				ChatbotMain.print("I thought I told you already.");
				resCount++;
				while(resCount > 0) {
					if(resCount == 3) {
						ChatbotMain.print("Are you even listening to me??");
					}else if(confess){
						ChatbotMain.print("I SAID " + reject[index]);
					}else if(askDate) {
						ChatbotMain.print("I already said that " + excuse[idx]);
					}
					response = ChatbotMain.getInput();
					if(responseBefore.toLowerCase().equals(response.toLowerCase()))
						resCount++;
					else {
						resCount = 0;
						ChatbotMain.print("What a waste of my time.");
					}
				}
			}else if(ChatbotMain.chatbot.getErik().isTriggered(response)) {
				ChatbotMain.chatbot.getErik().startChatting(response);
			}else if(ChatbotMain.chatbot.getSam().isTriggered(response)) {
				ChatbotMain.chatbot.getSam().startChatting(response);
			}else if(ChatbotSam.getComplimentScore() == 8 && askDate) {

			}else if(ChatbotSam.getComplimentScore() == 11 && confess) {

			}else if(questioned(response)) {
				ChatbotMain.print(excuse[idx]);
			}else if(confessed(response)) {
				ChatbotMain.print(reject[index]);
			}else if(ChatbotMain.findKeyword(response, goodbyeWord, 0) >= 0) {
				chatting = false;
				ChatbotMain.chatbot.startTalkingAgain();
			}else if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
				ChatbotMain.print("OMG that's my favorite thing ever! Are you free this week?");
				accepted = true;
				while(accepted) {
					///////////////////////////////////////////////////////////////////						
				}
			}else
				ChatbotMain.print("Huh. I don't really get you. Tell me something else.");
			responseBefore = response;
		}
	}

}
