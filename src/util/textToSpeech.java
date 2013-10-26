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
	//TODO: Get the user preferences, which will contain the speaker string & speed
	
	public static void speak(String text)
	{
		speakWithSpeaker("kevin16", text, 100);
	}
	public static void speakWithSpeaker(String voice, String text, int wpm)
	{
		//TODO: Remove & use speak and set the settings from the user prefs
		
		VoiceManager voiceManager = VoiceManager.getInstance();
        Voice speakerVoice = voiceManager.getVoice(voice);
//		Set the speaker's voice speed (default = 100 words/minute)
        speakerVoice.setRate(wpm);
//		Allocates the resources for the voice
        speakerVoice.allocate();
//		Synthesize speech & clean up after 
        speakerVoice.speak(text);
        speakerVoice.deallocate();
	}
}