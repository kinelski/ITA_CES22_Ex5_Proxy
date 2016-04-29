package ita_ces22_ex5_proxy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    
    private ServerSocket server;
    private Socket client;
    private String request;
    
    public Server (int port){
        try{
            server = new ServerSocket(port);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void accept(){
        try{
            client = server.accept();
        }
        catch (Exception e){}
    }
    
    public void clientRequest(){
        request = "";
        
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
            e.printStackTrace();
        }
    }
    
    public void getAnswer(){
        String answer;
        
        clientRequest();
        answer = request;
        
        try{
            PrintStream out = new PrintStream(client.getOutputStream());
            out.print(answer + "EndOfFile\n");
        }
        catch(Exception e){}
    }
    
    public void close(){
        try{
            server.close();
        }
        catch (Exception e){}
    }
    
}
