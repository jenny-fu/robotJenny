package chatbotProject;

public class ChatbotErik implements Topic {

	private String[] topics;
	private String[] neutralResponses;
	private boolean regTopics = false;
	private String[] litTopics; 
	private String[] interestResponses;
	private boolean aweTopics = false;
	private String[] repeatResponses;
	private String[] flirtyResponses;
	private boolean flirty = false;
	private int complimentScore;
	private String[] robotDislikes;
	//private String[] jokeResponses;
	//private String[] jokeTriggers;
	//private String[] jokeAnswers;
	//private String[] questionsForMe;
	private String[] goodbyeWords;
	private String secretWord;
	private boolean chatting;
	private int topicTrigger;
	//private boolean angry = false;

	private String lastResponse = "";


	public ChatbotErik() {
		String[] temp = {"hobby","life","anything","interests","internet"};
		topics = temp;
		String[] temp2 = {"okay","that's cool","oh, really","mhmm","uh-huh", "im totally paying attention to you", "woahhhh -snores-"};
		neutralResponses = temp2;
		String[] temp3 = {"food", "summer", "weekends"};
		litTopics = temp3;
		String[] temp4 = {"Woahhh","Tell me more","Wow!","Im interested"};
		interestResponses  = temp4;
		String[] temp5 = {"You're being annoying.","Is that all you can say?","Such extensive vocabulary","Are you having fun?"};
		repeatResponses = temp5;
		String[] temp6 = {"okay cutie", "of course babe",};
		flirtyResponses = temp6;		
		String[] temp7 = {"entertainment","video games","sports"};
		robotDislikes = temp7;

		//String[] temp6 = {""};
		//jokeResponses = temp6;
		//String[] temp7 = {"Tell me a joke", "know any jokes","a joke"};
		//jokeTriggers = temp7;
		//String[]temp8 = {""};
		//jokeAnswers = temp8;

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

	public boolean isTriggeredDislikes(String response) {
		for(int i = 0; i < robotDislikes.length; i++) {
			if(ChatbotMain.findKeyword(response, robotDislikes[i], 0) >= 0){
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

	public boolean isTriggeredJokes(String response) {
		for(int i = 0; i < goodbyeWords.length; i++) {
			if(ChatbotMain.findKeyword(response, goodbyeWords[i], 0) >= 0){
				return true;
			}
		}
		return false;
	}

	public boolean checkFlirty() {
		if(complimentScore >= 8) {
			return true;
		}
		return false;
	}

	public void startChatting(String response) {
		//String topic = "";
		int numberOfNeutral = 0;
		int numberOfInterest = 0;
		int numberOfRepeat = 0;
		int numberOfFlirt = 0;
		int randomIndex = 0;
		boolean forceChange = false;
		complimentScore = ChatbotSam.getComplimentScore();
		flirty = checkFlirty();

		if(regTopics) {
			aweTopics = false;
		}
		if(aweTopics) {
			regTopics = false;
		}
		ChatbotMain.print("Let's talk some more about that! What about it? " + complimentScore);
		chatting = true;
		while(chatting) {
			int stayOnTopic = topicTrigger;
			response = ChatbotMain.getInput();

			if(flirty) {
				regTopics = false;
				aweTopics = false;
			}
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


			if(forceChange) {
				lastResponse = response;
				ChatbotMain.print("Sorry, but is this going to take any longer? I am getting bored of you. Tell me something else.");
			}else
				if(!regTopics && !aweTopics) {
					lastResponse = response;
					numberOfRepeat = 0;
					randomIndex = (int) Math.floor(Math.random()*flirtyResponses.length);
					ChatbotMain.print(flirtyResponses[randomIndex]);
					numberOfFlirt++;
					if(numberOfFlirt > 2) {
						ChatbotMain.print("Why don't you ask me out already?");
						ChatbotMain.chatbot.getJenny().startChatting(response);
					}
				}else if(regTopics && topicTrigger == stayOnTopic) {
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
					}else if(stayOnTopic != topicTrigger) {
						lastResponse = response;
						numberOfRepeat = 0;
						forceChange = false;
						numberOfNeutral = 0;
						numberOfInterest = 0;
						regTopics = false;
						aweTopics = false;
						ChatbotMain.chatbot.startTalkingAgain();
					}else if(!regTopics && !aweTopics && numberOfRepeat > 4){
						numberOfRepeat = 0;
						ChatbotMain.print("Im bringing you back to talk about something else.");
						ChatbotMain.chatbot.startTalkingAgain();
					}else
					{
						lastResponse = response;
						ChatbotMain.print("Tell me something else please. Like any other topic.");
					}
		}
	}


}
