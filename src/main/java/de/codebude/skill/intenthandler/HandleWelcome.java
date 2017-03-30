package de.codebude.skill.intenthandler;

import com.amazon.speech.speechlet.SpeechletResponse;

import de.codebude.helper.AskRepsonseHelper;

/**
 * Handler for welcome on skill start.
 * 
 * @author Daniel Vehof
 */
public class HandleWelcome {

	public static SpeechletResponse getWelcomeResponse() {
		String speechOutput = "Welcome to HelloSkill! Ask something!";
		String repromptText = "You want to ask something?";

		return AskRepsonseHelper.newAskResponse(speechOutput, false, repromptText, false);
	}

}
