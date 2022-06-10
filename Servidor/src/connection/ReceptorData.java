package connection;

import java.io.Serializable;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.Filter;

public class ReceptorData implements Serializable{
	private Filter f;
	private InetAddress address;
	
	public ReceptorData(Filter f, InetAddress address) {
		this.f = f;
		this.address = address;
	}
	
	@Override
	public String toString() {
		String response;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		response = "Dir: " + this.address.getHostAddress() + ":" + this.f.getPort() + 
				" / Fecha: " + dateFormat.format(new Date(Calendar.getInstance().getTimeInMillis())) + 
				" / Eventos Aceptados:";
		if(this.f.isAcceptAM()) {
			response += " AM";
		}
		if(this.f.isAcceptPS()) {
			response += " PS";
		}
		if(this.f.isAcceptFI()) {
			response += " FI";
		}
		return response;
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
