package net.olada.alexa.sippel;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by David
 * Date: 02.11.2017 - 21:21.
 */
public class SippelSpeechlet implements SpeechletV2 {
    private static final Logger logger = LoggerFactory.getLogger(SippelSpeechlet.class);

    private static final String SLOT_NAME = "Name";

    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {
        logger.debug("onSessionStarted requestId={}, sessionId={}",
                        speechletRequestEnvelope.getRequest().getRequestId(),
                        speechletRequestEnvelope.getSession().getSessionId());
        // any initialization logic goes here
    }

    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {
        logger.debug("onLaunch requestId={}, sessionId={}",
                        speechletRequestEnvelope.getRequest().getRequestId(),
                        speechletRequestEnvelope.getSession().getSessionId());

        String speechText = "Hallo. Willkommen zur Sippi Polizei. Frag mich einfach, wer der größte Sippi ist.";
        logger.info("Saying: \"{}\"", speechText);

        PlainTextOutputSpeech welcomeSpeech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(welcomeSpeech);
        return SpeechletResponse.newAskResponse(welcomeSpeech, reprompt);
    }

    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> speechletRequestEnvelope) {
        IntentRequest request = speechletRequestEnvelope.getRequest();
        logger.debug("onIntent requestId={}, sessionId={}", request.getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : "";
        SippelIntent sippelIntent = SippelIntent.fromString(intentName);

        SpeechletResponse response;

        if (intent == null) {
            logger.error("Etwas ist schiefgelaufen... der Intent ist NULL");
            return getSpeechletResponse("Etwas ist schief gelaufen... der Intent ist NULL");
        }

        switch (sippelIntent) {
            case BiggestSippel:
                response = getSpeechletResponse("Emmanuel ist der größte Sippi");
                break;
            case MirrorMirrorSippel:
                response = getSpeechletResponse("Ihr, mein Sippi, seid der größte Sippi hier. " +
                        "Aber Emmanuel, über den Bergen, bei den sieben Zwergen ist der größte Sippi im ganzen Land.");
                break;
            case IsXTheBiggestSippel:
                Slot nameSlot = intent.getSlot(SLOT_NAME);
                if (nameSlot != null && nameSlot.getValue() != null) {
                    response = getSpeechletResponse("Nein, " + nameSlot.getValue() + " ist nicht der größte Sippi. Emmanuel ist mit Abstand der größte Sippi.");
                } else { // Kein Slot übergeben
                    logger.warn("Der übergebene Slot ist komischerweise leer....");
                    response = getSpeechletResponse("Du hast keinen Namen genannt, du Sippi");
                }
                break;
            default:
                response = getSpeechletResponse("Ich konnte keinen Intent erkennen, du Sippi");
                break;
        }

        return response;
    }

    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {
        logger.debug("onSessionEnded requestId={}, sessionId={}",
                        speechletRequestEnvelope.getRequest().getRequestId(),
                        speechletRequestEnvelope.getSession().getSessionId());
        // any cleanup logic goes here
    }

    private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return speech;
    }

    private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);
        return reprompt;
    }

    private SpeechletResponse getSpeechletResponse(OutputSpeech outputSpeech) {
        return SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse getSpeechletResponse(String speechText) {
        return getSpeechletResponse(getPlainTextOutputSpeech(speechText));
    }
}
