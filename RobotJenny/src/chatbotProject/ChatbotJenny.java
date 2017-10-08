package chatbotProject;

public class ChatbotJenny implements Topic {

	private String[] date;
	private String[] question;
	private String[] excuse;
	private String[] confessions;
	private String[] reject;
	private String[] repeat;
	private String[] accept;
	private String[] beforeReject;
	private String goodbyeWord;
	private String secretWord;
	private boolean askDate;
	private boolean confess;
	private boolean chatting;
	private int resCount;
	private int confessionIdx;

	public ChatbotJenny() {
		String[] temp = {"date", "free", "hang out", "dating", "do you want to go to", "let's go to"};
		String[] temp2 = {"be my girlfriend", "be your boyfriend", "like you", "love you", "marry me", "go out with me", "go out"};
		String[] temp3 = {"why not", "why", "why don't", "how come"};
		String[] temp4 = {"I have a lot of homework today.", "I have work.", "I have some family business I need to go to."};
		String[] temp5 = {"I don't want to be in a relationship at the moment.", "I am not interested in you.", "I am not looking for a boyfriend right now.",
				"I need some time before getting into another relationship.", "I don't want to get into a relationship with someone I don't know."};
		String[] temp6 = {"Um, are you listening to me?", "Are you reading what I have to say?", "Hello???", "What is wrong with you?", "Ugh! I hate people like you!"};
		String[] temp7 = {"I would love to be your girlfriend!", "I wouldn't mind if you were my boyfriend.", "I like you too!", "I love you too!",
				"I don't want to get married right now, but I can accept you as my boyfriend!", "Aww I'll accept you confession!", "We are official now. Don't let me catch you staring at other girls!"};
		String[] temp8 = {"I just", "I'm just"};
		date = temp;
		confessions = temp2;
		question = temp3;
		excuse = temp4;
		reject = temp5;
		repeat = temp6;
		accept = temp7;
		beforeReject = temp8;
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
				confessionIdx = i;
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

	
	public void startChatting(String response) {
		String responseBefore = "";
		int index = (int) Math.floor(Math.random() * reject.length);
		int idx = (int) Math.floor(Math.random() * excuse.length);
		if(ChatbotSam.getComplimentScore() < 4 || ChatbotSam.getComplimentScore() > 6) {
			for(int j = 0; j < confessions.length; j++) {
				if(ChatbotMain.findKeyword(response, confessions[j], 0) >= 0) {
					ChatbotMain.print(reject[index]);
					confess = true;
					askDate = false;
				}
			}
			for(int i = 0; i < date.length; i++) {
				if(ChatbotMain.findKeyword(response, date[i], 0) >= 0) {
					ChatbotMain.print("No, sorry.");
					confess = false;
					askDate = true;
				}
			}
		}else if(askedOut(response)) {
			responseBefore = response;
				ChatbotMain.print("Sure, I'll go on a date with you! Where do you want to go?");
				response = ChatbotMain.getInput();
				if(ChatbotMain.findKeyword(response, "do you", 0) >= 0) {
					ChatbotMain.print("Why do you want to go to " + location(response,0) + "?");
				}else 
					ChatbotMain.print("Sure, let's go to " + location(response,0) + "! I'll go get ready. Bye!");
					ChatbotMain.chatbot.startTalkingAgain();
		}else if(confessed(response)) {
			responseBefore = response;
			if(ChatbotSam.getComplimentScore() == 6) {
				ChatbotMain.print(accept[confessionIdx]);
				ChatbotMain.print("Oh! I just remembered I had to do something. I'll see you next time!");
				ChatbotMain.chatbot.startTalkingAgain();
			}else if(ChatbotSam.getComplimentScore() < 6)
				ChatbotMain.print("Oh...um, sorry, try again after a bit.");
				else
					ChatbotMain.print("Oh...but you're like my best friend!");
		}else if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
			
		}
			
		chatting = true;
		while(chatting) {
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
				int i = 0;
				while(resCount > 0) {
					if(i > repeat.length - 1) {
						ChatbotMain.print("I won't talk to you until you say something else.");
					}else if(resCount%2 == 0) {
						ChatbotMain.print(repeat[i]);
						i++;
					}else if(confess && resCount > 1){
						ChatbotMain.print("I SAID that " + reject[index]);
					}else if(askDate && resCount > 1) {
						ChatbotMain.print("I said that " + excuse[idx]);
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
			}else if(questioned(response)) {
				if(askDate)
					ChatbotMain.print(excuse[idx]);
				else if(confess) {
					if(index == 1 || index == 2) {
						String rejection = reject[index].substring(5);
						ChatbotMain.print(beforeReject[1] + " " + rejection);
					}else {
						String rejection = reject[index].substring(2);
						ChatbotMain.print(beforeReject[0] + " " + rejection);
					}	
				}
			}else if(ChatbotMain.findKeyword(response, goodbyeWord, 0) >= 0) {
				chatting = false;
				ChatbotMain.chatbot.startTalkingAgain();
			}else
				ChatbotMain.print("Huh. I don't really get you. Tell me something else.");
			responseBefore = response;
		}
	}
	
	public String location(String response, int psn) {
		if(psn == 0 && response.indexOf(" ") == -1){
			return response;
		}
		else if(ChatbotMain.findKeyword(response, "go to", 0) >= 0) {
			int position = ChatbotMain.findKeyword(response, "go to", 0);
			String newString = response.substring(position + 6);
			return newString;
		}
		return response;
	}
	
}
