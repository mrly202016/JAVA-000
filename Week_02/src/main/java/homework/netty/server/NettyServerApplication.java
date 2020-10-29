package homework.netty.server;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,8801);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
