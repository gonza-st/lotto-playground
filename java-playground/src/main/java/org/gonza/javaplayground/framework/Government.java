package org.gonza.javaplayground.framework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Government {
    private static final Integer POOL_SIZE = 10;
    private static final Integer PORT = 12345;

    private final ServerSocket serverSocket;
    private final ExecutorService connectionPool;

    private final BenefitProgram program;
    private Boolean isReady = true;

    {
        try {
            this.serverSocket = new ServerSocket(PORT);
            this.connectionPool = Executors.newFixedThreadPool(POOL_SIZE);
        } catch (IOException e) {
            this.isReady = false;
            throw new RuntimeException(e);
        }
    }

    public Government(BenefitProgram program) {
        this.program = program;
    }

    public void work() throws IOException {
        while (isReady) {
            Socket clientSocket = serverSocket.accept();
            connectionPool.submit(() -> this.startProgram(clientSocket));
        }
    }

    private void startProgram(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            program.run(in, out);
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
