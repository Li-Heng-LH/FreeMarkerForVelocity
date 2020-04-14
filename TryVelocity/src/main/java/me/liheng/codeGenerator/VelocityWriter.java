package me.liheng.codeGenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VelocityWriter {

    static String className = "User";
    static String packageName = "me.liheng.project";

    public static void main(String[] args) {

        //Create the velocity engine instance
        VelocityEngine velocityEngine = new VelocityEngine();

        initialiseVelocityEngine2(velocityEngine);

        //Read the template USING INSTANCE method
        Template template = velocityEngine.getTemplate("class.vm");

        //Initialise the velocity context
        VelocityContext context = new VelocityContext();

        //Put the data model in context object
        context.put("packageName", packageName);
        context.put("className", className);
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("id", "int"));
        attributes.add(new Attribute("firstName", "String"));
        attributes.add(new Attribute("lastName", "String"));
        attributes.add(new Attribute("dob", "LocalDate"));
        context.put("attributes", attributes);

        //Merge the template with context data and render the view
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        System.out.println(writer.toString());
    }

    private static void initialiseVelocityEngine1(VelocityEngine velocityEngine) {
        Properties properties = new Properties();
        properties.setProperty("file.resource.loader.path","src/main/resources/vtemplates");

        //Initialise the velocity engine instance
        velocityEngine.init(properties);
    }

    private static void initialiseVelocityEngine2(VelocityEngine velocityEngine) {
        velocityEngine.setProperty("file.resource.loader.path","src/main/resources/vtemplates");
        velocityEngine.init();
    }

}
