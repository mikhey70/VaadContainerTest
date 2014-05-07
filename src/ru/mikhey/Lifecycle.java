package ru.mikhey;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Lifecycle implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Init servlet. Count: "+Singleton.getInstance().getCount());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Destroy servlet");

    }
}