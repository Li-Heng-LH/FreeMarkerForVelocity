package me.liheng.codeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class StringWriterTest {

    public static void run() throws IOException, TemplateException {
        /* Create a data-model */
        Map root = new HashMap();
        root.put("text", "Simple text");

        /* Get the configuration singleton */
        Configuration cfg = ConfigurationSingleton.getInstance();

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("simple.ftl");

        /* Merge data-model with template */
        StringWriter stringWriter = new StringWriter();
        temp.process(root, stringWriter);

        System.out.println(stringWriter.toString());

        System.out.println(stringWriter.toString());

        System.out.println(stringWriter.toString());
    }
}
