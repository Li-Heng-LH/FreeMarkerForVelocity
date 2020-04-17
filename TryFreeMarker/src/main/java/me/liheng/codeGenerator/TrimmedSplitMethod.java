package me.liheng.codeGenerator;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

import java.util.List;

public class TrimmedSplitMethod implements TemplateMethodModel {

    @Override
    public Object exec(List list) throws TemplateModelException {
        String string = (String) list.get(0);
        String delim = (String) list.get(1);

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
