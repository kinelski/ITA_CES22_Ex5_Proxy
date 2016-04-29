package ita_ces22_ex5_proxy;

import java.io.IOException;
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
        try{
            Socket client = server.accept();
            Scanner input = new Scanner(client.getInputStream());
            
            while (input.hasNextLine())
                System.out.println (input.nextLine());
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
