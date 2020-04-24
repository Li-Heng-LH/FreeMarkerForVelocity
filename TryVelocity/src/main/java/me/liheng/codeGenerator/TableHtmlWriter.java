package me.liheng.codeGenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableHtmlWriter {

    private static final String TEMPLATE_TO_USE = "tableWithMacro.vm";

    public static void write() throws IOException {
        //Initialise the velocity context
        VelocityContext context = new VelocityContext();
        context.put("didPass", true);
        context.put("summary", readCSV());
        context.put("StringUtils", StringUtils.class);
        List<String> listOfLanguages = new ArrayList<>();
        listOfLanguages.add("Java");
        listOfLanguages.add("Python");
        listOfLanguages.add("C++");
        listOfLanguages.add("Ruby");
        context.put("listOfLanguages", listOfLanguages);

        Template template = Velocity.getTemplate(TEMPLATE_TO_USE);
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);

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
