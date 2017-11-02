package net.olada.alexa.sippel;

/**
 * Created by David
 * Date: 02.11.2017 - 21:47.
 */
public enum SippelIntent {
    BiggestSippel, MirrorMirrorSippel, IsXTheBiggestSippel, Undefined;

    public static SippelIntent fromString(String str) {
        SippelIntent sippelIntent;
        try {
            sippelIntent = SippelIntent.valueOf(str);
        } catch (IllegalArgumentException e){
            sippelIntent = SippelIntent.Undefined;
        }

        return sippelIntent;
    }
}
