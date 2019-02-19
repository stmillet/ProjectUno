package handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import requests.RegisterRequest;
import results.RegisterResult;
import services.RegisterService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static handlers.RootHandler.printHeaders;
import static java.net.HttpURLConnection.HTTP_OK;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("SERVER: register handler\n");

        // 1. get request headers
        System.out.println("request:");
        System.out.println("method: " + exchange.getRequestMethod());
        System.out.println("uri: " + exchange.getRequestURI() + '\n');
        printHeaders(exchange.getRequestHeaders());

        // 2. get request body
        System.out.println("request body:");
        Scanner in = new Scanner(exchange.getRequestBody());
        StringBuilder sb = new StringBuilder();
        buildRequest(in, sb);

        // 3. send response headers
        exchange.sendResponseHeaders(HTTP_OK,  0);

        // process request
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        RegisterRequest registerRequest = gson.fromJson(sb.toString(), RegisterRequest.class);
        RegisterResult registerResult = new RegisterService().register(registerRequest);

        // 4. send response body
        String response = gson.toJson(registerResult);
        PrintWriter out = new PrintWriter(exchange.getResponseBody());
        out.print(response);
        out.close();

        System.out.println("response body:");
        System.out.println(response);
        System.out.println();

        System.out.println("SERVER: register handler done");
        System.out.println();
    }

    static void buildRequest(Scanner in, StringBuilder sb) {
        while (in.hasNextLine()) {
            String next_line = in.nextLine();
            System.out.println(next_line);
            sb.append(next_line);
            sb.append('\n');
        }
    }
}