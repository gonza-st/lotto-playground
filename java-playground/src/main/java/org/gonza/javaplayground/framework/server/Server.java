package org.gonza.javaplayground.framework.server;

import org.gonza.javaplayground.framework.BenefitProgram;

import java.io.IOException;

public interface Server {
    void execute(BenefitProgram program) throws IOException;
}
