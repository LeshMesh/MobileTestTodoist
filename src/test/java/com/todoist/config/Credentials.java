package com.todoist.config;

import org.aeonbits.owner.ConfigFactory;

public class Credentials {
    public static BrowserstackConfig configBrow = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
}
