package com.allplan.exchange.mobile.android.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.allplan.exchange.mobile.android.model.organization.Follower;
import com.allplan.exchange.mobile.android.requests.AuthenticateRequest;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class LoginActivity extends Activity {
	
	
	private static final String KEY_LAST_REQUEST_CACHE_KEY = "lastRequestCacheKey";

    private SpiceManager spiceManager = new SpiceManager(
        JacksonSpringAndroidSpiceService.class);

    private String lastRequestCacheKey;
    AuthenticateRequest request ;
    private EditText Username;
    private EditText Password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_login);
        initUIComponents();
    }
    
    @Override
	protected void onStart() {
		super.onStart();
		spiceManager.start(this);
	}

    @Override
    protected void onStop() {
    	if (spiceManager.isStarted()) 
    		spiceManager.shouldStop();
        super.onStop();
    }
    
    private void initUIComponents() {
    	Button loginButton=(Button)findViewById(R.id.Loginbutton);
    	Username=(EditText)findViewById(R.id.username);
    	Password=(EditText)findViewById(R.id.password);

    	loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				boolean startAuthenticateRequest = true;
				
	        	if(null == Username || Username.getText().toString().isEmpty()){
	        		startAuthenticateRequest = false;
	        		Username.setError("Required");
	        	}
	        	if(null == Password || Password.getText().toString().isEmpty()){
	        		startAuthenticateRequest = false;
	        		Password.setError("Required");
	        	}
	        	
	        	if (startAuthenticateRequest == true) {
	        					
					performRequest(Username.getText().toString(), Password.getText().toString());
						
					// hide keyboard
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(Username.getWindowToken(), 0);
					imm.hideSoftInputFromWindow(Password.getWindowToken(), 0);
					
	        	}
				
			}
		});
    
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!TextUtils.isEmpty(lastRequestCacheKey)) {
            outState.putString(KEY_LAST_REQUEST_CACHE_KEY, lastRequestCacheKey);
        }
        super.onSaveInstanceState(outState);
    }
    
       
	private void performRequest(String userEmail, String userPwd) {
		LoginActivity.this.setProgressBarIndeterminateVisibility(true);
		request = new AuthenticateRequest(userEmail, userPwd);

		lastRequestCacheKey = request.createCacheKey();

		spiceManager
				.execute(request, lastRequestCacheKey,
						DurationInMillis.ONE_HOUR,
						new ListAuthenticateRequestListener());
	}
	
	private class ListAuthenticateRequestListener implements
	    RequestListener<Follower> {
	    @Override
	    public void onRequestFailure(SpiceException e) {
	        Toast.makeText(LoginActivity.this,
		            "Wrong username or password", Toast.LENGTH_LONG)
		            .show();
	        LoginActivity.this.setProgressBarIndeterminateVisibility(false);
	    }
	    
	    @Override
		public void onRequestSuccess(Follower follower) {
	    	
			LoginActivity.this
					.setProgressBarIndeterminateVisibility(false);
					
			Intent i = new Intent(getBaseContext(),
					ShowUserOrgsActivity.class);
			
			i.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);                     
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 		
			i.putExtra("username", Username.getText().toString());
			i.putExtra("password", Password.getText().toString());
			
			if (null != follower) {
				i.putExtra("authDto", follower.getAuthDTO());
			}
					
			getBaseContext().startActivity(i);
			
		}
	}
    
}
