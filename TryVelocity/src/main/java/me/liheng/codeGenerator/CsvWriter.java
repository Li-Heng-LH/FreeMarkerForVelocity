package me.liheng.codeGenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvWriter {

    private static final String TEMPLATE_TO_USE = "csv.vm";

    public static void write() throws IOException {

        //Initialise the velocity context
        VelocityContext context = new VelocityContext();

        List<List<String>> csv = new ArrayList<>();
        String [] row1 = {"a", "b", "c", "d", "e"};
        String [] row2 = {"1", "2", "3", "4", "5"};
        String [] row3 = {"f", "g", "h", "i", "j"};
        String [] row4 = {"6", "7", "8", "9", "0"};
        csv.add(Arrays.asList(row1));
        csv.add(Arrays.asList(row2));
        csv.add(Arrays.asList(row3));
        csv.add(Arrays.asList(row4));
        context.put("csv", csv);

        Template template = Velocity.getTemplate(TEMPLATE_TO_USE);
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);

        //Write to file
        FileWriter fw = null;
        try {
            fw = new FileWriter("output/a.csv");
            fw.write(stringWriter.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
