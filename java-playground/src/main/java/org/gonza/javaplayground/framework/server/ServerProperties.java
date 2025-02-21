package org.gonza.javaplayground.framework.server;

public record ServerProperties(
        Integer port,
        Integer poolSize
){
}

