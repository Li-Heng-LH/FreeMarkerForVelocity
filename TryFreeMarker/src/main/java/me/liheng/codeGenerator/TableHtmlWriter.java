package me.liheng.codeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableHtmlWriter {

    public static void write() throws IOException, TemplateException {
        /* Create a data-model */
        Map root = new HashMap();
        root.put("didPass", true);
        root.put("summary", readCSV());
        root.put("StringUtils", StringUtils.class);
        root.put("trimmedSplit", new TrimmedSplitMethod());

        /* Get the configuration singleton */
        Configuration cfg = ConfigurationSingleton.getInstance();

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("try.ftl");

        /* Merge data-model with template */
        StringWriter stringWriter = new StringWriter();
        temp.process(root, stringWriter);

        //Write to file
        FileWriter fw = null;
        try {
            fw = new FileWriter("out/table.html");
            fw.write(stringWriter.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    private static List<String> readCSV() throws IOException {
        List<String> buffer = new ArrayList<>();
        BufferedReader reader  = null;
        try {
            reader  = new BufferedReader(new FileReader("src/main/resources/report.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.add(line);
            }
        } finally {
            if (reader != null)
                reader.close();
        }
        return buffer;
    }
}
