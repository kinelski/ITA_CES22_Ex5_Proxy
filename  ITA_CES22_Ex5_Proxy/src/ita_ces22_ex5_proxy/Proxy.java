
package ita_ces22_ex5_proxy;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Proxy {
    
    private String answer;
    private String request;
    
    private Client myClient;
    private Socket client;
    private Server server;
    private ServerSocket proxyServer;
    private Socket proxyClient;
    
    public Proxy(Server server, int port){
        try{
            proxyClient = new Socket("127.0.0.1", port);
            proxyServer = new ServerSocket(3321);
            myClient = new Client(3321);
            
            this.server = server;
            client = proxyServer.accept();
            server.accept();
        }
        catch(Exception e){
            System.out.println("exc");
        }
    }
    
    public void clientRequest(){
        request = "";
        
        myClient.sendInfo();
        
        try{
            
            Scanner input = new Scanner(client.getInputStream());
            String new_line="";
            
            new_line = input.nextLine();
            while (!new_line.equals("EndOfFile")){
                request = request + new_line + "\n";
                new_line = input.nextLine();
            }
            
        }
        catch(Exception e){
            System.out.println ("erro");
        }
    }
    
    public void sendRequestToServer(){
        try{
            PrintStream output = new PrintStream (proxyClient.getOutputStream());
            output.println(request + "\nEndOfFile");
        }
        catch(Exception e){
            System.out.println ("nao foi");
        }
    }
    
    public void getAnswerFromServer(){
        String answer = "";
        
        server.getAnswer();
        
        try{
            
            Scanner input = new Scanner(proxyClient.getInputStream());
            String new_line="";
            
            new_line = input.nextLine();
            while (!new_line.equals("EndOfFile")){
                answer = answer + new_line + "\n";
                new_line = input.nextLine();
            }
            
        }
        catch(Exception e){
            System.out.println ("erro");
        }
    }
    
    public void sendAnswerToClient(){
        try{
            PrintStream output = new PrintStream (client.getOutputStream());
            output.println(request + "\nEndOfFile");
            
            myClient.printReceivedInfo();
        }
        catch(Exception e){
            System.out.println ("nao foi");
        }
    }
    
}