<html>

    <body style="padding: 10px; padding-left: 12px; margin: 0px; color: black; font-size:14px; font-family: Verdana, Sans Serif">

    <#call global>()
    <#call myMacro>()


    <table style="width: 100%">
        <tr>
            <#if didPass>                <th style= "background-color: #006600; color: <#call ffffff>; height:60px; margin:6px; border: 1px solid black"> Status: Pass </th>
            <#else>                <th style= "background-color: <#call B20000>; color: <#call ffffff>; height:60px; margin:6px; border: 1px solid black"> Status: Fail </th>
            </#if>        </tr>

        <#assign NumberOfColumnsInReport = 6><#assign failedColNum = NumberOfColumnsInReport-1><#assign releaseYearColNum = NumberOfColumnsInReport-2><#assign modelColNum = NumberOfColumnsInReport-3>
        <tr><td>
            <#if summary.size() == 0>                <th style="height:60px"> Summary cannot be retrieved</th>
            <#else><#assign summaryHeader = summary.remove(0)><#assign summaryHeaderTokens = StringUtils.trimmedSplit(summaryHeader, ",")>
                <table style="border: 1px solid black; border-collapse: collapse; width: 100%; font-size:100%">
                    <thead>
                    <#-- Add Header Row
-->                    <tr style = "background-color: <#call A09FDA>">
                        <#foreach headerToken in summaryHeaderTokens>                            <th style="border: 1px solid black; padding:5px"> ${headerToken} </th>
                        </#foreach>                    </tr>
                    </thead>

                    <tbody>
                    <#-- Add detail Rows
-->                    <#foreach line in summary><#assign summaryLineTokens = StringUtils.trimmedSplit(line, ",")>
                        <#-- Determine Background color
-->                        <#if (summaryLineTokens.size()>failedColNum) && summaryLineTokens.get(failedColNum) == "failed">                            <#if summaryLineTokens.get(releaseYearColNum) == "Long Ago" || summaryLineTokens.get(modelColNum) == "Not Found">                                <tr style="background-color: <#call B20000>; color: <#call ffffff>">
                            <#else>                                <tr style="background-color: <#call F3EED7>; color: #000000">
                            </#if>                        <#else>                            <tr style="background-color: <#call ffffff>; color: #000000">
                        </#if>
                        <#foreach token in summaryLineTokens>                            <td style="border: 1px solid black; padding:5px"> ${token} </td>
                        </#foreach>                            </tr>
                    </#foreach>                </tbody>

              </table>

            </#if>
            </td></tr>
    </table>

    <#assign greatlakes = ["Superior", "Michigan", "Huron", "Erie", "Ontario"]><#assign color = "blue">    <table>
        <#call tablerows>( ${color} ${greatlakes} )
    </table>

    </body>

</html>