<html>

<body>
    <h4>Dear ${firstName} ${lastName},</h4>
    <p>Sending Email using javax with <b>Velocity Template!!!</b></p>
    <p>Here are your products: <br/></p>

    <ul>
<#list products as product>
    <li> ${product.name} :  ${product.price} (<a href="${product.link}">Link</a>) </li>
</#list>
    </ul>

    <p><br/>Thanks<br/></p>
    <p>${signature}</p>
    <p>${location}</p>
</body>

</html>