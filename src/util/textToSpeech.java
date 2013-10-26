package util;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/* TODO: Remove this when done & add jsapi.jar part to installation doc 
 * IF YOU ARE GETTING ERRORS: 
 * 	- 	Make sure you added FREETTS.JAR to your build path libraries 
 *	  	(Right-click project > Properties > Java Build Path > Libraries > Add External JARs 
 * IF YOU CAN'T HEAR THE AUDIO: 
 * 	-	Make sure you added JSAPI.JAR to your JRE external library folder
 * 			(C:\Program Files\Java\jre1.6.*\lib\ext) 
 * 		or  (C:\Program Files\Java\jdk1.*.*\jre\lib\ext)
 * If you're still getting errors, find Denise!  	
*/

public class textToSpeech
{
	//TODO: Get the user preferences
	
	public static void speak(String text)
	{
		//TODO: Remove this method and use only speakWithSpeaker
		speakWithSpeaker("kevin16", text, 0);
	}
	public static void speakWithSpeaker(String voice, String text, int speed)
	{
		//TODO: Change voice (kevin works, but alan doesn't. MBROLA also doesn't)
		//TODO: Change speed (needs MBROLA)
		VoiceManager voiceManager = VoiceManager.getInstance();
        Voice speakerVoice = voiceManager.getVoice(voice);
        /* Allocates the resources for the voice.*/
        speakerVoice.allocate();
        /* Synthesize speech.*/
        speakerVoice.speak(text);
        /* Clean up and leave.*/
        speakerVoice.deallocate();
	}
}