package me.liheng.codeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerWriter {

    static String className = "User";
    static String packageName = "me.liheng.project";

    public static void main(String[] args) throws IOException, TemplateException {

        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDirectoryForTemplateLoading(new File("/Users/liheng/OneDrive/FreeMarkerForVelocity/TryFreeMarker/src/main/resources/ftemplates"));
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);



        /* ------------------------------------------------------------------------ */
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */
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

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("class.ftl");

        /* Merge data-model with template */
        Writer out = new StringWriter();
        temp.process(root, out);

        System.out.println(out.toString());
    }
}
