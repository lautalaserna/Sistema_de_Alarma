package model;

import java.net.InetAddress;

public class ConfirmationFactory {
	public static Confirmation getConfirmation(InetAddress address, int port, String value) {
		return new Confirmation(address, port, value);
	}
}
