package model;

import java.io.Serializable;

public class EventAsistenciaMedica implements Event, Serializable{

	@Override
	public String getEventType() {
		return "Asistencia Médica";
	}
	
}
