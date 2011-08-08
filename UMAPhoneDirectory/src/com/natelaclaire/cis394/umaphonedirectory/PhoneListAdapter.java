package com.natelaclaire.cis394.umaphonedirectory;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * The PhoneListAdapter class is used by the ListView object that displays
 * the list of contacts
 * @author Nate
 *
 */
public class PhoneListAdapter extends BaseAdapter {

	private List<PhoneEntry> phoneList;
	
	// the context is the Activity that created the PhoneListAdapter
	// and is needed when inflating the layout
	private Context context;
	
	/**
	 * Constructor
	 * @param list of phone entries
	 * @param context (activity)
	 */
	public PhoneListAdapter(List<PhoneEntry> list, Context c) {
		phoneList = list;
		context = c;
	}
	
	/**
	 * How many items are in the data set represented by this Adapter?
	 * @return the number of entries in the list
	 */
	@Override
	public int getCount() {
		return phoneList.size();
	}

	/**
	 * Get the data item associated with the specified position in the data set.
	 * @param position in phoneList
	 * @return the PhoneEntry object in the specified position
	 */
	@Override
	public PhoneEntry getItem(int position) {
		return phoneList.get(position);
	}

	/**
	 * Get the row id associated with the specified position in the list.
	 * @param position in phoneList
	 * @return a long representing the identification number for the object in the 
	 *         specified position. Since identification numbers need not last beyond
	 *         this execution of the app, we're just returning the position supplied,
	 *         as a temporary ID.
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Get a View that displays the data at the specified position in the data set.
	 * @param position in phoneList
	 * @param the old view to reuse, if possible
	 * @param the parent that this view will eventually be attached to
	 * @return a View corresponding to the data at the specified position
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout itemLayout; // temporary reference to the layout we're updating
		PhoneEntry entry = phoneList.get(position); // get the entry at the specified position
		
		// check to see if there is a view that we can reuse
		if (convertView == null) {
			// "inflate" the layout used for contact items (defined in contact_item.xml) so that we can use it
			itemLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
		} else {
			// we already have a view that we can reuse, so assign it to the reference
			itemLayout = (LinearLayout) convertView;
		}
		
		// create a reference to the TextView with an ID of ContactName (in contact_item.xml) 
		TextView tvName = (TextView) itemLayout.findViewById(R.id.ContactName);
		tvName.setText(entry.getName()); // set the text in the TextView to the name value for this entry
		
		return itemLayout;
	}

}
