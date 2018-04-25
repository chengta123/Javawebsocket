package websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketEngine;
//import org.openide.modules.OnStart;

//@OnStart()
public class StartMain {
   
//    public void instanceMethod() throws IOException
//    {
//        final HttpServer server = HttpServer.createSimpleServer("/var/www", 9090);
//        final WebSocketAddOn addon = new WebSocketAddOn();
//    
//        BufferedReader br = null;
//        String line = null;
//        
//        for (NetworkListener listener : server.getListeners()) {
//            listener.registerAddOn(addon);
//        }  
//        
//        SocketServer socketServer = new SocketServer();
//        //WebSocketEngine.getEngine().register("/", socketServer); 
//        WebSocketEngine.getEngine().register(socketServer);
//        server.start();
//        List<WebSocket> sockets = socketServer.getSockets(); 
//        
//        br = new BufferedReader(new InputStreamReader(System.in));
//
//        while ((line = br.readLine()) != null) {
//            for (WebSocket webSocket : sockets) {
//                webSocket.send(line);
//            }
//        }        
//        server.stop();        
//    }
        
    public static void main(String[] args) throws Exception {

        //new StartMain().instanceMethod();   //@@@   

        BufferedReader br = null;
        String line = null;        
        
        SocketServer socketServer = new SocketServer();

        HttpServer server = HttpServer.createSimpleServer("/var/www", 9090);
        server.getListener("grizzly").registerAddOn(new WebSocketAddOn());
        WebSocketEngine.getEngine().register("/", socketServer);
        server.start();

        List<WebSocket> sockets = socketServer.getSockets();
        br = new BufferedReader(new InputStreamReader(System.in));

        while ((line = br.readLine()) != null) {
            for (WebSocket webSocket : sockets) {
                webSocket.send(line);
            }
        }
        server.stop();
    }

}