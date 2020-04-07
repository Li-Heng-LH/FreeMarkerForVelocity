package me.liheng.codeGenerator;

import org.apache.velocity.util.StringUtils;

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
        return StringUtils.capitalizeFirstLetter(attributeName);
    }
}
