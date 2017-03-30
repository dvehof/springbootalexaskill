package de.codebude.skill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;

import de.codebude.skill.speechlet.DayOfDateSpeechlet;

/**
 * Registers the servlet with our custom speechlet
 * 
 * @author Daniel Vehof
 *
 */
@Configuration
public class SkillConfig {

	@Autowired
	private DayOfDateSpeechlet mySpeechlet;

	@Bean
	public ServletRegistrationBean registerServlet() {

		SpeechletServlet speechletServlet = new SpeechletServlet();
		speechletServlet.setSpeechlet(mySpeechlet);

		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(speechletServlet, "/alexaservice");
		return servletRegistrationBean;
	}

}
