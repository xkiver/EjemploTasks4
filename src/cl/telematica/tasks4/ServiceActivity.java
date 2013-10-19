package cl.telematica.tasks4;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ServiceActivity extends Activity {
	
	private IntentFilter filter;
	private ProgressReceiver rcv;
	
	private ProgressBar progressBar;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		button = (Button) findViewById(R.id.button1);
		
		filter = new IntentFilter();
		filter.addAction(MyIntentService.ACTION_PROGRESO);
		filter.addAction(MyIntentService.ACTION_FIN);
		rcv = new ProgressReceiver();
		registerReceiver(rcv, filter);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent msgIntent = new Intent(ServiceActivity.this, MyIntentService.class);
		        msgIntent.putExtra("iteraciones", 10);
		        startService(msgIntent);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class ProgressReceiver extends BroadcastReceiver {
		 
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        if(intent.getAction().equals(MyIntentService.ACTION_PROGRESO)) {
	            int prog = intent.getIntExtra("progreso", 0);
	            progressBar.setProgress(prog);
	        }
	        else if(intent.getAction().equals(MyIntentService.ACTION_FIN)) {
	            Toast.makeText(ServiceActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
	        }
	    }
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(rcv);
	}

}
