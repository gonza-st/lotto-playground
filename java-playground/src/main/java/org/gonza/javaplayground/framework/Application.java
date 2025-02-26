package org.gonza.javaplayground.framework;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public interface Application {
    void run(BufferedReader in, PrintWriter out) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
}
