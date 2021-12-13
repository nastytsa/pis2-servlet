<html>
<head>
    <title>Adding Form</title>
</head>
<body>
<h1>Add a new item</h1>
<form action="/orders" method="post">
    <table style="width: 25%">
        <tr>
            <td>Content of request</td>
            <td><input type="text" name="content" /></td>
        </tr>
    </table>
    <input type="submit" value="Create order" />
</form>
</body>
</html>