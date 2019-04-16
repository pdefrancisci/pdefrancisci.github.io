<!DOCTYPE html>

<head>
    <title>Sign-in</title>
    <link rel="stylesheet" type="text/css" href=${style}>
    <meta name="author" content="Josh Kraines">
</head>

<body>
<div class="page">
    <h1>Sign-in</h1>

    <div class="body">

        <div class="INFO">
        <p>
            ${message}
        </p>
        <form action="/signin" method="POST">
                <input name="username" />
                <br/><br/>
                <button type="submit">Ok</button>
        </form>
        </div>
    </div>
</div>
</body>

</html>