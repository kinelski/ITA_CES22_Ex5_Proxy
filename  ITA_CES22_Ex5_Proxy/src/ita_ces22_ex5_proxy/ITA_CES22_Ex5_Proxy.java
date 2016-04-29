package ita_ces22_ex5_proxy;

public class ITA_CES22_Ex5_Proxy {

    public static void main(String[] args) {
        Server server = new Server(3324);
        Proxy proxy = new Proxy(server, 3324);
        
        proxy.clientRequest();
        proxy.sendRequestToServer();
        proxy.getAnswerFromServer();
        proxy.sendAnswerToClient();
    }
    
}
