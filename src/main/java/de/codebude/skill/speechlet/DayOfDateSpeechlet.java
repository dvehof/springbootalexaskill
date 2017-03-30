package de.codebude.skill.speechlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;

import de.codebude.skill.intenthandler.HandleCancelIntent;
import de.codebude.skill.intenthandler.HandleDayOfDateIntent;
import de.codebude.skill.intenthandler.HandleHelpIntent;
import de.codebude.skill.intenthandler.HandleStopIntent;
import de.codebude.skill.intenthandler.HandleWelcome;

/**
 * @author Daniel Vehof
 *
 */
@Service
public class DayOfDateSpeechlet implements Speechlet {

	private static final Logger log = LoggerFactory.getLogger(DayOfDateSpeechlet.class);
	private static final String INTENT_DAYOFDATE = "DayOfDateIntent";

	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
		log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		return HandleWelcome.getWelcomeResponse();
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {

		Intent intent = request.getIntent();
		if (intent == null)
			throw new SpeechletException("Unrecognized intent");

		String intentName = intent.getName();

		if (INTENT_DAYOFDATE.equals(intentName)) {
			HandleDayOfDateIntent dodi = new HandleDayOfDateIntent(intent, session);
			return dodi.handleIntent();
		} else if ("AMAZON.HelpIntent".equals(intentName)) {
			HandleHelpIntent hi = new HandleHelpIntent(intent, session);
			return hi.handleIntent();
		} else if ("AMAZON.StopIntent".equals(intentName)) {
			HandleStopIntent si = new HandleStopIntent(intent, session);
			return si.handleIntent();
		} else if ("AMAZON.CancelIntent".equals(intentName)) {
			HandleCancelIntent ci = new HandleCancelIntent(intent, session);
			return ci.handleIntent();
		} else {
			throw new SpeechletException("Unknown intent...");
		}
	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
		log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
	}
}