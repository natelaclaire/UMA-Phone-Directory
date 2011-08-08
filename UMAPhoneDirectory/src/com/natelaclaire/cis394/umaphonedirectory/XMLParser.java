package com.natelaclaire.cis394.umaphonedirectory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

/**
 * Parse the XML file containing phone entries
 * @author Nate
 *
 */
public class XMLParser {
	private ArrayList<PhoneEntry> entries; // a reference to our ArrayList of entries
	private final URL xmlURL; // the URL for the XML file
	
	/**
	 * Constructor takes the URL and instantiates the xmlURL object, assuming
	 * the URL is not malformed, or throws an exception otherwise
	 * @param URL for XML file
	 */
	public XMLParser(String url) {
		try {
			this.xmlURL = new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Opens a connection to the XML file's URL and returns the InputStream,
	 * or throws an exception if that doesn't work 
	 * @return InputStream for XML file
	 */
	public InputStream getInputStream() {
		try {
			return xmlURL.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Parse the XML file
	 * @return ArrayList of phone entries in the XML file
	 */
	public ArrayList<PhoneEntry> parse() {
		final PhoneEntry currentEntry = new PhoneEntry(); // object used to store temporary data before adding it to the ArrayList
		entries = new ArrayList<PhoneEntry>();
		RootElement root = new RootElement("entries"); // root of XML document
		
		Element entry = root.getChild("entry"); // XML element that describes an entire phone entry
		entry.setEndElementListener(new EndElementListener() {

			/**
			 * Called when the end of an "entry" element is reached
			 */
			@Override
			public void end() {
				entries.add(currentEntry.copy()); // get a new object with the current entry's
				                                  // values and add it to the ArrayList
			}
			
		});
		
		// the "name" XML element contains the name associated with the entry
		entry.getChild("name").setEndTextElementListener(new EndTextElementListener() {

			/**
			 * Called when the end of the name element is reached
			 */
			@Override
			public void end(String text) {
				currentEntry.setName(text); // replace the name field with the name element's value
			}
			
		});
		
		// the "number" XML element contains the phone number associated with the entry
		entry.getChild("number").setEndTextElementListener(new EndTextElementListener() {

			/**
			 * Called when the end of the number element is reached
			 */
			@Override
			public void end(String text) {
				currentEntry.setNumber(text); // replace the number field with the number element's value
			}
			
		});
		try {
			// call the static Xml.parse() method to initiate the process of parsing
			// the XML document, passing it the InputStream, encoding type, and content handler
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return entries; // return the ArrayList to the calling method
	}
	
}
