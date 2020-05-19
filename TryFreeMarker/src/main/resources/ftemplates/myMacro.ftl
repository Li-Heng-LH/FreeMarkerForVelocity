<#macro myMacro>
  <div style="color:green">
  <h2> This is from MY macro! </h2>
  <p> My Macro</p>
  </div>
</#macro>


<#macro tablerows color somelist>
<table>
 <#list somelist as something >
     <tr><td bgcolor=${color}>${something}</td></tr>
 </#list>
</table>
</#macro>

<#macro greet person>
  <font size="+2">Hello ${person}!</font>
</#macro>