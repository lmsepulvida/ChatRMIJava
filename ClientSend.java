import java.awt.Container;
import java.awt.FlowLayout;
import java.rmi.Naming;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ClientSend extends JFrame
{
		
	public static void main(String args[])
	{
		ClientSend prog = new ClientSend();
	}
	
	JLabel label1 = new JLabel("Autor");
	JTextField fieldAutor = new JTextField(25);
	
	JLabel label2 = new JLabel("Mensagem");
	JTextArea textArea = new JTextArea(10,30);
    JScrollPane scroll = new JScrollPane(textArea);
	       
    JButton send = new JButton("Enviar");
    
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();
	
	public ClientSend()
	{
		super ("Digite uma mensagem");
		Container container = getContentPane();
	    container.setLayout( new FlowLayout());
	     
	    Ouvinte ouvinte = new Ouvinte();
	     
	    send.addActionListener(ouvinte);
	    
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	     
	    jp1.add(label1);
	    jp1.add(fieldAutor);
	    jp2.add(label2);
	    jp3.add(scroll);
	    jp4.add(send);
	    
	    container.add(jp1);
	    container.add(jp2);
	    container.add(jp3);
	    container.add(jp4); 
	    
	    setSize(400,350);
	    setVisible(true);
	    setResizable(false);
	}

	class Ouvinte implements ActionListener {
      
        public void actionPerformed(ActionEvent event) {
 
            ServerInterface x;
    		try {
    			x = (ServerInterface) Naming.lookup("rmi://localhost/ChatServer");

            	if (event.getSource()== send)
            	{
            		String autor = fieldAutor.getText();
            		String texto = textArea.getText();
                
            		Message msg = new Message(autor, texto);
    				x.send(msg);
    				textArea.setText("");
            	}
            
        	}
    		catch(Exception e) {
    			System.out.println(e);
    		}
        }

   }
}
