package chatbotProject;

public class ChatbotErik implements Topic {

	private String[] topics;
	private String goodbyeWord;
	private String[] neutralResponses;
	private String[] litTopics; 
	private String[] interestResponses;
	//private String[] questionsForMe;
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
				return true;
			}
			if((ChatbotMain.findKeyword(response, litTopics[i], 0) >= 0)){
				topicTrigger = i;
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
		ChatbotMain.print("Let's talk some more about that!");
		chatting = true;
		while(chatting) {
			int stayOnTopic = topicTrigger;
			response = ChatbotMain.getInput();
			if(isTriggeredRegularTopics(response)) {
				int randomIndex = (int) Math.floor(Math.random()*neutralResponses.length);
				ChatbotMain.print(neutralResponses[randomIndex]);
			}else
			if(isTriggeredLitTopics(response)) {
				int randomIndex = (int) Math.floor(Math.random()*neutralResponses.length);
				ChatbotMain.print(interestResponses[randomIndex]);
			}else
			if(stayOnTopic == topicTrigger) {
				int randomIndex = (int) Math.floor(Math.random()*neutralResponses.length);
				ChatbotMain.print(neutralResponses[randomIndex]);
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
