package org.gonza.javaplayground.framework;

import org.gonza.javaplayground.framework.server.Server;

import java.io.*;

public class Government {
    private final BenefitProgram program;
    private final Server server;
    private Boolean isReady = true;

    public Government(Server server, BenefitProgram program) {
        this.server = server;
        this.program = program;
    }

    public void work() throws IOException {
        while (isReady) {
            server.execute(program);
        }
    }
}
