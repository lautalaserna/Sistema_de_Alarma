package model;

import java.io.Serializable;

public class EventPersonalSeguridad implements Event, Serializable {

	@Override
	public String getEventType() {
		return "Personal de Seguridad";
	}
	
}
