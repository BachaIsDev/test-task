<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=cp1251" />
    <title>NMEA solver</title>
</head>
<body>
<div>
    <form:form method="post" action="/api/uploadFile" enctype="multipart/form-data">
        Выберите файл: <input type="file" name="file">

        <br><br>

        Укажите начальное время:
        <input type="text" name="from">

        <br><br>

        Укажите конечное время:
        <input type="text" name="to">

        <button>Ok!</button>
    </form:form>
</div>

</body>
</html>