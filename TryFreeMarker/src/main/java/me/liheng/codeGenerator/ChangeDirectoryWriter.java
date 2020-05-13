package me.liheng.codeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ChangeDirectoryWriter {

    private static final String GREEN_DIRECTORY = "src/main/resources/templatesDir2";
    private static final String BLUE_DIRECTORY = "src/main/resources/templatesDir3";
    private static final String GREEN_TEMPLATE_TO_USE = "greenHeading.ftl";
    private static final String BLUE_TEMPLATE_TO_USE = "blueHeading.ftl";

    public static void writeGreen() throws IOException, TemplateException {

        /* Create a data-model */
        Map root = new HashMap();
        root.put("greenText", "This is a GREEN heading");
        root.put("blueText", "This is a BLUE heading");

        /* Get the configuration singleton */
        Configuration cfg = ConfigurationSingleton.getInstance();
        cfg.setDirectoryForTemplateLoading(new File(GREEN_DIRECTORY));

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate(GREEN_TEMPLATE_TO_USE);

        /* Merge data-model with template */
        StringWriter stringWriter = new StringWriter();
        temp.process(root, stringWriter);

        //Write to file
        FileWriter fw = null;
        try {
            fw = new FileWriter("output/green.html");
            fw.write(stringWriter.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public static void writeBlue() throws IOException, TemplateException {

        /* Create a data-model */
        Map root = new HashMap();
        root.put("greenText", "This is a GREEN heading");
        root.put("blueText", "This is a BLUE heading");

        /* Get the configuration singleton */
        Configuration cfg = ConfigurationSingleton.getInstance();
        cfg.setDirectoryForTemplateLoading(new File(BLUE_DIRECTORY));

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate(BLUE_TEMPLATE_TO_USE);

        /* Merge data-model with template */
        StringWriter stringWriter = new StringWriter();
        temp.process(root, stringWriter);

        //Write to file
        FileWriter fw = null;
        try {
            fw = new FileWriter("output/blue.html");
            fw.write(stringWriter.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
