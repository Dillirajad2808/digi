<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Upload and Sign PDF</title>
</head>
<body>
    <form action="/Payment" method="post" enctype="multipart/form-data">
        
        <input type="text" name="pdfFile" accept=".pdf" required>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
