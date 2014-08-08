package com.allplan.exchange.mobile.android.requests;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.allplan.exchange.mobile.android.util.Constants;

	public class FileDownloadTask1 extends AsyncTask<String, Integer, String>  {
		
	private Context context;
	private String userEmail;
	private String pwd;
	private long fileEntryId;
	private String fileName;
	private File filesDir;
	Intent intent = null;
	
	public FileDownloadTask1(String userEmail, String pwd, long fileEntryId, File filesDir, String fileName, Context context) {
		this.userEmail = userEmail;
		this.pwd = pwd;
		this.fileEntryId=fileEntryId;
		this.filesDir = filesDir;
		this.fileName = fileName;
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
				copyFile(response.getEntity().getContent(), new File(filePath));
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.parse("file://" + filePath),
						"application/pdf");
			
			}
        }
		catch(ClientProtocolException cpe){
			cpe.printStackTrace();
		}
		catch(IOException cpe){
			cpe.printStackTrace();
		}
	
		return null;
    }
	
	 private void copyFile(InputStream in, File outPutFile) throws IOException
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
	    protected void onProgressUpdate(Integer... progress) {
	        super.onProgressUpdate(progress);
	        
	    }
		
		@Override
	protected void onPostExecute(String result) {
		if (result != null)
			Toast.makeText(context, "Download error: " + result,
					Toast.LENGTH_LONG).show();
		else {
			context.startActivity(intent);
		}

	}
		

}
