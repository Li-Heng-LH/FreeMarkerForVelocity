package ${packageName};

public ${className} implements Serializable {

 /** Serial Version UID. */
 private static final long serialVersionUID = 1L;

<#list attributes as attribute>
 private ${attribute.attributeType} ${attribute.attributeName};
</#list>


<#list attributes as attribute>
 public ${attribute.attributeType} get${attribute.getGetterAndSetterString()}() {
     return this.${attribute.attributeName};
 }
 public void set${attribute.getGetterAndSetterString()}(${attribute.attributeType} ${attribute.attributeName}) {
     this.${attribute.attributeName} = ${attribute.attributeName};
 }
</#list>

}