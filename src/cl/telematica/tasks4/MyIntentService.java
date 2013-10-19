package cl.telematica.tasks4;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {
	
	public static final String ACTION_PROGRESO = "cl.telematica.tasks4.intent.action.PROGRESO";
	public static final String ACTION_FIN = "cl.telematica.tasks4.intent.action.FIN";

	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		int iter = intent.getIntExtra("iteraciones", 0);
		 
        for(int i=1; i<=iter; i++) {
            task();
 
            //Comunicamos el progreso
            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_PROGRESO);
            bcIntent.putExtra("progreso", i*10);
            sendBroadcast(bcIntent);
        }
 
        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_FIN);
        sendBroadcast(bcIntent);
    }
 
    private void task() {
    	try {
    		Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }

}
