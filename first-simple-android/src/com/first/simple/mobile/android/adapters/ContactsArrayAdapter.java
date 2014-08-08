package com.first.simple.mobile.android.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.first.simple.mobile.android.DownloadImagesTask;
import com.first.simple.mobile.android.activities.R;
import com.first.simple.mobile.android.model.contact.Contact;
import com.first.simple.mobile.android.model.contact.Details;
import com.first.simple.mobile.android.model.contact.EmailAddress;
import com.first.simple.mobile.android.model.contact.Phone;
import com.first.simple.mobile.android.model.contact.Reference;

public class ContactsArrayAdapter extends ArrayAdapter<Contact> implements
		OnClickListener {

	private final Context context;
	private final List<Contact> values;
	private String projectName;
	private SparseArray<EmailAddress> emailSparseArray = new SparseArray<EmailAddress>();
	private SparseArray<Phone> phoneSparseArray = new SparseArray<Phone>();

	public ContactsArrayAdapter(Context context, List<Contact> values,
			String projectName) {
		super(context, R.layout.single_contact_row, values);
		this.context = context;
		this.values = values;
		this.projectName = projectName;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final int currentOrgPos = position;

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.single_contact_row, parent,
				false);

		TextView contactName = (TextView) rowView.findViewById(R.id.label);
		Details details = values.get(position).getDetails();
		contactName.setText(details.getFirstName() + " "
				+ details.getLastName());
		
		try {
			
		   Log.i("portrait url: ", "https://exchange.allplan.com/image/logo?img_id="+details.getImageId());	
		   String URL = "https://exchange.allplan.com/image/logo?img_id="+details.getImageId();	
		   ImageView portrait = (ImageView) rowView.findViewById(R.id.portrait);
		   
		   if(details.getImageId()!=0) { 
			   portrait.setTag(URL);
			   new DownloadImagesTask().execute(portrait);	
		   } else {
			   Resources res = context.getResources();
			   if (details.getGender().equals("female")) {
				   portrait.setImageDrawable(res.getDrawable(R.drawable.avatar_female));
			   } else {
				   portrait.setImageDrawable(res.getDrawable(R.drawable.avatar_male));
			   }
			   
		   }
		   
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Error in creating portait link: ", "https://exchange.allplan.com/image/logo?img_id="+details.getImageId());
		}
		
		List<Reference> reference = values.get(position).getReferences();

		EmailAddress email = null;
		for (Reference reference2 : reference) {
			if (reference2.getContactRefType().equals("email")) {
				email = reference2.getEmailAddress();
			}
		}
		
		//emailMap.put(position, email);
		emailSparseArray.append(position, email);
	
		TextView contactEmail = (TextView) rowView.findViewById(R.id.emailtext);
		View emailButton = rowView.findViewById(R.id.email);
		
		if (null != email) {
			if (!projectName.equals("")) {
				contactEmail
				.setText(Html.fromHtml(context.getString(R.string.email)+": <a href=\"mailto:"
						+ email.getEmailId() + "?subject="+context.getString(R.string.project) + ": " + projectName+"\">" + email.getEmailId()
						+ "</a>"));
			} else {
				contactEmail
				.setText(Html.fromHtml(context.getString(R.string.email)+": <a href=\"mailto:"
						+ email.getEmailId() + "\">" + email.getEmailId()
						+ "</a>"));
			}
			contactEmail.setMovementMethod(LinkMovementMethod.getInstance());
			emailButton.setTag(Integer.valueOf(position));
			emailButton.setOnClickListener(this);
			
		} else {
			//rowView.findViewById(R.id.email).setVisibility(View.INVISIBLE);
			emailButton.setEnabled(false);
			emailButton.setBackground(context.getResources().getDrawable(R.drawable.email_button_disabled));		
			//contactEmail.setVisibility(View.INVISIBLE);
			contactEmail.setText(context.getString(R.string.email) + ": n/a");		
		}

		
		Phone phone = null;
		for (Reference reference2 : reference) {
			if (reference2.getContactRefType().equals("phone")) {
				if (reference2.getPhone().isPrimary())
					phone = reference2.getPhone();
			}
		}
		
		//phoneMap.put(position, phone);
		phoneSparseArray.append(position, phone);
		
		TextView phoneText = (TextView) rowView.findViewById(R.id.phonetext);
		View callButton = rowView.findViewById(R.id.phone);
		
		if (phone != null) {
			phoneText.setText(context.getString(R.string.phone) + ": " + phone.getNumber());
			callButton.setTag(Integer.valueOf(position));
			callButton.setOnClickListener(this);
			
		}  else {
			callButton.setEnabled(false);
			callButton.setBackground(context.getResources().getDrawable(R.drawable.phone_button_disabled));
			
			phoneText.setText(context.getString(R.string.phone)+": n/a");
			
		}

		return rowView;
	}

	private void phoneCall(Integer pos) {
		Log.i("phonepos: ", phoneSparseArray.get(pos.intValue()).getNumber());
		String phoneCallUri = "tel:" + phoneSparseArray.get(pos.intValue()).getNumber();
		Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
		phoneCallIntent.setData(Uri.parse(phoneCallUri));
		context.startActivity(phoneCallIntent);
	}
	
	
	private void sendEmail(Integer pos) {
	      Log.i("emailpos: ", emailSparseArray.get(pos.intValue()).getEmailId());
	      String[] TO = {emailSparseArray.get(pos.intValue()).getEmailId()};
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("text/plain");

	      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	      if (!projectName.equals("")) {
	    	  emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.project) + ": " + projectName);
	      }
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "");
	      try {
	         context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         Log.i("Finished sending email...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(context.getApplicationContext(), 
	         "There is no email client installed.", Toast.LENGTH_SHORT).show();
	      }
	}
	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.phone:
			phoneCall((Integer) v.getTag());
			break;
		case R.id.email:
			sendEmail((Integer) v.getTag());
			break;
		default:
			break;
		}

	}

}
