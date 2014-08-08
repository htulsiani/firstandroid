package com.allplan.exchange.mobile.android.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
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
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.allplan.exchange.mobile.android.adapters.ContactsArrayAdapter;
import com.allplan.exchange.mobile.android.model.contact.Contact;
import com.allplan.exchange.mobile.android.model.contact.ContactList;
import com.allplan.exchange.mobile.android.requests.ContactsRequest;

public class ShowProjectContactsActivity extends Activity {
	
	private static final String KEY_LAST_REQUEST_CACHE_KEY = "lastRequestCacheKey";

    private SpiceManager spiceManager = new SpiceManager(
        JacksonSpringAndroidSpiceService.class);
  
    private ContactsArrayAdapter contactsAdapter;
    
    private String lastRequestCacheKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState);
          requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
          setContentView(R.layout.contacts_list);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	setContentView(R.layout.contacts_list);
        initUIComponents();
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
    			ShowProjectContactsActivity.this.setProgressBarIndeterminateVisibility(true);	
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
    	
    	String userName = (String) i.getExtras().get("userEmail");
    	String password = (String) i.getExtras().get("pwd");
    	long orgId = (Long) i.getExtras().get("orgId");
    	String projectId = (String) i.getExtras().get("projectId");
    	String projectName = (String) i.getExtras().get("projectName");
    	
    	performRequest(userName, password, orgId, projectId);
    	
        final ListView listview = (ListView) findViewById(R.id.listcontactsview);
          
        contactsAdapter = new ContactsArrayAdapter(this, new ArrayList<Contact>(), projectName); 
        listview.setAdapter(contactsAdapter);

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
               
        /** set project name to the subtitle of action bar */
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.projectcontactlist_actionbar_subtitle);
        actionBar.setSubtitle(projectName);
        actionBar.setIcon(R.drawable.project_icon_nobgr);
        //actionBar.show();
	    actionBar.setDisplayOptions(actionBar.getDisplayOptions()
	            | ActionBar.DISPLAY_SHOW_CUSTOM);
	    ImageView imageView = new ImageView(actionBar.getThemedContext());
	    imageView.setScaleType(ImageView.ScaleType.CENTER);
	    imageView.setImageResource(R.drawable.contact_icon_nobgr);
	    ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
	            ActionBar.LayoutParams.WRAP_CONTENT,
	            ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
	                    | Gravity.CENTER_VERTICAL);
	    layoutParams.rightMargin = 20;
	    imageView.setLayoutParams(layoutParams);
	    actionBar.setCustomView(imageView);
             
    }
 
    private void performRequest(String userEmail, String userPwd, long orgId, String projectId) {
        ShowProjectContactsActivity.this.setProgressBarIndeterminateVisibility(true);
        
        ContactsRequest request = new ContactsRequest(userEmail, userPwd, orgId, projectId);
        
        lastRequestCacheKey = request.createCacheKey();
        
        spiceManager.execute(request, lastRequestCacheKey,
            DurationInMillis.ONE_HOUR, new ListContactsRequestListener());
        
    }
    

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!TextUtils.isEmpty(lastRequestCacheKey)) {
            outState.putString(KEY_LAST_REQUEST_CACHE_KEY, lastRequestCacheKey);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState.containsKey(KEY_LAST_REQUEST_CACHE_KEY)) {
			lastRequestCacheKey = savedInstanceState
					.getString(KEY_LAST_REQUEST_CACHE_KEY);
			spiceManager.addListenerIfPending(ContactList.class,
					lastRequestCacheKey, new ListContactsRequestListener());
			spiceManager.getFromCache(ContactList.class, lastRequestCacheKey,
					DurationInMillis.ONE_MINUTE,
					new ListContactsRequestListener());
		}
	}

    private class ListContactsRequestListener implements
        RequestListener<ContactList> {
        @Override
        public void onRequestFailure(SpiceException e) {
            Toast.makeText(ShowProjectContactsActivity.this,
                "Error during request: " + e.getLocalizedMessage(), Toast.LENGTH_LONG)
                .show();
            ShowProjectContactsActivity.this.setProgressBarIndeterminateVisibility(false);
        }
        
        @Override
		public void onRequestSuccess(ContactList contactList) {
			contactsAdapter.clear();
			if (null != contactList) {
				for (Contact nextContact : contactList) {
					contactsAdapter.add(nextContact);
				}
			} else {
				ListView lv = (ListView) findViewById(R.id.listcontactsview);
				TextView emptyText = (TextView) findViewById(R.id.emptyprojectcontactsview);
				lv.setEmptyView(emptyText);
			}
			contactsAdapter.notifyDataSetChanged();
			ShowProjectContactsActivity.this
					.setProgressBarIndeterminateVisibility(false);
		}
    }
    
}
   