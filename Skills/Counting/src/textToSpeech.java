
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/* TODO: Remove this when done & add jsapi.jar part to installation doc 
 * IF YOU ARE GETTING ERRORS: 
 * 	- 	Make sure you added FREETTS.JAR to your build path libraries 
 *	  	(Right-click project > Properties > Java Build Path > Libraries > Add External JARs 
 * If you're still getting errors, find Denise!  	
*/

public class textToSpeech
{
	private static final String DEFAULT_VOICE = "kevin16";
	private static textToSpeech instance = new textToSpeech();
	private ExecutorService executor; 
	private int wordsPerMinute; 
	
	private textToSpeech()
	{
		wordsPerMinute = 100; // default speed (in case people don't set the speed)
		executor = Executors.newFixedThreadPool(1);
	}
	
	public static textToSpeech getInstance()
	{
		return instance;
	}
	
	private class SpeakerThread implements Runnable
	{
		private String textToSpeak; 
		private int wordsPerMinute;
		
		public SpeakerThread(String text, int wpm)
		{
			textToSpeak = text; 
			wordsPerMinute = wpm;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			VoiceManager voiceManager = VoiceManager.getInstance();
	        Voice speakerVoice = voiceManager.getVoice(DEFAULT_VOICE);
	        speakerVoice.setRate(wordsPerMinute);	// Set the speaker's voice speed (default = 100 words/minute)
	        speakerVoice.allocate();	// Allocates the resources for the voice
	        speakerVoice.speak(textToSpeak);
	        speakerVoice.deallocate();	// Synthesize speech & clean up after 
		}
		
	}
	public void setWPM(int wpm)
	{
		wordsPerMinute = wpm;
	}
	
	public void speak(String text)
	{
		executor.execute(new SpeakerThread(text, wordsPerMinute));
	}
}
