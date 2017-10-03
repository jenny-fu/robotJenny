package chatbotProject;

public class ChatbotSam implements Topic{

	private String[] keywords;
	private String[] compliments;
	private String[] insults;
	private String[] complimentReplies;
	private String[] insultReplies;
	private String[] repeatReplies;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;
	private static int complimentScore;
	private int repeatScore;
	
	public ChatbotSam() {
		String[] temp = {"pretty", "beautiful", "gorgeous", "sexy", "ugly", "fat", "disgusting","hideous", "you", "cute","lovely"};
		keywords = temp;
		String[] temp2 = {"pretty", "beautiful", "gorgeous", "sexy", "cute", "lovely"};
		compliments = temp2;
		String[] temp3 = {"ugly", "fat", "disgusting", "hideous","nasty"};
		insults = temp3;
		String[] temp4 = {"Aw, thank you so much!", "You're so sweet!", "Stop it, I'm blushing!", "Keep on complimenting me please.", "You wouldn't mind saying that again would you?","Do you mean it?"};
		complimentReplies = temp4;
		String[] temp5 = {"Deadass b.", "Say that again and see what happens.","Don't talk so bad about yourself.","So rude!","You jerk!", "Ay watch your mouth before I smack you aight."};
		insultReplies = temp5;
		String[] temp6 = {"Seriously stop saying that.", "Is that all you can say.", "I'm tired of hearing you say that.", "Boring.",};
		repeatReplies = temp6;
		goodbyeWord = "bye";
		secretWord = "exo";
		complimentScore = 0;
		repeatScore = 0;
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
	public static int getComplimentScore() {
		return complimentScore;
	}

	public void startChatting(String response) {
		ChatbotMain.print("That's an interesting thing to say to me.");
		chatting = true;
		String lastResponse = "";
		while(chatting) {
			response = ChatbotMain.getInput();
			if(lastResponse.toLowerCase().equals(response.toLowerCase())) {
				ChatbotMain.print("You said that already.");
				repeatScore++;
				while(repeatScore > 0) {
					if(repeatScore > 5) {
						ChatbotMain.print("I'll wait for you to say something else.");
					}
					else if(repeatScore > 1) {
						int repeatReplyNumber = (int) Math.round(Math.random()*(repeatReplies.length-1));
						ChatbotMain.print(repeatReplies[repeatReplyNumber]);
					}
					response = ChatbotMain.getInput();
					if(lastResponse.toLowerCase().equals(response.toLowerCase())) {
						repeatScore++;
					}
					else {
						repeatScore= 0;
						ChatbotMain.print("You finally said something else.");
					}
				}
			}
			else if(ChatbotMain.findKeyword(response, goodbyeWord, 0)>=0) {
				chatting = false;
				ChatbotMain.chatbot.startTalking();
				repeatScore = 0;
			}
			else if(ChatbotMain.findKeyword(response, secretWord, 0)>=0) {
				ChatbotMain.print("Oh my goodness! You guessed my favorite thing ever. We are friends now.");
				repeatScore = 0;
			}
			else if(isTriggeredCompliments(response)) {
				int replyNumber = (int) Math.round(Math.random()*(complimentReplies.length-1));
				ChatbotMain.print(complimentReplies[replyNumber]);
				complimentScore++;
				repeatScore = 0;
				if(replyNumber == 5) {
					response = ChatbotMain.getInput();
					if(ChatbotMain.findKeyword(response, "yes", 0) >=0) {
						int replyNumber2 = (int) Math.round(Math.random()*(complimentReplies.length-2));
						ChatbotMain.print(complimentReplies[replyNumber2]);
					}
					else if(ChatbotMain.findKeyword(response, "no", 0) >=0){
						ChatbotMain.print("Don't lie to me like that");
					}
				}
			}
			else if(isTriggeredInsults(response)) {
				int replyNumber = (int) Math.round(Math.random()*(insultReplies.length-1));
				ChatbotMain.print(insultReplies[replyNumber]);
				complimentScore = complimentScore - 3;
				repeatScore = 0;
			}
			else {
				ChatbotMain.print("Huh. I don't really get you. Tell me something else");
				repeatScore = 0;
			}
			lastResponse = response;
		}
	}

}
