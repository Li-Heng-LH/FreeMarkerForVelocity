package me.liheng.codeGenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class VelocityWriter {

    static String className = "User";
    static String packageName = "me.liheng.project";

    public static void main(String[] args) {

        //Initialise the velocity engine
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        //Read the template
        Template template = velocityEngine.getTemplate("/src/main/resources/vtemplates/class.vm");

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
