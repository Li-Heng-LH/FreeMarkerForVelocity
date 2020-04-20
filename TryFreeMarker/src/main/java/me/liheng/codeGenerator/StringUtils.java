package me.liheng.codeGenerator;

public class StringUtils {

    public static String[] trimmedSplit(String string, String delim) throws Exception {
        if (string != null && delim !=null) {

            System.out.println(string);
            System.out.println(delim);

            String [] rawTokens = string.split(delim);
            String [] trimmedTokens = new String[rawTokens.length];

            for (int i = 0; i < rawTokens.length; i++) {
                trimmedTokens[i] = rawTokens[i].trim();
            }

            return trimmedTokens;

        } else {
            throw new Exception();
        }
    }

    public void methodA() {}

    public int getZero() {
        return 0;
    }
}
