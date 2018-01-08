package ru.otus;

import org.apache.catalina.LifecycleException;

import javax.servlet.ServletException;

public class Main {
    public static void main(String[] args) throws ServletException, LifecycleException {
        Launcher launcher = new Launcher();
        launcher.launch();
    }
}
