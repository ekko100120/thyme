package com.thyme.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerListener implements ServletContextListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
    }

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        logger.info("=================================");
        logger.info("系统[{}]启动完成!!!", contextEvent.getServletContext().getServletContextName());
        logger.info("=================================");
    }
}