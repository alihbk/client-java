package signalgo.client;

import java.io.IOException;

/**
 * Created by white on 2016-08-17.
 */
public class Main {

    /**
     * Created by mehdi akbarian on 2016-08-06.
     */
        static Connector connector;
        static boolean a = true;
        static TestService service;
        public static void main(String[] args) throws IOException {
            service = new TestService();
            connector=new Connector();
            connector.setTimeout(20000);
            connector.registerService(service);
            connector.connectAsync("http://82.102.13.99:9981/CPMServices");
            connector.onSocketChangeListener(new GoSocketListener() {
                public void onSocketChange(GoSocketListener.SocketState lastState, GoSocketListener.SocketState currentState) {
                    if(lastState==SocketState.Disconnected && currentState==SocketState.Connected && a ){
                        for(int i=0;i<100;i++){
                            service.hello();
                            System.err.println(""+i);
                        }
                        a=false;
                    }
                }
                public void socketExeption(Exception e) {
                    e.printStackTrace();
                }
            });
        }
}