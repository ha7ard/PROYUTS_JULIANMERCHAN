package com.proyuts.util;

public class DatabaseConfig {
    private static final String URL = System.getenv().getOrDefault("PROYUTS_DB_URL", "jdbc:mysql://localhost:3306/proyuts_db?serverTimezone=UTC");
    private static final String USER = System.getenv().getOrDefault("PROYUTS_DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("PROYUTS_DB_PASSWORD", "");

    public static String getUrl() { return URL; }
    public static String getUser() { return USER; }
    public static String getPassword() { return PASSWORD; }
}
