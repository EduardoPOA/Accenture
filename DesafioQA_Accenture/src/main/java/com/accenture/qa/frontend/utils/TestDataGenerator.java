package com.accenture.qa.frontend.utils;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class TestDataGenerator {
    private static final Random random = new Random();

    public static String generateFirstName() {
        String[] names = {"João", "Maria", "Pedro", "Ana", "Carlos", "Julia", "Miguel", "Laura"};
        return names[random.nextInt(names.length)];
    }

    public static String generateLastName() {
        String[] lastNames = {"Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Alves", "Lima"};
        return lastNames[random.nextInt(lastNames.length)];
    }

    public static String generateEmail() {
        String[] domains = {"gmail.com", "hotmail.com", "yahoo.com", "outlook.com"};
        String[] prefixes = {"teste_qa", "teste_quality", "test_user", "demo_account"};
        Random random = new Random();
        String prefix = prefixes[random.nextInt(prefixes.length)];
        String domain = domains[random.nextInt(domains.length)];
        return prefix + "@" + domain;
    }

    public static String generateMobile() {
        return "119" + (10000000 + random.nextInt(90000000));
    }

    public static String generateAddress() {
        return "Rua Test, " + (100 + random.nextInt(900)) + " - Slum";
    }

    public static String directoryFile(){
        String filePath = Paths.get(System.getProperty("user.dir"),
                "src", "test", "resources", "SportBike_TestUpload.png").toString();
        return filePath;
    }
}