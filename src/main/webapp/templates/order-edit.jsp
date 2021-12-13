<html>
<head>
    <title>Editing Form</title>
</head>
<body>
<h1>Edit order</h1>
<form action="/orders" method="post">
    <table style="border: 1px solid black;">
        <thead>
        <tr>
            <th style="border: 1px solid black;"> Id </th>
            <th style="border: 1px solid black;"> Client </th>
            <th style="border: 1px solid black;"> Content </th>
            <th style="border: 1px solid black;"> Status </th>
            <th style="border: 1px solid black;"> Price </th>
            <th style="border: 1px solid black;"> Comment </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th style="border: 1px solid black;"><input name="order_id" value="${order.order_id}" readonly></th>
            <th style="border: 1px solid black;"><input name="client_id" value="${order.client_id}" readonly></th>
            <th style="border: 1px solid black;"><input name="content" value="${order.content}" readonly></th>
            <th style="border: 1px solid black;">
                <select name="status">${order.status}"
                    <option value="CREATED">CREATED</option>
                    <option value="ACCEPTED">ACCEPTED</option>
                    <option value="DENIED">DENIED</option>
                    <option value="=CLOSED">CLOSED</option>
                </select></th>
            <th style="border: 1px solid black;"><input name="price" value="${order.price}"></th>
            <th style="border: 1px solid black;"><input name="_comment" value="${order.comment}"></th>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="Save" />
</form>
</body>
</html>