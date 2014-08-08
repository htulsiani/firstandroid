package com.allplan.exchange.mobile.android.activities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;

import android.app.ActionBar;
import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.allplan.exchange.mobile.android.adapters.DocumentsArrayAdapter;
import com.allplan.exchange.mobile.android.model.document.Document;
import com.allplan.exchange.mobile.android.model.document.DocumentList;
import com.allplan.exchange.mobile.android.model.document.File;
import com.allplan.exchange.mobile.android.model.document.LatestVersion;
import com.allplan.exchange.mobile.android.requests.DocumentsRequest;
import com.allplan.exchange.mobile.android.util.Constants;


public class ShowProjectDocumentsActivity extends ExpandableListActivity {
	
	private static final String KEY_LAST_REQUEST_CACHE_KEY = "lastRequestCacheKey";
	
	ProgressDialog mProgressDialog; 
	private PowerManager.WakeLock mWakeLock;
	
	private SpiceManager spiceManager = new SpiceManager(
        JacksonSpringAndroidSpiceService.class);
    
    private static final String NAME = "Document Name";
    
    List<String> groupList = new ArrayList<String>();
    List<File> childList = new ArrayList<File>();
    
    Map<String, List<File>> documentCollection = new HashMap<String, List<File>>(); 
    private DocumentsArrayAdapter documentsAdapter;
    
    private String lastRequestCacheKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState);
          requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
          setContentView(R.layout.documents_list);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	setContentView(R.layout.documents_list);
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
    			ShowProjectDocumentsActivity.this.setProgressBarIndeterminateVisibility(true);	
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
    	
    	final String userName = (String) i.getExtras().get("userEmail");
    	final String password = (String) i.getExtras().get("pwd");
    	long orgId = (Long) i.getExtras().get("orgId");
    	String projectId = (String) i.getExtras().get("projectId");
    	long start = (Long) i.getExtras().get("start");
    	long end = (Long) i.getExtras().get("end");
    	String projectName = (String) i.getExtras().get("projectName");
    	
    	performRequest(userName, password, orgId, projectId, start, end);
    	
    	documentsAdapter = new DocumentsArrayAdapter(this, groupList, documentCollection);
    	
    	setListAdapter(documentsAdapter);
        
    	
        getExpandableListView().setOnChildClickListener(new OnChildClickListener(){
        	public boolean onChildClick(ExpandableListView parent, View v,
        				int groupPosition, int childPosition,
							long id) {
						try {
							
					    	File clickedFile = (File) documentsAdapter
									.getChild(groupPosition, childPosition);
							if (clickedFile.getExtension().equalsIgnoreCase(
									"pdf") || clickedFile.getExtension().equalsIgnoreCase(
											"jpg")) {
								
								//Progress dialog to display during file download
						    	mProgressDialog = new ProgressDialog(ShowProjectDocumentsActivity.this);
						    	mProgressDialog.setMessage(getResources().getString(R.string.filedownloadinprogress));
						    	mProgressDialog.setIndeterminate(true);
						    	mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						    	mProgressDialog.setCancelable(true);
						    	
						    	final FileDownloadTask downloadTask = new FileDownloadTask(
										userName, password, clickedFile
												.getFileEntryId(),
												getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),clickedFile.getTitle(),clickedFile.getExtension(),ShowProjectDocumentsActivity.this);
								
								downloadTask.execute(new String[]{});
								
								mProgressDialog
										.setOnCancelListener(new DialogInterface.OnCancelListener() {
											@Override
											public void onCancel(
													DialogInterface dialog) {
												downloadTask.cancel(true);
											}
										});
								
								return true;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return false;
					}
        });
           
        /** set project name to the subtitle of action bar */
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.projectdocumentlist_actionbar_subtitle);
        actionBar.setSubtitle(projectName);
        actionBar.setIcon(R.drawable.project_icon_nobgr);
        //actionBar.show(); 
	    actionBar.setDisplayOptions(actionBar.getDisplayOptions()
	            | ActionBar.DISPLAY_SHOW_CUSTOM);
	    ImageView imageView = new ImageView(actionBar.getThemedContext());
	    imageView.setScaleType(ImageView.ScaleType.CENTER);
	    imageView.setImageResource(R.drawable.document_icon_nobgr);
	    ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
	            ActionBar.LayoutParams.WRAP_CONTENT,
	            ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
	                    | Gravity.CENTER_VERTICAL);
	    layoutParams.rightMargin = 20;
	    imageView.setLayoutParams(layoutParams);
	    actionBar.setCustomView(imageView);
        
        
    }
    
    
    private void performRequest(String userEmail, String userPwd, long orgId, String projectId, long start, long end) {
        ShowProjectDocumentsActivity.this.setProgressBarIndeterminateVisibility(true);
        DocumentsRequest request = new DocumentsRequest(userEmail, userPwd, orgId, projectId,start,end);
        lastRequestCacheKey = request.createCacheKey();
        spiceManager.execute(request, lastRequestCacheKey,
            DurationInMillis.ONE_HOUR, new ListDocumentsRequestListener());
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
			spiceManager.addListenerIfPending(DocumentList.class,
					lastRequestCacheKey, new ListDocumentsRequestListener());
			spiceManager.getFromCache(DocumentList.class, lastRequestCacheKey,
					DurationInMillis.ONE_MINUTE,
					new ListDocumentsRequestListener());
		}
	}
    
    private class ListDocumentsRequestListener implements
        RequestListener<DocumentList> {
        @Override
        public void onRequestFailure(SpiceException e) {
            Toast.makeText(ShowProjectDocumentsActivity.this,
                "Error during request: " + e.getLocalizedMessage(), Toast.LENGTH_LONG)
                .show();
            ShowProjectDocumentsActivity.this.setProgressBarIndeterminateVisibility(false);
        }
        
        @Override
		public void onRequestSuccess(DocumentList documentList) {
//			documentsAdapter.clear();
			if (null != documentList) {
				populateGroupAndChildData(documentList);
				} else {
					//TODO fixme
			}
			
			documentsAdapter.notifyDataSetChanged();
			ShowProjectDocumentsActivity.this
					.setProgressBarIndeterminateVisibility(false);
		}
    }
    
	private void populateGroupAndChildData(DocumentList documentList) {
		groupList.clear();
		
		documentCollection.clear();
		
		for (Document nextDoc : documentList) {
			childList = new ArrayList<File>();
			StringBuilder docNameAndversionInfo = new StringBuilder();
			docNameAndversionInfo.append(nextDoc.getPlanName());
			docNameAndversionInfo.append(";");
			LatestVersion latestVersion = nextDoc.getLatestVersion();
			if (null != latestVersion) {
				docNameAndversionInfo.append(latestVersion.getIndex());
				docNameAndversionInfo.append(";");
				docNameAndversionInfo.append(latestVersion.getUploadedBy());
				List<File> files = latestVersion.getFiles();
				for (File nextFile : files) {
					childList.add(nextFile);
				}
			}
			groupList.add(docNameAndversionInfo.toString());
			documentCollection.put(docNameAndversionInfo.toString(), childList);
		}
	}
	
	
	class FileDownloadTask extends AsyncTask<String, Integer, String>  {
		
		private Context context;
		private String userEmail;
		private String pwd;
		private long fileEntryId;
		private String fileName;
		private java.io.File filesDir;
		private String fileExt;
		Intent intent = null;
		
		FileDownloadTask(String userEmail, String pwd, long fileEntryId, java.io.File filesDir, String fileName, String fileExt, Context context) {
			this.userEmail = userEmail;
			this.pwd = pwd;
			this.fileEntryId=fileEntryId;
			this.filesDir = filesDir;
			this.fileName = fileName;
			this.fileExt = fileExt;
			this.context = context;
		}
		
		@Override
		protected String doInBackground(String... urls) {
			String url = Constants.filedownloadaddress + "?entryId="
					+ fileEntryId + "&rest=" + Boolean.TRUE;
			HttpAuthentication httpAuthentication = null;
			HttpClient client = null;
			HttpPost post = null;
			HttpResponse response = null;
			
			try{
			httpAuthentication = new HttpBasicAuthentication(
						userEmail, pwd);
			client = new DefaultHttpClient();
			post = new HttpPost(url);
			post.setHeader("Authorization",httpAuthentication.getHeaderValue());
			response = client.execute(post);
	        
	        String filePath = filesDir + fileName;
	        
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					copyFile(response.getEntity().getContent(),
							new java.io.File(filePath));
					intent = new Intent(Intent.ACTION_VIEW);

					if (fileExt.equalsIgnoreCase("pdf"))
						intent.setDataAndType(Uri.parse("file://" + filePath),
								"application/pdf");
					else if (fileExt.equalsIgnoreCase("jpg"))
						intent.setDataAndType(Uri.parse("file://" + filePath),
								"image/jpeg");

				}
	        System.out.println("response"+response.getStatusLine().getStatusCode());	
			}
			catch(ClientProtocolException cpe){
				cpe.printStackTrace();
			}
			catch(IOException cpe){
				cpe.printStackTrace();
			}
		
			return null;
	    }
		
		 private void copyFile(InputStream in, java.io.File outPutFile) throws IOException
		    {
			 FileOutputStream fos = null;
			 try{
				  fos = new FileOutputStream(outPutFile);
			    	byte[] buffer = new byte[1024];
			        int read;
			        while ((read = in.read(buffer)) != -1)
			        {
			        	fos.write(buffer, 0, read);
			        	fos.flush();
			        } 
			 }
			 	catch(Exception e){
			 		e.printStackTrace();
			 		return;
			 	}
			 finally{
				 if(null != fos){
					 fos.close();
				 }
			}
			 	
		    }
		
		 @Override
		    protected void onPreExecute() {
		        super.onPreExecute();
		        // take CPU lock to prevent CPU from going off if the user 
		        // presses the power button during download
		        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
		             getClass().getName());
		        mWakeLock.acquire();
		        mProgressDialog.show();
		  }
		 
		@Override
		protected void onProgressUpdate(Integer... progress) {
			super.onProgressUpdate(progress);
			// if we get here, length is known, now set indeterminate to false
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setMax(100);
			mProgressDialog.setProgress(progress[0]);
		}
			
		@Override
		protected void onPostExecute(String result) {
			if (result != null)
				Toast.makeText(context, "Download error: " + result,
						Toast.LENGTH_LONG).show();
			else {
				mProgressDialog.hide();
				context.startActivity(intent);
			}

		}
			

	}
	

}
   