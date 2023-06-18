package controller;

import dataaccess.DB;

public class DBInstance {
	
	static DB db;

	// Singleton design
	public static DB getInstance() {
		if (db == null) {
			db = new DB();
		}

		return db;
	}
}
