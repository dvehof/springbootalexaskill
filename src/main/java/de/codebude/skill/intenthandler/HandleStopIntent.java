package de.codebude.skill.intenthandler;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;

/**
 * Handler for built-in StopIntent
 * 
 * @author Daniel Vehof
 *
 */
public class HandleStopIntent {

	private Intent intent;

	private Session session;

	public HandleStopIntent(Intent intent, Session session) {
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
		PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
		outputSpeech.setText("Goodbye!");
		return SpeechletResponse.newTellResponse(outputSpeech);
	}
}
