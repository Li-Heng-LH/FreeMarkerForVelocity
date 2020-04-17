package me.liheng.codeGenerator;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class TrimmedSplitMethod implements TemplateMethodModelEx {

    @Override
    public Object exec(List list) throws TemplateModelException {
        String string = ((SimpleScalar) list.get(0)).getAsString();
        String delim = ((SimpleScalar) list.get(1)).getAsString();

        System.out.println(string);
        System.out.println(delim);

        if (string != null && delim !=null) {
            String[] rawTokens = string.split(delim);
            String[] trimmedTokens = new String[rawTokens.length];

            for (int i = 0; i < rawTokens.length; i++) {
                trimmedTokens[i] = rawTokens[i].trim();
            }

            return trimmedTokens;
        }

        return null;
    }
}
