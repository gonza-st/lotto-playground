package org.gonza.javaplayground.framework.server;

import org.gonza.javaplayground.framework.BenefitProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsoleServer implements Server {
    private final ExecutorService connectionPool;
    private final ServerSocket serverSocket;

    public ConsoleServer(ServerProperties properties) {
        try {
            this.connectionPool = Executors.newFixedThreadPool(properties.poolSize());
            this.serverSocket = new ServerSocket(properties.port());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(BenefitProgram program) throws IOException {
        Socket clientSocket = serverSocket.accept();
        connectionPool.submit(() -> handleRequest(clientSocket, program));
    }

    private void handleRequest(Socket clientSocket, BenefitProgram program) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            program.run(in, out);
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
