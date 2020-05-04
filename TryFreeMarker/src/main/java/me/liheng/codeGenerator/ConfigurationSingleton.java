package me.liheng.codeGenerator;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class ConfigurationSingleton {
    private static Configuration instance;

    // private constructor restricted to this class itself
    private ConfigurationSingleton() {}

    public static Configuration getInstance() throws IOException {

        if(instance == null) {

            instance = new Configuration(Configuration.VERSION_2_3_30);
            instance.setDirectoryForTemplateLoading(new File("src/main/resources/ftemplates"));
            // Recommended settings for new projects:
            instance.setDefaultEncoding("UTF-8");
            instance.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            instance.setLogTemplateExceptions(false);
            instance.setWrapUncheckedExceptions(true);
            instance.setFallbackOnNullLoopVariable(false);
            instance.setAPIBuiltinEnabled(true); //set to true to use: value?api.someJavaMethod()
        }

        return instance;
    }
}
