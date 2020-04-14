package me.liheng.codeGenerator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class UserClassWriter {

    static String className = "User";
    static String packageName = "me.liheng.project";

    public static void write() throws IOException {

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

        //Merge the template with context data
        StringWriter stringWriter = new StringWriter();
        Velocity.mergeTemplate("class.vm", context, stringWriter);

        //Write to file
        FileWriter fw = null;
        try {
            fw = new FileWriter("out/User.java");
            fw.write(stringWriter.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
