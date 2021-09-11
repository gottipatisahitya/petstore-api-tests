package tests;

import org.junit.jupiter.api.*;
import configProviders.ConfigFileReader;


public class TestBase {
    public TestBase(){
        ConfigFileReader.ReadTestConfigurationProperties();
    }

    @BeforeAll
    public static void packageInitialiser() {

    }

    @BeforeAll
    public static void testInitialiser() {

    }


    @AfterEach
    public void packageTeardown() {

    }

    @AfterAll
    public static void testTeardown() {

    }
}
