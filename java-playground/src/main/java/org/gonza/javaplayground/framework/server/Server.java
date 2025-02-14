package org.gonza.javaplayground.framework.server;

import org.gonza.javaplayground.framework.Application;

import java.io.IOException;

public interface Server {
    void execute(Application program) throws IOException;
}
