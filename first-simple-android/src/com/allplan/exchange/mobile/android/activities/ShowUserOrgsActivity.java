package com.allplan.exchange.mobile.android.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.allplan.exchange.mobile.android.adapters.OrganizationsArrayAdapter;
import com.allplan.exchange.mobile.android.model.organization.AuthDTO;
import com.allplan.exchange.mobile.android.model.organization.Organization;
import com.allplan.exchange.mobile.android.requests.AuthenticateRequest;
import com.allplan.exchange.mobile.android.util.CustomDrawableView;

public class ShowUserOrgsActivity extends Activity {
	
	private static final String KEY_LAST_REQUEST_CACHE_KEY = "lastRequestCacheKey";
	
	private SpiceManager spiceManager = new SpiceManager(
        JacksonSpringAndroidSpiceService.class);
  
    private OrganizationsArrayAdapter followersAdapter;
    AuthenticateRequest request ;
    private String lastRequestCacheKey;
    
    CustomDrawableView mCustomDrawableView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState);
          requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
          setContentView(R.layout.organization_list);
          
          if(!spiceManager.isStarted())
            	spiceManager.start(this);
          
          initUIComponents();
    
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	if(!spiceManager.isStarted())
        	spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
      
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
            		
            	spiceManager.removeAllDataFromCache();
            	spiceManager.cancelAllRequests();
    			ShowUserOrgsActivity.this.setProgressBarIndeterminateVisibility(true);	
    			Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
    			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    			startActivity(intent);
    			
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    
    private void initUIComponents() {
    	
    	Intent i = getIntent();
    	
    	String userName = (String) i.getExtras().get("username");
    	String password = (String) i.getExtras().get("password");
    	
    	AuthDTO authDto = (AuthDTO) getIntent().getSerializableExtra("authDto");
    	  	
        final ListView listview = (ListView) findViewById(R.id.listorganizationview);
          
        followersAdapter = new OrganizationsArrayAdapter(this, new ArrayList<Organization>(), userName, password); 
        listview.setAdapter(followersAdapter);
        
        if(null != authDto){
    		for (Organization nextOrg : authDto
    				.getOrganizations()) {
    			followersAdapter.add(nextOrg);
    		}
    		followersAdapter.notifyDataSetChanged();
			ShowUserOrgsActivity.this
					.setProgressBarIndeterminateVisibility(false);
    	}
        else {
        	
			TextView emptyText = (TextView) findViewById(R.id.emptyorganizationsview);
			listview.setEmptyView(emptyText);
			
		}
        	
        /**FIXME seems not to work anymore because of buttons in ArrayAdapter*/
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      	  @Override
      	  public void onItemClick(AdapterView<?> parent, View view,
      	    int position, long id) {
      	    Toast.makeText(getApplicationContext(),
      	      "Click ListItem Number " + position, Toast.LENGTH_LONG)
      	      .show();
      	  }   
        });
        
        final ActionBar actionBar = getActionBar();
        //actionBar.show();     
	    actionBar.setDisplayOptions(actionBar.getDisplayOptions()
	            | ActionBar.DISPLAY_SHOW_CUSTOM);
	    ImageView imageView = new ImageView(actionBar.getThemedContext());
	    imageView.setScaleType(ImageView.ScaleType.CENTER);
	    imageView.setImageResource(R.drawable.orga_icon_nobgr);
	    ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
	            ActionBar.LayoutParams.WRAP_CONTENT,
	            ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
	                    | Gravity.CENTER_VERTICAL);
	    layoutParams.rightMargin = 20;
	    imageView.setLayoutParams(layoutParams);
	    actionBar.setCustomView(imageView);
        
             
    }
 
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && spiceManager.isStarted()) {
    			spiceManager.removeAllDataFromCache();
    	}
		
		return super.onKeyDown(keyCode, event);
		
	}
	
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!TextUtils.isEmpty(lastRequestCacheKey)) {
            outState.putString(KEY_LAST_REQUEST_CACHE_KEY, lastRequestCacheKey);
        }
        super.onSaveInstanceState(outState);
    }
   
}
   