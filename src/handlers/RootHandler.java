package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.net.HttpURLConnection.HTTP_OK;

public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI rootURI = exchange.getRequestURI();

        System.out.printf("SERVER: root handler%n%n");

        //get request headers
        System.out.println("request:");
        System.out.println("method:" + exchange.getRequestMethod());
        System.out.println("uri:" + rootURI);
        System.out.println();
        printHeaders(exchange.getRequestHeaders());

        //get request body
        String[] rootArguments = rootURI.toString().split("/", 2);
        String requestPath;
        Headers rootHeaders = exchange.getResponseHeaders();

        //check to see if URI has a longer path
        if (rootArguments.length <= 1 || rootArguments[1].equals("")) {
            rootHeaders.set("Content-Type", "text/html");
            requestPath = "index.html";
        } else {
            requestPath = rootArguments[1];
            String rootURIstrSplit = rootURI.toString().split("/")[1];
            switch (rootURIstrSplit) {
                case "css":
                    rootHeaders.set("Content-Type", "text/css");
                    break;
                case "img":
                    rootHeaders.set("Content-Type", "image/png");
                    break;
                default:
                    rootHeaders.set("Content-Type", "text/html");
                    break;
            }
        }

        //send response headers
        exchange.sendResponseHeaders(HTTP_OK, 0);

        //send response body
        String responseFile = "web/" + File.separator + requestPath;

        //check if file exists
        Scanner fileOutScanner;
        try {
            fileOutScanner = new Scanner(new FileReader(responseFile));
        } catch (IOException e) {
            String fileNotFound = "web/HTML/404.html";
            fileOutScanner = new Scanner(new FileReader(fileNotFound));
        }
        OutputStreamWriter out = new OutputStreamWriter(exchange.getResponseBody());

        StringBuilder fileOutSB = new StringBuilder();
        while (fileOutScanner.hasNextLine()) {
            fileOutSB.append(fileOutScanner.nextLine());
            fileOutSB.append('\n');
        }
        fileOutScanner.close();
        out.write(fileOutSB.toString());
        out.close();

        System.out.println("SERVER: root handler done");
        System.out.println();
    }


    static void printHeaders(Map<String, List<String>> headers) {

        System.out.println("request headers:");

        for (String name : headers.keySet())
            System.out.println(name + " = " + headers.get(name));

        System.out.println();

    }
}
