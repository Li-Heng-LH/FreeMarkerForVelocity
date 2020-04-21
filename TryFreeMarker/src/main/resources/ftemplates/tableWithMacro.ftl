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
                <#assign summaryHeaderTokens = StringUtils.trimmedSplit(summaryHeader, ",") >

                <table style="border: 1px solid black; border-collapse: collapse; width: 100%; font-size:100%">
                    <thead>
                    <#-- Add Header Row -->
                    <tr style = "background-color: #A09FDA">

                        <#list summaryHeaderTokens as headerToken >
                            <th style="border: 1px solid black; padding:5px"> ${headerToken} </th>
                        </#list>

                    </tr>
                    </thead>

                    <tbody>
                    <#-- Add detail Rows -->
                    <#list summaryBody as line >

                        <#assign summaryLineTokens = StringUtils.trimmedSplit(line, ",") >

                        <#-- Determine Background color -->
                        <#if (summaryLineTokens?size > failedColNum && summaryLineTokens[failedColNum] == "failed") >
                            <#if summaryLineTokens[releaseYearColNum] == 'Long Ago' || summaryLineTokens[modelColNum] == 'Not Found' >
                                <tr style="background-color: #B20000; color: #ffffff">
                            <#else>
                                <tr style="background-color: #F3EED7; color: #000000">
                            </#if>
                        <#else>
                            <tr style="background-color: #ffffff; color: #000000">
                        </#if>

                        <#list summaryLineTokens as token >
                            <td style="border: 1px solid black; padding:5px"> ${token} </td>
                        </#list>
                            </tr>
                    </#list>
                </tbody>

              </table>

            </#if>

            </td></tr>
    </table>
    </body>

</html>