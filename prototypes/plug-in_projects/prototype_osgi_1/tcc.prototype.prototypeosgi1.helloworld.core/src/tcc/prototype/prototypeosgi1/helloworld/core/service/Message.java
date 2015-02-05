package tcc.prototype.prototypeosgi1.helloworld.core.service;

import java.util.ArrayList;
import java.util.List;

public class Message {
	
	private List<IMessageSender> services = new ArrayList<IMessageSender>();
	
	public void addService(IMessageSender pMessageSender) {
		services.add(pMessageSender);
	}
	
	public void removeService(IMessageSender pMessageSender) {
		services.remove(pMessageSender);
	}
	
	public void send(String pMessage) {
		
		for (IMessageSender sender : services) {
			sender.send(pMessage);
		}
	}

}
