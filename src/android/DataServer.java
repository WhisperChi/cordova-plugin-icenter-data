package icenterdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.BreakIterator;

import io.cordova.hellocordova.MainActivity;

public class DataServer {
    private static DataServer dataServer = null;
    private HttpServerThread server = null;

    private DataServer() {
        int testPort = 9001;
        server = new HttpServerThread(testPort);
    }

    public static DataServer getInstance() {
        if (dataServer == null) {
            dataServer = new DataServer();
        }

        return dataServer;
    }

    public void start() {
        server.run();
    }

    public void stop() {
        server.interrupt();
    }

    private class HttpServerThread extends Thread {

        private int HttpServerPORT = 8888;

        HttpServerThread(int ServerPort) {
            HttpServerPORT = ServerPort;
        }

        @Override
        public void run() {
            Socket socket = null;

            try {
                ServerSocket httpServerSocket = new ServerSocket(HttpServerPORT);

                while (true) {
                    socket = httpServerSocket.accept();

                    HttpResponseThread httpResponseThread =
                            new HttpResponseThread(
                                    socket);
                    httpResponseThread.start();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    private class HttpResponseThread extends Thread {

        Socket socket;
        String h1;

        HttpResponseThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader is;
            PrintWriter os;
            String request;

            try {
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                request = is.readLine();

                // request /z/x/y.<suffix>
                System.out.println("request is " + request);

                os = new PrintWriter(socket.getOutputStream(), true);

                String response =
                        "<html><head></head>" +
                                "<body>" +
                                "<h1>" + "Hello from dataServer." + "</h1>" +
                                "</body></html>";

                os.print("HTTP/1.0 200" + "\r\n");
                os.print("Content type: text/html" + "\r\n");
                os.print("Content length: " + response.length() + "\r\n");
                os.print("\r\n");
                os.print(response + "\r\n");
                os.flush();
                socket.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return;
        }


    }
}


