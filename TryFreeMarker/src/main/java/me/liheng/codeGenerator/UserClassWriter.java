package me.liheng.codeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserClassWriter {
    private static String className = "User";
    private static String packageName = "me.liheng.project";

    public static void write() throws IOException, TemplateException {

        /* Create a data-model */
        Map root = new HashMap();
        root.put("packageName", packageName);
        root.put("className", className);
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("id", "int"));
        attributes.add(new Attribute("firstName", "String"));
        attributes.add(new Attribute("lastName", "String"));
        attributes.add(new Attribute("dob", "LocalDate"));
        root.put("attributes", attributes);


        /* Get the configuration singleton */
        Configuration cfg = ConfigurationSingleton.getInstance();

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("class.ftl");

        /* Merge data-model with template */
        StringWriter stringWriter = new StringWriter();
        temp.process(root, stringWriter);

        //Write to file
        FileWriter fw = null;
        try {
            fw = new FileWriter("output/User.java");
            fw.write(stringWriter.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
