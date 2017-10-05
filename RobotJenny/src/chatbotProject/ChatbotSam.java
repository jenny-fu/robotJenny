package chatbotProject;

public class ChatbotSam implements Topic{

	private String[] keywords;
	private String[] compliments;
	private String[] insults;
	private String[] complimentReplies;
	private String[] insultReplies;
	private String[] repeatReplies;
	private String[] flirtyReplies;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;
	private static int complimentScore;
	private int repeatScore;
	private int replyNumber;
	private int lastInsult;
	private int replyNumber2;
	private int lastCompliment;
	private int replyFlirts;
	private int lastFlirt;
	private String lastResponse;

	public ChatbotSam() {
		String[] temp = {"pretty", "beautiful", "gorgeous", "sexy", "ugly", "fat", "disgusting","hideous", "cute","lovely", "amazing", "nice", "kind", "smart", "nasty", "dumb", "stupid", "idiot", "cheese", "hate you", "hot", "cool", "awesome", "perfect"};
		keywords = temp;
		String[] temp2 = {"pretty", "beautiful", "gorgeous", "sexy", "cute", "lovely", "amazing", "nice", "kind", "smart", "hot", "cool", "awesome", "perfect"};
		compliments = temp2;
		String[] temp3 = {"ugly", "fat", "disgusting", "hideous","nasty","dumb","stupid","idiot","cheese", "hate you"};
		insults = temp3;
		String[] temp4 = {"Aw, thank you so much!", "You're so sweet!", "Stop it, I'm blushing!", "Keep on complimenting me please.", "You wouldn't mind saying that again would you?", "I know", "Do you mean it?"};
		complimentReplies = temp4;
		String[] temp5 = {"Deadass b.", "Say that again and see what happens.","Don't talk so bad about yourself.","So rude!","You jerk!", "Ay watch your mouth before I smack you aight.","I know you are but what am I."};
		insultReplies = temp5;
		String[] temp6 = {"Seriously stop saying that.", "Is that all you can say.", "I'm tired of hearing you say that.", "Boring."};
		repeatReplies = temp6;
		String[] temp7 = {"Hey I just met you and this crazy, but here's my number so call me maybe! ;)", "Don't you think we should hang out sometimes?", "I'm starting to like you a bit.", "You're starting to look pretty cute yourself ;)"};
		flirtyReplies = temp7;
		goodbyeWord = "bye";
		secretWord = "exo";
		complimentScore = 0;
		repeatScore = 0;
		replyNumber = -1;
		replyNumber2 =-1;
		lastInsult = -1;
		lastCompliment = -1;
		replyFlirts = -1;
		lastFlirt = -1;
		lastResponse = "";
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
	public void givenCompliment(String response) {
		while(replyNumber2 == lastCompliment) {
			replyNumber2 = (int) Math.round(Math.random()*(complimentReplies.length-2));
		}
		ChatbotMain.print(complimentReplies[replyNumber2]);
		lastCompliment = replyNumber2;
		repeatScore = 0;
		complimentScore++;
		if(replyNumber == 6) {
			response = ChatbotMain.getInput();
			if(ChatbotMain.findKeyword(response, "yes", 0) >=0) {
				replyNumber2 = (int) Math.round(Math.random()*(complimentReplies.length-2));
				ChatbotMain.print(complimentReplies[replyNumber2]);
			}
			else if(ChatbotMain.findKeyword(response, "no", 0) >=0){
				ChatbotMain.print("Don't lie to me like that!");
				complimentScore--;
			}
		}
	}
	public void givenInsults(String response) {
		complimentScore = complimentScore - 3;
		if(ChatbotMain.findKeyword(response, "cheese", 0)>=0) {
			ChatbotMain.print("Don't ever say cheese around me");
		}
		else {
			while(replyNumber == lastInsult) {
				replyNumber = (int) Math.round(Math.random()*(insultReplies.length-1));
			}
			ChatbotMain.print(insultReplies[replyNumber]);
			lastInsult = replyNumber;
			repeatScore = 0;
		}
	}
	public void repitition(String response) {
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
	public static int getComplimentScore() {
		return complimentScore;
	}

	public void startChatting(String response) {
		if(isTriggeredCompliments(response)) {
			givenCompliment(response);
		}
		else if(isTriggeredInsults(response)) {
			givenInsults(response);
		}
		lastResponse = response;
		chatting = true;
		while(chatting) {
			response = ChatbotMain.getInput();
			if(lastResponse.toLowerCase().equals(response.toLowerCase())) {
				repitition(response);
			}
			else if(ChatbotMain.chatbot.getErik().isTriggered(response)) {
				ChatbotMain.chatbot.getErik().startChatting(response);
			}
			else if(ChatbotMain.chatbot.getJenny().isTriggered(response)) {
				ChatbotMain.chatbot.getJenny().startChatting(response);
			}
			else if(ChatbotMain.findKeyword(response, goodbyeWord, 0)>=0) {
				chatting = false;
				ChatbotMain.chatbot.startTalkingAgain();
				repeatScore = 0;
			}
			else if(ChatbotMain.findKeyword(response, secretWord, 0)>=0) {
				ChatbotMain.print("I LOVE EXO OMGGGGG");
				repeatScore = 0;
			}
			else if(complimentScore >= 4 && complimentScore <= 6 && isTriggeredCompliments(response)) {
				complimentScore++;
				while(replyFlirts == lastFlirt) {
					replyFlirts = (int) Math.round(Math.random()*(flirtyReplies.length-2));
				}
				ChatbotMain.print(flirtyReplies[replyFlirts]);
				lastFlirt = replyFlirts;
				repeatScore = 0;
			}
			else if(isTriggeredCompliments(response)) {
				givenCompliment(response);
			}
			else if(isTriggeredInsults(response)) {
				givenInsults(response);
			}
			else {
				ChatbotMain.print("Not sure what you mean, can you say something else.");
				repeatScore = 0;
			}
			lastResponse = response;
		}
	}

	public static String getUser(String input, int startPsn) {
		if (startPsn == 0 && ChatbotMain.findKeyword(input, " ", 0) == -1){
			return input;
		}
		if(ChatbotMain.findKeyword(input, "name is", 0) >= 0){
			return input.substring(ChatbotMain.findKeyword(input, "name is", 0)+8);
		}
		return input;
	}

}