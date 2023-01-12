<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>NMEA solver</title>
</head>
<body>
<div>
    <form:form method="post" action="/api/uploadFile" enctype="multipart/form-data">
        Choose file: <input type="file" name="file">

        <br><br>

        From:
        <input type="text" name="from">

        <br><br>

        To:
        <input type="text" name="to">

        <button>Ok!</button>
    </form:form>
</div>

</body>
</html>