package me.liheng.codeGenerator;

import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.util.Properties;

public class SingletonVelocityWriter {

    public static void main(String[] args) throws IOException {
        initialiseVelocity2();
        UserClassWriter.write();
        ProductClassWriter.write();
    }


    private static void initialiseVelocity1() {
        Properties properties = new Properties();
        properties.setProperty("file.resource.loader.path","src/main/resources/vtemplates");

        //initialize the Velocity engine Singleton
        Velocity.init(properties);
    }

    private static void initialiseVelocity2() {
        Velocity.setProperty("file.resource.loader.path","src/main/resources/vtemplates");

        //initialize the Velocity engine Singleton
        Velocity.init();
    }
}
