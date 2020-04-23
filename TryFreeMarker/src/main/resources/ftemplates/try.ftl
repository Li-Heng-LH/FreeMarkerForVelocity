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
            <#if summary?api.size() == 0 >
                <th style="height:60px"> Summary cannot be retrieved</th>
            <#else>

                <#assign summaryHeader = summary?api.remove(0) >
                <#assign summaryHeaderTokens = StringUtils.trimmedSplit(summaryHeader, ",") >


                <#-- ******** To be removed ******** -->

                <ul>
                <#list summaryHeaderTokens as token>
                <li> ${token} </li>
                </#list>
                </ul>

                <h3> ${Math.random()}</h3>
                 <#-- ************************ -->


                <ul>
                    <#list summary as row>
                    <li> ${row} </li>
                    </#list>
                </ul>


            </#if>

        </td></tr>
    </table>
    </body>
</html>