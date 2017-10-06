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
	private String[] dislikeResponses;
	private boolean dislikeTopics = false;
	private String[] questions;
	private String questionType = "";
	//private String[] jokeResponses;
	//private String[] jokeTriggers;
	//private String[] jokeAnswers;
	//private String[] questionsForMe;
	private String[] goodbyeWords;
	private String secretWord;
	private boolean chatting;
	//private int topicTrigger;
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
		String[] temp5 = {"You're being annoying.","Is that all you can say?","Such extensive vocabulary","Are you having fun?",};
		repeatResponses = temp5;
		String[] temp6 = {"okay cutie", "of course babe", "sounds lit hon", "thats so funny eksdee"};
		flirtyResponses = temp6;		
		String[] temp7 = {"entertainment","video games","sports"};
		robotDislikes = temp7;
		String[] temp8 = {"Im bored","What a nerd","Sorry I have nothing to contribute to this","BOOOORRRRRRIIINNNNGGG"};
		dislikeResponses = temp8;
		String[] temp9 = {"what","how","when","where","why","who"};
		questions = temp9;

		String[] goodbyeStrings = {"bye", "goodbye", "see you later"};
		goodbyeWords = goodbyeStrings;
		secretWord = "Exo";
	}

	public boolean isTriggered(String response) {
		for(int i = 0; i < topics.length; i++) {
			if((ChatbotMain.findKeyword(response, topics[i], 0) >= 0)) {
				lastResponse = response;
				//topicTrigger = i;
				regTopics = true;
				return true;
			}
		}
		for(int j = 0; j < litTopics.length; j++){
			if((ChatbotMain.findKeyword(response, litTopics[j], 0) >= 0)){
				lastResponse = response;
				//topicTrigger = j;
				aweTopics = true;
				return true;
			}
		}
		for(int k = 0; k < robotDislikes.length; k++){
			if((ChatbotMain.findKeyword(response, robotDislikes[k], 0) >= 0)){
				//topicTrigger = k;
				dislikeTopics = true;
				return true;
			}
		}
		return false;
	}

	public boolean isTriggeredRegularTopics(String response) {
		for(int i = 0; i < topics.length; i++) {
			if((ChatbotMain.findKeyword(response, topics[i], 0) >= 0)){
				regTopics = true;
				return true;
			}
		}
		return false;
	}

	public boolean isTriggeredLitTopics(String response) {
		for(int i = 0; i < litTopics.length; i++) {
			if(ChatbotMain.findKeyword(response, litTopics[i], 0) >= 0){
				aweTopics = true;
				return true;
			}
		}
		return false;
	}

	public boolean isTriggeredDislikes(String response) {
		for(int i = 0; i < robotDislikes.length; i++) {
			if(ChatbotMain.findKeyword(response, robotDislikes[i], 0) >= 0){
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

	public boolean isTriggeredQuestions(String response) {
		for(int i = 0; i < questions.length; i++) {
			if(ChatbotMain.findKeyword(response, questions[i], 0) >= 0) {
				questionType = questions[i];
				return true;
			}
		}
		if(ChatbotMain.findKeyword(response, "?", 0) >= 0){
			return true;
		}

		return false;
	}	

	/*
	public boolean isTriggeredJokes(String response) {
		for(int i = 0; i < goodbyeWords.length; i++) {
			if(ChatbotMain.findKeyword(response, goodbyeWords[i], 0) >= 0){
				return true;
			}
		}
		return false;
	}
	 */
	public boolean checkFlirty() {
		if(complimentScore >= 4 && complimentScore <= 6) {
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
		//boolean dislike = false;
		complimentScore = ChatbotSam.getComplimentScore();
		flirty = checkFlirty();

		if(isTriggeredDislikes(response)) {
			//dislikeTopics = true;
			randomIndex = (int) Math.floor(Math.random()*dislikeResponses.length);
			ChatbotMain.print(dislikeResponses[randomIndex]);
		}else if(flirty) {
			ChatbotMain.print("What's up cutie?");
		}
		else
			ChatbotMain.print("Let's talk some more about that! What about it?");
		chatting = true;
		while(chatting) {
			//int stayOnTopic = topicTrigger;

			response = ChatbotMain.getInput();

			flirty = checkFlirty();
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




				if(forceChange) {
					lastResponse = response;
					ChatbotMain.print("Sorry, but is this going to take any longer? I am getting bored of you. Tell me something else.");
					ChatbotMain.chatbot.startTalkingAgain();
				}else if(isTriggeredQuestions(response)) {
					if(questionType.equals("who")) {
						ChatbotMain.print("I dont know that person. Or who you are talking about.");
					}else if(questionType.equals("what")) {
						ChatbotMain.print("I dont know.");
					}else if(questionType.equals("when")) {
						ChatbotMain.print("Never.");
					}else if(questionType.equals("where")) {
						ChatbotMain.print("That's a secret");
					}else if(questionType.equals("why")) {
						ChatbotMain.print("I'll never tell.");
					}else if(questionType.equals("how")) {
						ChatbotMain.print("It's okay.");
					}
				}else


					if(regTopics && isTriggeredLitTopics(response)) {
						lastResponse = response;
						numberOfRepeat = 0;
						numberOfNeutral = 0;
						randomIndex = (int) Math.floor(Math.random()*interestResponses.length);
						ChatbotMain.print(interestResponses[randomIndex]);
						numberOfInterest++;
						if(numberOfInterest > 4) {
							aweTopics = false;
							forceChange = true;
						}
					}else

						if(aweTopics && isTriggeredRegularTopics(response)) {
							lastResponse = response;
							numberOfRepeat = 0;
							numberOfInterest = 0;
							randomIndex = (int) Math.floor(Math.random()*neutralResponses.length);
							ChatbotMain.print(neutralResponses[randomIndex]);
							numberOfNeutral++;
							if(numberOfNeutral > 2) {
								regTopics = false;
								forceChange = true;
							}
						}else




							if(!isTriggeredRegularTopics(response) && !isTriggeredLitTopics(response) && flirty) {
								lastResponse = response;
								numberOfRepeat = 0;
								numberOfFlirt++;
								if(numberOfFlirt > 2) {
									ChatbotMain.print("Hey weird thought, but why don't you ask me out already?");
									ChatbotMain.chatbot.getJenny().startChatting(response);
								}
								randomIndex = (int) Math.floor(Math.random()*flirtyResponses.length);
								ChatbotMain.print(flirtyResponses[randomIndex]);
							}else if(isTriggeredRegularTopics(response) || regTopics/* && topicTrigger == stayOnTopic*/) {
								lastResponse = response;
								numberOfRepeat = 0;
								randomIndex = (int) Math.floor(Math.random()*neutralResponses.length);
								ChatbotMain.print(neutralResponses[randomIndex]);
								numberOfNeutral++;
								if(numberOfNeutral > 2) {
									regTopics = false;
									forceChange = true;
								}
							}else if(isTriggeredLitTopics(response) || aweTopics/*&& topicTrigger == stayOnTopic*/) {
								lastResponse = response;
								numberOfRepeat = 0;
								randomIndex = (int) Math.floor(Math.random()*interestResponses.length);
								ChatbotMain.print(interestResponses[randomIndex]);
								numberOfInterest++;
								if(numberOfInterest > 4) {
									aweTopics = false;
									forceChange = true;
								}
							}/*else if(!isTriggeredRegularTopics(response) && !isTriggeredLitTopics(response)) {
				lastResponse = response;
				numberOfRepeat = 0;
				forceChange = false;
				numberOfNeutral = 0;
				numberOfInterest = 0;
				ChatbotMain.print("Let's talk some more about that! What about it?");
			}*/

							else if(!isTriggeredRegularTopics(response) && !isTriggeredLitTopics(response) && numberOfRepeat > 4){
								numberOfRepeat = 0;
								ChatbotMain.print("Im bringing you back to talk about something else.");
								ChatbotMain.chatbot.startTalkingAgain();
							}else
							{
								lastResponse = response;
								numberOfNeutral = 0;
								numberOfInterest = 0;
								ChatbotMain.print("Tell me something else please. Like any other topic.");
							}
		}
	}


}
