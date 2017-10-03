package chatbotProject;

public class ChatbotErik implements Topic {

	private String[] topics;
	private String[] neutralResponses;
	private boolean regTopics = false;
	private String[] litTopics; 
	private String[] interestResponses;
	private boolean aweTopics = false;
	private String[] repeatResponses;
	//private String[] questionsForMe;
	private String[] goodbyeWords;
	private String secretWord;
	private boolean chatting;
	private int topicTrigger;
	private boolean angry = false;
	private boolean flirty = false;
	private String lastResponse = "";
	private Topic jenny3;
	private Topic sam3;
	
	
	
	public ChatbotErik() {
		String[] temp = {"entertainment","video games","hobby", "sport","life","anything","interests","internet"};
		topics = temp;
		String[] temp2 = {"okay","that's cool","oh, really","mhmm","uh-huh", "im totally paying attention to you", "woahhhh -snores-"};
		neutralResponses = temp2;
		String[] temp3 = {"food", "summer", "weekends"};
		litTopics = temp3;
		String[] temp4 = {"Woahhh","Tell me more","Wow!","Im interested"};
		interestResponses  = temp4;
		String[] temp5 = {"You're being annoying.","Is that all you can say?","Such extensive vocabulary","Are you having fun?"};
		repeatResponses = temp5;
		String[] goodbyeStrings = {"bye", "goodbye", "see you later"};
		goodbyeWords = goodbyeStrings;
		secretWord = "Exo";
	}

	public boolean isTriggered(String response) {
		for(int i = 0; i < topics.length; i++) {
			if((ChatbotMain.findKeyword(response, topics[i], 0) >= 0)) {
				lastResponse = response;
				topicTrigger = i;
				regTopics = true;
				return true;
			}
		}
		for(int j = 0; j < litTopics.length; j++){
			if((ChatbotMain.findKeyword(response, litTopics[j], 0) >= 0)){
				lastResponse = response;
				topicTrigger = j;
				aweTopics = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean isTriggeredRegularTopics(String response) {
		for(int i = 0; i < topics.length; i++) {
			if((ChatbotMain.findKeyword(response, topics[i], 0) >= 0)){
				topicTrigger = i;
				return true;
			}
		}
		return false;
	}

	public boolean isTriggeredLitTopics(String response) {
		for(int i = 0; i < litTopics.length; i++) {
			if(ChatbotMain.findKeyword(response, litTopics[i], 0) >= 0){
				topicTrigger = i;
				return true;
			}
		}
		return false;
	}
	
	public boolean isTriggeredGoodbye(String response) {
		for(int i = 0; i < goodbyeWords.length; i++) {
			if(ChatbotMain.findKeyword(response, goodbyeWords[i], 0) >= 0){
				return true;
			}
		}
		return false;
	}
	
	public void startChatting(String response) {
		String topic = "";
		int numberOfNeutral = 0;
		int numberOfInterest = 0;
		int numberOfRepeat = 0;
		int randomIndex = 0;
		boolean forceChange = false;
		if(regTopics) {
			aweTopics = false;
		}
		if(aweTopics) {
			regTopics = false;
		}
		ChatbotMain.print("Let's talk some more about that! What about it?");
		chatting = true;
		while(chatting) {
			int stayOnTopic = topicTrigger;
			response = ChatbotMain.getInput();
			if(isTriggeredGoodbye(response)) {
				chatting = false;
				ChatbotMain.chatbot.startTalkingAgain();
			}else if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
				lastResponse = response;
				numberOfRepeat = 0;
				ChatbotMain.print("Oh my goodness! You guessed my favorite things ever. We are friends now.");
			}else if(lastResponse.equals(response)) {
				lastResponse = response;
				numberOfRepeat++;
				regTopics = false;
				aweTopics = false;
				if(numberOfRepeat > 4) {
					ChatbotMain.print("Say something else.");
				}else if(numberOfRepeat > 2) {
					randomIndex = (int) Math.floor(Math.random()*repeatResponses.length);
					ChatbotMain.print(repeatResponses[randomIndex]);
				} else {
					ChatbotMain.print("Why are you repeating yourself?");
					}
				} else if(ChatbotMain.chatbot.getSam().isTriggered(response)) {
					ChatbotMain.chatbot.getSam().startChatting(response);
				}
				else if(ChatbotMain.chatbot.getJenny().isTriggered(response)) {
					ChatbotMain.chatbot.getJenny().startChatting(response);
				} else
				
					
				for(int i = 0; i < topics.length; i++) {
					if(ChatbotMain.findKeyword(response, topics[i], 0) >= 0){
						topicTrigger = i;
						}
					}
					for(int i = 0; i < litTopics.length; i++) {
						if(ChatbotMain.findKeyword(response, litTopics[i], 0) >= 0){
							topicTrigger = i;
						}
					}		
					
				if(regTopics && topicTrigger == stayOnTopic) {
				lastResponse = response;
				numberOfRepeat = 0;
				randomIndex = (int) Math.floor(Math.random()*neutralResponses.length);
				ChatbotMain.print(neutralResponses[randomIndex]);
				numberOfNeutral++;
				if(numberOfNeutral > 2) {
					regTopics = false;
					forceChange = true;
				}
			}else if(aweTopics && topicTrigger == stayOnTopic) {
				lastResponse = response;
				numberOfRepeat = 0;
				randomIndex = (int) Math.floor(Math.random()*interestResponses.length);
				ChatbotMain.print(interestResponses[randomIndex]);
				numberOfInterest++;
				if(numberOfInterest > 2) {
					aweTopics = false;
					forceChange = true;
				}
			}else if(stayOnTopic != topicTrigger || forceChange) {
				lastResponse = response;
				numberOfRepeat = 0;
				forceChange = false;
				numberOfNeutral = 0;
				numberOfInterest = 0;
				ChatbotMain.chatbot.startTalkingAgain();
			}else if(!regTopics || !aweTopics){
				ChatbotMain.print("Im bringing you back to talk about something else.");
				ChatbotMain.chatbot.startTalkingAgain();
			}else
			{
				ChatbotMain.print("Huh, I don't really get you. Tell me something else?");
			}
		}
	}

	
}
