<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Page Title</title>
    <!-- Add your CSS styles if needed -->
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        header {
            background-color: #f1f1f1;
            padding: 10px;
            text-align: center;
        }

        #logo {
            float: left;
            width: 20%;
            padding: 10px;
        }

        #companyName {
            float: left;
            width: 60%;
            text-align: center;
            padding: 10px;
        }

        #userSection {
            float: right;
            width: 20%;
            padding: 10px;
            text-align: right;
        }

        #userLogo {
            width: 50px; /* Adjust as needed */
            height: 50px; /* Adjust as needed */
            margin-right: 10px;
        }
    </style>
</head>
<body>

    <header>
        <div id="logo">
            <img src="" alt="Left Logo">
        </div>

        <div id="companyName">
            <h1>Allison Transmission</h1>
        </div>

        <div id="userSection">
            <img id="userLogo" src="path/to/user_logo.png" alt="User Logo">
            <form action="LoginServlet" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required><br>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required><br>

                <input type="submit" value="Login">
            </form>
        </div>
    </header>

    <!-- Your page content goes here -->

</body>
</html>
