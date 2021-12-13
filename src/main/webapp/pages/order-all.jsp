<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order Records</title>
</head>
<body>
<h1>List of all orders:</h1>
<table style="border: 1px solid black;">
    <thead>
    <tr>
        <th style="border: 1px solid black;"> Id </th>
        <th style="border: 1px solid black;"> Client </th>
        <th style="border: 1px solid black;"> Content </th>
        <th style="border: 1px solid black;"> Status </th>
        <th style="border: 1px solid black;"> Price </th>
        <th style="border: 1px solid black;"> Comment </th>
        <th style="border: 1px solid black;"> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orderList}">
        <tr style="border: 1px solid black;">
            <td style="border: 1px solid black;">${order.order_id}</td>
            <td style="border: 1px solid black;">${order.client_id}</td>
            <td style="border: 1px solid black;">${order.content}</td>
            <td style="border: 1px solid black;">${order.status}</td>
            <td style="border: 1px solid black;">${order.price}</td>
            <td style="border: 1px solid black;">${order.comment}</td>
            <td style="border: 1px solid black;"><a href="/orders?order_id=${order.order_id}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>