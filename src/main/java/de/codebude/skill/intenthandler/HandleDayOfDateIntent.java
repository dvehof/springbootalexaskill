package de.codebude.skill.intenthandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.StandardCard;

import de.codebude.helper.AskRepsonseHelper;

/**
 * Handler for DayOfDdate Intent
 * Converts the given Date-slot of the intent into a day of the week
 * 
 * @author Daniel Vehof
 *
 */
public class HandleDayOfDateIntent {

	private static final Logger log = LoggerFactory.getLogger(HandleDayOfDateIntent.class);

	private Intent intent;
	private Session session;

	private static final String SLOT_DATE = "Date";

	private static final String[] DAY_NAMES = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", };

	public HandleDayOfDateIntent(Intent intent, Session session) {
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
		Calendar calendar = getCalendar(intent);

		// above could return null for invalid values of DateSlot!
		if (calendar == null) {

			PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
			speech.setText("Did not understand for what date you wanna know the day. Try again!");

			return AskRepsonseHelper.newAskResponse(speech.getText(), false, "What do you want to know?", false);
		}
		String day = DAY_NAMES[calendar.get(Calendar.DAY_OF_WEEK) - 1];

		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
		String dateString = format.format(date);
		String speechText = "";

		speechText = speechText + "On " + dateString + ", we got " + day + ".";

		String cardText = speechText;

		speechText = speechText + " You want to know more?";

		StandardCard card = new StandardCard();
		card.setTitle("Sample Skill Date");
		card.setText(cardText);

		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		SpeechletResponse response = AskRepsonseHelper.newAskResponse(speech.getText(), false, "What do you want to know?", false);
		response.setCard(card);
		return response;
	}

	private Calendar getCalendar(Intent intent) {
		Slot daySlot = intent.getSlot(SLOT_DATE);
		Date date;
		Calendar calendar = Calendar.getInstance();
		if (daySlot != null && daySlot.getValue() != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
			try {
				log.debug("DaySlot Value is: " + daySlot.getValue());
				date = dateFormat.parse(daySlot.getValue());
			} catch (ParseException e) {
				log.debug("ParseException for DaySlot value!");
				return null;
			}
		} else {
			log.debug("DaySolt or Value are null!");
			return null;
		}
		calendar.setTime(date);
		return calendar;
	}

}
