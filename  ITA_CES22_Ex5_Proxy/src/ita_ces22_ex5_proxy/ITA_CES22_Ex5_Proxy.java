package ita_ces22_ex5_proxy;

public class ITA_CES22_Ex5_Proxy {

    public static void main(String[] args) {
        
        Server server = new Server();
        Client client = new Client();
        
        client.sendInfo();
        
        server.accept();
        
    }
    
}
