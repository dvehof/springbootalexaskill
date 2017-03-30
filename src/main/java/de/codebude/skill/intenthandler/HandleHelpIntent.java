package de.codebude.skill.intenthandler;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;

import de.codebude.helper.AskRepsonseHelper;

/**
 * Handler for built-in HelpIntent
 * 
 * @author Daniel Vehof
 *
 */
public class HandleHelpIntent {

	private Intent intent;

	private Session session;

	public HandleHelpIntent(Intent intent, Session session) {
		this.intent = intent;
		this.session = session;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public SpeechletResponse handleIntent() {
		String speechOutput = "Help needed!";
		String repromptText = "Help needed, reprompt!";
		return AskRepsonseHelper.newAskResponse(speechOutput, false, repromptText, false);
	}
}
