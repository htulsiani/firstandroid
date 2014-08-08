package com.allplan.exchange.mobile.android.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.allplan.exchange.mobile.android.activities.R;
import com.allplan.exchange.mobile.android.activities.ShowOrgaContactsActivity;
import com.allplan.exchange.mobile.android.activities.ShowOrgaProjectsActivity;
import com.allplan.exchange.mobile.android.model.organization.Organization;
import com.allplan.exchange.mobile.android.util.CustomDrawableView;

public class OrganizationsArrayAdapter extends ArrayAdapter<Organization> {
	
	  private final Context context;
	  private final List<Organization> values;
	  private static final String sfDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	  private String userEmail;
	  private String pwd;
	  CustomDrawableView mCustomDrawableView;
  
	public OrganizationsArrayAdapter(Context context,
			List<Organization> values, String userEmail, String pwd) {
		super(context, R.layout.single_organization_row, values);
		this.context = context;
		this.values = values;
		this.userEmail = userEmail;
		this.pwd = pwd;
	}
	  
	/* (non-Javadoc)
	* @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	*/
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  
		final int currentOrgPos = position;
	    
		LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.single_organization_row, parent, false);
	    
	    TextView orgName = (TextView) rowView.findViewById(R.id.label);
	    orgName.setText(values.get(position).getOrgName());
	    
	    TextView validUntil = (TextView) rowView.findViewById(R.id.validuntil); 
	    String expirationDate = new SimpleDateFormat("dd.MM.yyyy").format(convertToDateFromString(values.get(position).getValidUntil()));	    
	    if (expirationDate.equals(context.getString(R.string.expirationdate))) {
	    	validUntil.setText(context.getString(R.string.expirationdate_label) + ": " + context.getString(R.string.unlimited));
	    } else {
	    	validUntil.setText(context.getString(R.string.expirationdate_label) + ": " +expirationDate);
	    }
	    
	    TextView availMemory = (TextView) rowView.findViewById(R.id.availmemory);
	    Long freeDiscSpaceInBytes = values.get(position).getOrgFreeDiskSpace();	    
		freeDiscSpaceInBytes = freeDiscSpaceInBytes > 0 ? freeDiscSpaceInBytes/1024/1024 : 0;
		freeDiscSpaceInBytes = Math.abs(freeDiscSpaceInBytes);
		
		availMemory.setText(context.getString(R.string.totalspace) + ": " + displaySpaceHumanReadable(values.get(position).getOrgTotalDiskQuota()));
		
	    mCustomDrawableView = (CustomDrawableView) rowView.findViewById(R.id.customdrawableview);  
	    mCustomDrawableView.setFreeDicsSpace(values.get(position).getOrgFreeDiskSpace());
	    mCustomDrawableView.setTotalDiscSpace(values.get(position).getOrgTotalDiskQuota());

		Button orgContactButton = (Button) rowView.findViewById(R.id.orgcontactbutton);
		orgContactButton.setOnClickListener(new View.OnClickListener() {	
           @Override
           public void onClick(View v) {     	
          	  startOrgaContactsActivity(values.get(currentOrgPos).getOrgId(),
          			  values.get(currentOrgPos).getOrgName());
           }   
        });
		
		Button projectButton = (Button) rowView.findViewById(R.id.projectbutton);
		projectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startProjectsActivity(values.get(currentOrgPos).getOrgId(),
					  values.get(currentOrgPos).getOrgName());
			}
		});
	
	    return rowView;
	  }
	 
	
	private void startOrgaContactsActivity(long orgId,String orgName) {
		Intent i = new Intent(context, ShowOrgaContactsActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("userEmail", userEmail);
		i.putExtra("pwd", pwd);
		i.putExtra("orgId", orgId);
		i.putExtra("orgName", orgName);
		context.startActivity(i);
	}
	
	private void startProjectsActivity(long orgId,String orgName) {
		Intent i = new Intent(context, ShowOrgaProjectsActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("userEmail", userEmail);
		i.putExtra("pwd", pwd);
		i.putExtra("orgId", orgId);
		i.putExtra("orgName", orgName);
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
	
