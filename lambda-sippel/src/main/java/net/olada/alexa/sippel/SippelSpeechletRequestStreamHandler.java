package net.olada.alexa.sippel;

/**
 * Created by David Olah on 02.11.2017.
 */

import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.Set;

/**
 * Wird von AWS Lambda instanziiert
 */
public class SippelSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler{
    public SippelSpeechletRequestStreamHandler(SpeechletV2 speechlet, Set<String> supportedApplicationIds) {
        super(speechlet, supportedApplicationIds);
    }
}
