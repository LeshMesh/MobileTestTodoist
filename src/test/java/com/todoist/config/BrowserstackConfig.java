package com.todoist.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:browserstack.properties"})
public interface BrowserstackConfig extends Config {

    String username();
    String password();
    String app();
    String url();
}
