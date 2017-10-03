package chatbotProject;

public class ChatbotErik implements Topic {

	private String[] topics;
	private String[] neutralResponses;
	private boolean regTopics = false;
	private String[] litTopics; 
	private String[] interestResponses;
	private boolean aweTopics = false;
	//private String[] questionsForMe;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;
	private int topicTrigger;
	
	public ChatbotErik() {
		String[] temp = {"entertainment","video games","hobby", "sport","life","anything","interests","internet"};
		topics = temp;
		String[] temp2 = {"okay","that's cool","oh, really","mhmm","uh-huh", "im totally paying attention to you", "woahhhh -snores-"};
		neutralResponses = temp2;
		String[] temp3 = {"food", "summer", "weekends"};
		litTopics = temp3;
		String[] temp4 = {"Woahhh","Tell me more","Wow!","Im interested"};
		interestResponses  = temp4;
		goodbyeWord = "bye";
		secretWord = "Exo";
	}

	public boolean isTriggered(String response) {
		for(int i = 0; i < topics.length; i++) {
			if((ChatbotMain.findKeyword(response, topics[i], 0) >= 0)) {
				topicTrigger = i;
				regTopics = true;
				return true;
			}
		}
		for(int j = 0; j < litTopics.length; j++){
			if((ChatbotMain.findKeyword(response, litTopics[j], 0) >= 0)){
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
	
	public void startChatting(String response) {
		String topic = "";
		int numberOfNeutral = 0;
		int numberOfInterest = 0;
		boolean forceChange = false;
		if(regTopics) {
			topic = topics[topicTrigger];
		}
		if(aweTopics) {
			topic = litTopics[topicTrigger];
		}
		ChatbotMain.print("Let's talk some more about that! What about " + topic + "?");
		chatting = true;
		while(chatting) {
			int stayOnTopic = topicTrigger;
			response = ChatbotMain.getInput();
			if(regTopics && topicTrigger == stayOnTopic) {
				int randomIndex = (int) Math.floor(Math.random()*neutralResponses.length);
				ChatbotMain.print(neutralResponses[randomIndex]);
				numberOfNeutral++;
				if(numberOfNeutral > 5) {
					regTopics = false;
					forceChange = true;
				}
			}else
			if(aweTopics && topicTrigger == stayOnTopic) {
				int randomIndex = (int) Math.floor(Math.random()*interestResponses.length);
				ChatbotMain.print(interestResponses[randomIndex]);
				numberOfInterest++;
				if(numberOfInterest > 5) {
					aweTopics = false;
					forceChange = true;
				}
			}else
			if(stayOnTopic != topicTrigger || forceChange) {
				forceChange = false;
				numberOfNeutral = 0;
				numberOfInterest = 0;
				ChatbotMain.print("Would you like to talk about something else?");
				isTriggered(response);
			}else
			if(ChatbotMain.findKeyword(response, goodbyeWord, 0) >= 0) {
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			}else if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
				ChatbotMain.print("Oh my goodness! You guessed my favorite things ever. We are friends now.");
			}else{
				ChatbotMain.print("Huh, I don't really get you. Tell me something else?");
			}
		}
	}

	
}
