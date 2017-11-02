# alexa-sippel

## To-Dos
* Log4J richtig konfigurieren
* Überdenken, wie Alexa "Sippi-Polizei" richtig erkennen kann
* Mit SSML herumspielen und Random-Aussagen zurückgeben, damit Abwechslung drin ist.

## Flow-Diagram

"Wer ist der größte Sippel | Sip | Sippi" -> "Emmanuel ist der größte Sippel | Sip | Sippi"

"Alexa Alexa an der Wand, wer ist der größte Sippel | Sip | Sippi     im Land?" -> "Ihr seid der größte Sippel | Sip | Sippi hier, aber Emmanuel über den Bergen bei den sieben Zwergen ist noch ein viel größerer Sippel | Sip | Sippe als ihr"

## Intent-Schema

```
{
    "intents": [
        {
            "intent": "BiggestSippel"
        },
        {
            "intent": "MirrorMirrorSippel"
        },
        {
            "intent": "IsXTheBiggestSippel",
            "slots": [
                {
                    "name": "Name",
                    "type": "AMAZON.DE_FIRST_NAME"
                }
            ]
        }
    ]
}
```

## Utterances
```
BiggestSippel wer ist der größte sippel
BiggestSippel wer ist der größte sip
BiggestSippel wer ist der größte sippi

MirrorMirrorSippel spieglein spieglein an der wand wer ist der größte sippel im ganzen land
MirrorMirrorSippel spieglein spieglein an der wand wer ist der größte sip im ganzen land
MirrorMirrorSippel spieglein spieglein an der wand wer ist der größte sippi im ganzen land

IsXTheBiggestSippel ist {Name} der größte sippel
IsXTheBiggestSippel ist {Name} der größte sip
IsXTheBiggestSippel ist {Name} der größte sippi
```