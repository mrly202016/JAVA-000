package homework;

import homework.server.Server;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/2
 * Time: 10:00
 */
public class ServerApplication {
    public static void main(String[] args) {
        String proxyServer = "http://localhost:8801";

        Server server = new Server(8888, proxyServer);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
