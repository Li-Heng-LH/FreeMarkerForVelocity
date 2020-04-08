package me.liheng.codeGenerator;

public class Attribute {

    private String attributeName;
    private String attributeType;

    public Attribute(String attributeName, String attributeType) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getGetterAndSetterString() {
        return capitaliseFirstLetter(attributeName);
    }

    private String capitaliseFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

