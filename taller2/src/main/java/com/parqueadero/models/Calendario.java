package com.parqueadero.models;

import java.util.Calendar;

public class Calendario {
	
	public int getActualDay(){
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
}
