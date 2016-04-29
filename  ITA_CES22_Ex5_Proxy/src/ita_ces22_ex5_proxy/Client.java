
package ita_ces22_ex5_proxy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Client {
    
    private Socket client;
    
    public final int FW = 500;
    public final int FH = 200;
    public final int TW = 475;
    public final int TH = 100;
    public final int BW = 70;
    public final int BH = 20;
    
    public Client (int port){
        try{
            client = new Socket("127.0.0.1", port);
        }
        catch(Exception e){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
    }
    
    public void sendInfo (){
        JFrame frame;
        JTextArea text;
        JButton button;
        
        frame = new JFrame("Cliente");
        text = new JTextArea();
        button = new JButton("Enviar");
        
        text.setBounds((FW-TW)/2, 10, TW, TH);
        button.setBounds((FW-BW)/2, (TH+FH-BH)/2, BW, BH);
        
        text.setLineWrap(true);
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                
                try{
                    PrintStream output = new PrintStream (client.getOutputStream());
                    output.println(text.getText() + "\nEndOfFile");
                    
                    frame.setVisible(false);
                    frame.dispose();
                }
                catch(Exception e){
                    System.out.println ("nao foi");
                }
                
            }
        });
        
        frame.add(text);
        frame.add(button);
        
        frame.setLayout(null);
        frame.setSize(FW, FH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void printReceivedInfo(){
        try{
            Scanner input = new Scanner(client.getInputStream());
            String new_line, HTML = "";
            
            new_line = input.nextLine();
            while (!new_line.equals("EndOfFile")){
                HTML = HTML + new_line + "\n";
                new_line = input.nextLine();
            }
            
            System.out.println (HTML);
        }
        catch (Exception e){}
    }
}
