package com.natelaclaire.cis394.umaphonedirectory;

/**
 * Defines an entry in a phone directory, including name and number.
 * @author Nate
 *
 */
public class PhoneEntry {
	private String name;
	private String number;
	
	/**
	 * Two-parameter constructor
	 * @param name
	 * @param number
	 */
	public PhoneEntry(String name, String number) {
		this.name = name;
		this.number = number;
	}

	/**
	 * No-parameter constructor
	 */
	public PhoneEntry() {
		name = "";
		number = "";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * copy() method is used by XmlParser when loading phone entries from XML 
	 * @return a new PhoneEntry object that is an exact copy of the current object
	 */
	public PhoneEntry copy() {
		PhoneEntry copy = new PhoneEntry(this.name, this.number);
		return copy;
	}
	
}
