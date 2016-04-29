package ita_ces22_ex5_proxy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    
    private ServerSocket server;
    
    public Server (){
        try{
            server = new ServerSocket(3322);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void accept(){
        String info = "";
        
        try{
            Socket client = server.accept();
            Scanner input = new Scanner(client.getInputStream());
            String new_line="", returnInfo;
            
            new_line = input.nextLine();
            while (!new_line.equals("EndOfFile")){
                info = info + new_line + "\n";
                new_line = input.nextLine();
            }
            
            returnInfo = info + "EndOfFile\n";
            
            PrintStream out = new PrintStream(client.getOutputStream());
            out.print(returnInfo);
        }
        catch (Exception e){}
    }
    
    public void close(){
        try{
            server.close();
        }
        catch (Exception e){}
    }
    
}
