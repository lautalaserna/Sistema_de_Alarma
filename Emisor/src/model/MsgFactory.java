package model;

public class MsgFactory {

	public static Message getMessage(String name, Event e){
		return new Message(name, e);
	}
	
}
