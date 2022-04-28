package model;

import java.io.Serializable;

public class EventFocoIncendio implements Event, Serializable{

	@Override
	public String getEventType() {
		return "FI";
	}
	
}
