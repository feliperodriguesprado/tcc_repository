package tcc.prototype.prototypeosgi1.helloworld.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import tcc.prototype.prototypeosgi1.helloworld.core.service.Message;

public class Activator implements BundleActivator {

	private Message message;
	private JFrame frame;
	private ServiceTracker serviceTracker;
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Start module...");
		System.out.println("Hello World!!");
		
		serviceTracker = new ServiceTracker(context, Message.class.getName(), null);
		serviceTracker.open();
		
		message = (Message) serviceTracker.getService();
		
		buildInterface();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop module...");
		System.out.println("Goodbye World!!");
		destroyInterface();
		serviceTracker.close();
	}
	
	public void destroyInterface() {
		frame.setVisible(false);
	}
	
	public void buildInterface() {
		
		frame = new JFrame("Hello");
		frame.setSize(200, 80);
		frame.getContentPane().setLayout(new BorderLayout());
		final JTextField textField = new JTextField();
		
		JButton button = new JButton("Enviar");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				message.send(textField.getText());
			}
		});
		
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		
	}

}
