import java.awt.Container;
import java.awt.FlowLayout;
import java.rmi.Naming;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ClientReceive extends JFrame
{	
	
	JLabel label1 = new JLabel("Mensagem");		
	static JTextArea textArea = new JTextArea(15,30);
	JScrollPane scroll = new JScrollPane(textArea);
	
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();

	public ClientReceive()
	{
		super ("Recebendo mensagem...");
		Container container = getContentPane();
	    container.setLayout( new FlowLayout());
	     		    
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	     
	    jp1.add(label1);
	    jp2.add(scroll);
	   
	    container.add(jp1);
	    container.add(jp2);
	    
	    setSize(400,350);
	    setVisible(true);
	    setResizable(false);
	}

	public static void main(String args[])
	{
		ClientReceive prog = new ClientReceive();  
		
		int num=1;
		ServerInterface x;
		try {
			x = (ServerInterface) Naming.lookup("rmi://localhost/ChatServer");
		
			for(;;)
			{
				Message msg = x.receive(num);
				if (msg!=null)
				{
					textArea.append(msg.getAuthor()+": "+msg.getContent()+"\n");
					num++;
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
	   	}
	}
}