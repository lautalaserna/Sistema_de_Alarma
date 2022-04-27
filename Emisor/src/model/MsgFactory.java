package model;

public class MsgFactory {

	public static Message getMessage(Location loc, Event e){
		return new Message(loc, e);
	}
}
