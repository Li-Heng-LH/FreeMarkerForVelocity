package me.liheng.codeGenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ProductClassWriter {

    static String className = "Product";
    static String packageName = "me.liheng.project";

    public static void write() throws IOException {

        //Initialise the velocity context
        VelocityContext context = new VelocityContext();

        //Put the data model in context object
        context.put("packageName", packageName);
        context.put("className", className);
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("id", "int"));
        attributes.add(new Attribute("name", "String"));
        attributes.add(new Attribute("price", "double"));
        context.put("attributes", attributes);

        //Read the template USING STATIC method
        Template template = Velocity.getTemplate("class.vm");

        //Merge the template with context data
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);

        //Write to file
        FileWriter fw = null;
        try {
            fw = new FileWriter("output/Product.java");
            fw.write(stringWriter.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
