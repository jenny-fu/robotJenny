package chatbotProject;

public class ChatbotSam implements Topic{

	private String[] keywords;
	private String[] compliments;
	private String[] insults;
	private String[] complimentReplies;
	private String[] insultReplies;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;
	
	public ChatbotSam() {
		String[] temp = {"pretty", "beautiful", "gorgeous", "sexy", "ugly", "fat", "disgusting","hideous", "you"};
		keywords = temp;
		String[] temp2 = {"pretty", "beautiful", "gorgeous", "sexy"};
		compliments = temp2;
		String[] temp3 = {"ugly", "fat", "disgusting", "hideous"};
		insults = temp3;
		String[] temp4 = {"Aw, thank you so much!", "You're so sweet!", "Stop it, I'm blushing!", "Keep on complimenting me please.", "You wouldn't mind saying that again would you?","Do you mean it"};
		complimentReplies = temp4;
		String[] temp5 = {"Deadass b.", "Say that again and see what happens.","Don't talk so bad about yourself.","So rude!","You jerk!", "Ay watch your mouth before I smack you"};
		insultReplies = temp5;
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
				int replyNumber = (int) Math.round(Math.random()*complimentReplies.length);
				ChatbotMain.print(complimentReplies[replyNumber]);
			}
			else if(isTriggeredInsults(response)) {
				int replyNumber = (int) Math.round(Math.random()*insultReplies.length);
				ChatbotMain.print(insultReplies[replyNumber]);
			}
			else {
				ChatbotMain.print("Huh. I don't really get you. Tell me something else");
			}
		}
	}

}
