package com.me.mygdxgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

@SuppressLint("ShowToast")
public class MainActivity extends AndroidApplication {
	private Toast t;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        initialize(new MyGdxGame(), cfg);
    
        if (AppStatus.getInstance(this).isOnline(this)) {
        	message("online");
        } else {
        	message("offline");
        	
        	if (AppStatus.getInstance(this).turn3GOn(this, true)) {
        		message("successful");
        	} else {
        		message("unsuccessful");
        	}
        }
	}
	
	private void message (String text) {
		if (text.equals("online")) {
			t = Toast.makeText(this, "You are online!!!", 8000);
        	t.show();
		} else if (text.equals("offline")) {
			t = Toast.makeText(this, "You are not online!!!", 8000);
        	t.show();
		} else if (text.equals("successful")) {
			t = Toast.makeText(this, "3G is turned on successfully!!!", 8000);
        	t.show();
		} else if (text.equals("unsuccessful")) {
			t = Toast.makeText(this, "3G is turned on unsuccessfully!!\nPlease check your version!!", 8000);
        	t.show();
		}
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Intent myIntent = new Intent();
		myIntent.setClass(this, FacebookMain.class);
		startActivity(myIntent);
	}
}