package com.first.simple.mobile.android.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.util.Log;
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
import com.first.simple.mobile.android.adapters.ProjectsArrayAdapter;
import com.first.simple.mobile.android.model.project.Project;
import com.first.simple.mobile.android.model.project.ProjectList;
import com.first.simple.mobile.android.model.project.ProjectsResponse;
import com.first.simple.mobile.android.requests.ProjectsRequest;

public class ShowOrgaProjectsActivity extends Activity {
	
	private static final String KEY_LAST_REQUEST_CACHE_KEY = "lastRequestCacheKey";

    private SpiceManager spiceManager = new SpiceManager(
        JacksonSpringAndroidSpiceService.class);
  
    private ProjectsArrayAdapter projectsAdapter;
    
    private String lastRequestCacheKey;
    
	private String userName;
	private String password;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState);
          requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);     
          setContentView(R.layout.organization_list);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	setContentView(R.layout.projects_list);
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
     
    private void initUIComponents() {
    	
    	Intent i = getIntent();
    	
    	userName = (String) i.getExtras().get("userEmail");
    	password = (String) i.getExtras().get("pwd");
    	long orgId = (Long) i.getExtras().get("orgId");
    	String orgName = i.getExtras().getString("orgName");
    	
    	performRequest(userName, password, orgId);
    	
        final ListView listview = (ListView) findViewById(R.id.listprojectsview);
          
        projectsAdapter = new ProjectsArrayAdapter(this, new ArrayList<Project>(), userName, password); 
        listview.setAdapter(projectsAdapter);

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
        
        /** set orgname to the action bar */
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.projectlist_actionbar_subtitle);
        actionBar.setSubtitle(orgName);
        actionBar.setIcon(R.drawable.orga_icon_nobgr);  
        //actionBar.show();
	    actionBar.setDisplayOptions(actionBar.getDisplayOptions()
	            | ActionBar.DISPLAY_SHOW_CUSTOM);
	    ImageView imageView = new ImageView(actionBar.getThemedContext());
	    imageView.setScaleType(ImageView.ScaleType.CENTER);
	    imageView.setImageResource(R.drawable.project_icon_nobgr);
	    ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
	            ActionBar.LayoutParams.WRAP_CONTENT,
	            ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
	                    | Gravity.CENTER_VERTICAL);
	    layoutParams.rightMargin = 20;
	    imageView.setLayoutParams(layoutParams);
	    actionBar.setCustomView(imageView);
                             
    }
 
    private void performRequest(String userEmail, String userPwd, long orgId) {
        ShowOrgaProjectsActivity.this.setProgressBarIndeterminateVisibility(true);
        ProjectsRequest request = new ProjectsRequest(userEmail, userPwd, orgId);
        lastRequestCacheKey = request.createCacheKey();
        spiceManager.execute(request, lastRequestCacheKey,
        		DurationInMillis.ONE_HOUR, new ListAuthenticateRequestListener());
        
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        
        case R.id.action_logout:
    		
        	spiceManager.removeAllDataFromCache();
        	spiceManager.cancelAllRequests();
			ShowOrgaProjectsActivity.this.setProgressBarIndeterminateVisibility(true);	
			Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			
            return true;
            
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                // This activity is NOT part of this app's task, so create a new task
                // when navigating up, with a synthesized back stack.
                TaskStackBuilder.create(this)
                        // Add all of this activity's parents to the back stack
                        .addNextIntentWithParentStack(upIntent)
                        // Navigate up to the closest parent
                        .startActivities();
            } else {
                NavUtils.navigateUpTo(this, upIntent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
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
            spiceManager.addListenerIfPending(ProjectList.class,
                lastRequestCacheKey, new ListAuthenticateRequestListener());
            spiceManager.getFromCache(ProjectList.class,
                lastRequestCacheKey, DurationInMillis.ONE_MINUTE,
                new ListAuthenticateRequestListener());
        }
    }

    private class ListAuthenticateRequestListener implements
        RequestListener<ProjectList> {
        @Override
        public void onRequestFailure(SpiceException e) {
            Toast.makeText(ShowOrgaProjectsActivity.this,
                "Error during request: " + e.getLocalizedMessage(), Toast.LENGTH_LONG)
                .show();
            ShowOrgaProjectsActivity.this.setProgressBarIndeterminateVisibility(false);
        }
        
        @Override
        public void onRequestSuccess(ProjectList projectList) {
        	
        	projectsAdapter.clear();
        	
        	if(null != projectList){
        		for(ProjectsResponse nextProjectRes : projectList){
            		projectsAdapter.add(nextProjectRes.getProject());
            	}
        	}
        	else{
        		ListView lv = (ListView)findViewById(R.id.listprojectsview);
        		TextView emptyText = (TextView)findViewById(R.id.emptyprojectsview);
        		lv.setEmptyView(emptyText);
        	}
        	
        	projectsAdapter.notifyDataSetChanged();
        	ShowOrgaProjectsActivity.this.setProgressBarIndeterminateVisibility(false);
        	
        }
    }
    
}
   