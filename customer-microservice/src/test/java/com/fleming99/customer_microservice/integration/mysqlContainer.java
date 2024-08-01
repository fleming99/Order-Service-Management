package com.fleming99.customer_microservice.integration;

import org.testcontainers.containers.MySQLContainer;

public abstract class mysqlContainer {

    static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer<>("mysql:latest");
        MY_SQL_CONTAINER.start();
    }
}
