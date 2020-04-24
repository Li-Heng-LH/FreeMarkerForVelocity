<#macro myMacro>
  <div style="color:green">
  <h2> This is from MY macro! </h2>
  <p> My Macro</p>
  </div>
</#macro>


<#macro tablerows color somelist>
 <#list somelist as something >
     <tr><td bgcolor=${color}>${something}</td></tr>
 </#list>
</#macro>