package chatbotProject;

public class ChatbotErik implements Topic {

	private String[] topics;
	private String goodbyeWord;
	private String[] neutralResponses;
	private String[] litTopics; 
	//private String[] questionsForMe;
	private String secretWord;
	private boolean chatting;
	private int topicTrigger;
	
	public ChatbotErik() {
		String[] temp = {"entertainment","video games","hobby", "sport","life","anything","interests","internet"};
		topics = temp;
		String[] temp2 = {"okay","that's cool","oh, really","mhmm","uh-huh"};
		neutralResponses = temp2;
		String[] temp3 = {"food", "summer", "weekends"};
		litTopics = temp3;
		goodbyeWord = "bye";
		secretWord = "Exo";
	}

	public boolean isTriggered(String response) {
		for(int i = 0; i < topics.length; i++) {
			if(ChatbotMain.findKeyword(response, topics[i], 0) >= 0){
				topicTrigger = i;
				return true;
			}
		}
		return false;
	}

	public void startChatting(String response) {
		ChatbotMain.print("Let's talk some more about " + topics[topicTrigger] + "!");
		if(topicTrigger <= topics.length - 5) {
			ChatbotMain.print("What is your favorite kind of " + topics[topicTrigger] + "?");
		}
		if(topicTrigger >= topics.length - 4) {
			ChatbotMain.print("What about " + topics[topicTrigger] + "?");
		}
		chatting = true;
		while(chatting) {
			int stayOnTopic = topicTrigger;
			response = ChatbotMain.getInput();
			
			
			
			if(stayOnTopic == topicTrigger) {
				int randomIndex = (int) Math.floor(Math.random()*neutralResponses.length);
				ChatbotMain.print(neutralResponses[randomIndex]);
			}
			if(ChatbotMain.findKeyword(response, goodbyeWord, 0) >= 0) {
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			}else if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
				ChatbotMain.print("Oh my goodness! You guessed my favorite things ever. We are friends now.");
			}else if((isTriggered(response))){
				ChatbotMain.print("Huh, I don't really get you. Tell me something else?");
			}
		}
	}

	
}
