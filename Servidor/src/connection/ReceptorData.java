package connection;

import java.net.InetAddress;
import java.time.LocalDateTime;

import model.Filter;

public class ReceptorData {
	private Filter f;
	private InetAddress address;
	
	public ReceptorData(Filter f, InetAddress address) {
		this.f = f;
		this.address = address;
	}
	
	@Override
	public String toString() {
		String response;
		response = "Nuevo receptor suscripto: IP = " + this.address + ", Puerto = " + this.f.getPort() + ", Hora = " + LocalDateTime.now() + ".";
		if(this.f.isAcceptAM()) {
			response += " Admite Asistencia médica";
		}
		if(this.f.isAcceptPS()) {
			response += ", personal de seguridad";
		}
		if(this.f.isAcceptFI()) {
			response += ", foco de incendio";
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
