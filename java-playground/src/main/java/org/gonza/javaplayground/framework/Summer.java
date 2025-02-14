package org.gonza.javaplayground.framework;

import org.gonza.javaplayground.framework.server.Server;

import java.io.*;

public class Summer {
    private final Application program;
    private final Server server;
    private Boolean isReady = true;

    public Summer(Server server, Application program) {
        this.server = server;
        this.program = program;
    }

    public void work() throws IOException {
        while (isReady) {
            server.execute(program);
        }
    }
}
