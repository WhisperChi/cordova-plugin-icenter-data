package icenterdata;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class DataServer {
    private static DataServer dataServer = null;
    private HttpServerThread server = null;
    private Context context = null;

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

    public void setContext(Context context) {
        this.context = context;
    }

    public void start() {
        server.run();
    }

    public void stop() {
        server.interrupt();
        try {
            server.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        private final String path = "/sdcard/data/test";

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

                // request /<dir>/z/x/y.<suffix>
                System.out.println("request is " + request);

//                String dir = request.toString().split(".")[0];
//                String suffix = request.toString().split(".")[1]; // TODO

                // TODO
                // handle different data with different dir and suffix

                // Test Part Start
                File path = Environment.getExternalStorageDirectory();
                String db_path = path.getAbsolutePath() + File.separator + "data/test/Global_ImageJianghua/5/tiles_0_0.sqlite";
                DataReader dReader = new DataReader(context, db_path);
                String res = dReader.getTileData(0, 21);
                System.out.printf("%s", res);

                // Test Part End

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


