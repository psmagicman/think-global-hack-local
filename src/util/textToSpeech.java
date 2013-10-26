package util;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

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