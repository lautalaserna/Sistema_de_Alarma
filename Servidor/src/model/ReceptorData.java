package model;

import java.net.InetAddress;

import connection.Filter;

public class ReceptorData {
	private Filter f;
	private InetAddress address;
	
	public ReceptorData(Filter f, InetAddress address) {
		this.f = f;
		this.address = address;
	}
	
	public Filter getFilter() {
		return f;
	}
	
	public void setFilter(Filter f) {
		this.f = f;
	}

	public InetAddress getAddress() {
		return address;
	}
	
	public void setAddress(InetAddress address) {
		this.address = address;
	}
}
