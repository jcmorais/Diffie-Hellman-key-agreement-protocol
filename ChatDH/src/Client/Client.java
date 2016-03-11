package Client;

import Cipher.DiffieHellman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by carlosmorais on 01/03/16.
 */
public class Client {

    private static void log(String s){
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 4444;
        Socket socket = new Socket(host, port);
        DiffieHellman dh = new DiffieHellman();
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        dh.proceedDHagreement(new BufferedReader(new InputStreamReader(socket.getInputStream())), out);
        String line;

        while((line = in.readLine()) != null){
            line = dh.encrypt(line);
            out.println(line);
            out.flush();
        }

        socket.shutdownInput();
        socket.shutdownOutput();
        socket.close();

    }
}
