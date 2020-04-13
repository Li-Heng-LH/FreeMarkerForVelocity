package me.liheng.codeGenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SingletonVelocityWriter {

    static String className = "User";
    static String packageName = "me.liheng.project";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("file.resource.loader.path","src/main/resources/vtemplates");

        //initialize the Velocity engine Singleton
        Velocity.init(properties);

        //Read the template USING STATIC method
        Template template = Velocity.getTemplate("class.vm");

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
}
