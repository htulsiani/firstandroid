package com.first.simple.mobile.android.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.first.simple.mobile.android.activities.R;
import com.first.simple.mobile.android.activities.ShowProjectContactsActivity;
import com.first.simple.mobile.android.activities.ShowProjectDocumentsActivity;
import com.first.simple.mobile.android.model.project.Project;

public class ProjectsArrayAdapter extends ArrayAdapter<Project> {
	
	  private final Context context;
	  private final List<Project> values;
	  private static final String sfDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	  private String userEmail;
	  private String pwd; 
  
	public ProjectsArrayAdapter(Context context,
			List<Project> values, String userEmail, String pwd) {
		super(context, R.layout.single_project_row, values);
		this.context = context;
		this.values = values;
		this.userEmail = userEmail;
		this.pwd = pwd;
	}
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		 
		final int currentOrgPos = position;
	    
		LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.single_project_row, parent, false);
	    
	    TextView projName = (TextView) rowView.findViewById(R.id.label);
	    projName.setText(values.get(position).getGroup().getName());
	    
	    Button projContactButton = (Button) rowView.findViewById(R.id.projectcontactbutton);
	    projContactButton.setOnClickListener(new View.OnClickListener() {	
           @Override
           public void onClick(View v) {    
          	  //Toast.makeText(context,"Clicked Projects contacts button ", Toast.LENGTH_SHORT).show();
          	  startProjectContactsActivity(values.get(currentOrgPos).getOrgId(), values.get(currentOrgPos).getGuId(), values.get(currentOrgPos).getGroup().getName());
           }   
        });
		
		Button documentsButton = (Button) rowView.findViewById(R.id.projectdocumentsbutton);
		documentsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(context, "Clicked projects documents button ", Toast.LENGTH_SHORT).show();				
				startProjectDocumentsActivity(values.get(currentOrgPos).getOrgId(), values.get(currentOrgPos).getGuId(), values.get(currentOrgPos).getGroup().getName());
			}
		});
		
		return rowView;
	  }
	  
	  
	private void startProjectContactsActivity(long orgId, String projectId, String projectName) {
		Intent i = new Intent(context, ShowProjectContactsActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("userEmail", userEmail);
		i.putExtra("pwd", pwd);
		i.putExtra("orgId", orgId);
		i.putExtra("projectId", projectId);
		i.putExtra("projectName", projectName);
		context.startActivity(i);
	}
	  
	
	private void startProjectDocumentsActivity(long orgId, String projectId, String projectName) {
		Intent i = new Intent(context, ShowProjectDocumentsActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("userEmail", userEmail);
		i.putExtra("pwd", pwd);
		i.putExtra("orgId", orgId);
		i.putExtra("projectId", projectId);
		i.putExtra("start", 0L);
		i.putExtra("end", 10L);
		i.putExtra("projectName", projectName);
		context.startActivity(i);
	}
	
	  /**
	   * Converts from string to date
	   * @param dateStr
	   * @return
	   * @throws ParseException
	   */
	  public static Date convertToDateFromString(String dateStr)
				throws ParseException {
			SimpleDateFormat df = new SimpleDateFormat(sfDateFormat);
			Date date = null;
			try {
				date = (Date) df.parse(dateStr);				
			} catch (Exception e) {
				Log.d(dateStr, " Error in parsing date!");
			}
			return date;
	  }
	  
	  /**
	   * 
	   * @param bytes
	   * @return
	   */
	  public static String displaySpaceHumanReadable(long bytes) {
			int unit = 1024;
			if (bytes < unit)
				return bytes + " bytes";
			int exp = (int) (Math.log(bytes) / Math.log(unit));
			String pre = ("KMGTPE").charAt(exp - 1) + ""; 
			return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	  }
	
}
	