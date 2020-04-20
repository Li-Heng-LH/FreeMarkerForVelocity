<html>

    <body style="padding: 10px; padding-left: 12px; margin: 0px; color: black; font-size:14px; font-family: Verdana, Sans Serif">

    <table style="width: 100%">
        <tr>
            <#if didPass>
                <th style= "background-color: #006600; color: #ffffff; height:60px; margin:6px; border: 1px solid black"> Status: Pass </th>
            <#else>
                <th style= "background-color: #B20000; color: #ffffff; height:60px; margin:6px; border: 1px solid black"> Status: Fail </th>
            </#if>
        </tr>

        <#assign NumberOfColumnsInReport = 6
        failedColNum = NumberOfColumnsInReport - 1
        releaseYearColNum = NumberOfColumnsInReport - 2
        modelColNum = NumberOfColumnsInReport - 3 >

        <tr><td>
            <#if summary?size == 0 >
                <th style="height:60px"> Summary cannot be retrieved</th>
            <#else>



                <#--  <#assign StringUtils = statics['me.liheng.codeGenerator.StringUtils'] > -->
                <#--  <#assign Math = statics['java.lang.Math'] > -->



                <#assign summaryHeader = summary?first >
                <#assign summaryHeaderTokens = StringUtils.trimmedSplit(summaryHeader, ",") >

                <#-- ******** To be removed ******** -->
                <#list summaryHeaderTokens as token>
                <h2> ${token} </h2>
                </#list>

                <h3> ${Math.random()}</h3>
                 <#-- ************************ -->

            </#if>

        </td></tr>
    </table>
    </body>
</html>