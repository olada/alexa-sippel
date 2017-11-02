package net.olada.alexa.sippel;

/**
 * Created by David Olah on 02.11.2017.
 */

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Wird von AWS Lambda instanziiert
 */
public class SippelSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.ask.skill.8adb2663-e236-4397-91f9-4500ed607b2b");
    }

    public SippelSpeechletRequestStreamHandler() {
        super(new SippelSpeechlet(), supportedApplicationIds);
    }
}
