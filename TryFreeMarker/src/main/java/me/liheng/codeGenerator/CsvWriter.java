package me.liheng.codeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

public class CsvWriter {

    public static void write() throws IOException, TemplateException {

        /* Create a data-model */
        Map root = new HashMap();
        List<List<String>> csv = new ArrayList<>();
        String [] row1 = {"a", "b", "c", "d", "e"};
        String [] row2 = {"1", "2", "3", "4", "5"};
        String [] row3 = {"f", "g", "h", "i", "j"};
        String [] row4 = {"6", "7", "8", "9", "0"};
        csv.add(Arrays.asList(row1));
        csv.add(Arrays.asList(row2));
        csv.add(Arrays.asList(row3));
        csv.add(Arrays.asList(row4));
        root.put("csv", csv);

        /* Get the configuration singleton */
        Configuration cfg = ConfigurationSingleton.getInstance();

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("csv.ftl");

        /* Merge data-model with template */
        StringWriter stringWriter = new StringWriter();
        temp.process(root, stringWriter);

        //Write to file
        FileWriter fw = null;
        try {
            fw = new FileWriter("output/b.csv");
            fw.write(stringWriter.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
