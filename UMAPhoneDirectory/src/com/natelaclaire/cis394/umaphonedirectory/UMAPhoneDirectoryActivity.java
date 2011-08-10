package com.natelaclaire.cis394.umaphonedirectory;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

/**
 * The Activity is similar to an application class in a standard Java application.
 * @author Nate
 *
 */
public class UMAPhoneDirectoryActivity extends Activity {
	private ListView contactList; // the graphical list
	private ArrayList<PhoneEntry> entries; // the list data
	private PhoneListAdapter adapter; // used by the ListView to determine how to present the data

	/**
	 * Called when the activity is first created.
	 * @param a Bundle providing information about the state of the app when Android last closed it. I don't see a current need for this in my app.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // call the superclass's onCreate method
		setContentView(R.layout.main); // the View for this Activity is main.xml

		// attempt to download an XML file containing the phone entries and parse it,
		// storing the result in an ArrayList<PhoneEntry> object
		try {
			XMLParser parser = new XMLParser("http://natelaclaire.com/play/phoneEntries.xml");
			entries = parser.parse();
		} catch(Exception e) {
			// if the download is unsuccessful or the file is unable to be parsed, 
			// we'll use stale data
			entries = new ArrayList<PhoneEntry>(1);
			entries.add(new PhoneEntry("Information Center", "8778621234"));

			// since this is still in a beginning state, we'll also spit out 
			// whatever error message we receive
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(e.getMessage());
			AlertDialog alert = builder.create();
			alert.show();
		}

		// set up the list of contacts and define how the list will behave
		contactList = (ListView) findViewById(R.id.contact_list);
		adapter = new PhoneListAdapter(entries, this);
		contactList.setAdapter(adapter);
		contactList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				// when an item in the list is clicked, we'll get the phone number associated
				// with the position in the list where the click (or tap) occurred and dial it
				Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + adapter.getItem(position).getNumber()));
				startActivity(dialIntent);
			}

		});
	}
}