package com.allplan.exchange.mobile.android.adapters;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.allplan.exchange.mobile.android.activities.R;
import com.allplan.exchange.mobile.android.model.document.File;

	public class DocumentsArrayAdapter extends BaseExpandableListAdapter {
	
		private Activity context;
	    private Map<String, List<File>> documentCollections;
	    private List<String> documents;
 
		LayoutInflater layoutInflater;
        int title;
        
		public DocumentsArrayAdapter(Activity context, List<String> documents,
	            Map<String, List<File>> documentCollections){
			this.context = context;
	        this.documentCollections = documentCollections;
	        this.documents = documents;
	}
		
	public Object getChild(int groupPosition, int childPosition) {
		return documentCollections.get(documents.get(groupPosition)).get(
				childPosition);
	}
		 
	  public long getChildId(int groupPosition, int childPosition) {
	        return childPosition;
	    }
	  
		public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final File versionFile = (File) getChild(groupPosition, childPosition);
		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.single_document_child_row,
					null);
		}

		TextView item = (TextView) convertView.findViewById(R.id.version);
		item.setText(versionFile.getTitle());
		return convertView;
	}
	 
	public int getChildrenCount(int groupPosition) {
		return documentCollections.get(documents.get(groupPosition)).size();
	}
     
        public Object getGroup(int groupPosition) {
            return documents.get(groupPosition);
        }
     
        public int getGroupCount() {
            return documents.size();
        }
     
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        
        public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		String docNameAndVersionInfo[] = ((String) getGroup(groupPosition))
				.split(";");

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(
					R.layout.single_document_group_row, null);
		}
		
		
		TextView item = (TextView) convertView.findViewById(R.id.document);
		
		item.setText(Html.fromHtml(makeDocInfoString(docNameAndVersionInfo)));
			
		return convertView;
	}
     
        
	private String makeDocInfoString(String[] docNameAndVersionInfo) {

		StringBuilder docInfoBuilder = new StringBuilder();

		if (docNameAndVersionInfo[0] != null)
			docInfoBuilder.append("<strong>"+docNameAndVersionInfo[0]+"</strong>");

		if (docNameAndVersionInfo[1] != null) {
			docInfoBuilder.append("<br />" + context.getString(R.string.current_index) + ": ");
			docInfoBuilder.append(docNameAndVersionInfo[1]);
		}

		if (docNameAndVersionInfo[2] != null) {
			docInfoBuilder.append("<br />" + context.getString(R.string.uploadedby) + ": ");
			docInfoBuilder.append(docNameAndVersionInfo[2]);
		}		
		return docInfoBuilder.toString();
	}
        
        public boolean hasStableIds() {
            return true;
        }
     
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
        
}
	


	

