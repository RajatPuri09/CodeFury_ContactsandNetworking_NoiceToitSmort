package com.contactapp.utils;

import java.util.Comparator;

import com.contactapp.models.Human;

public class ContactAppNameComparator implements Comparator<Human>
{

	public int compare(Human h1, Human h2) {
		return h1.getFullName().compareToIgnoreCase(h2.getFullName());
	
	}

}
