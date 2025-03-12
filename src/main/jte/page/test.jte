<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
    <script src="https://unpkg.com/htmx.org@2.0.4"></script>

    <title>Document</title>
</head>
<body>

<button hx-get="/multi-update" hx-swap="innerHTML"
        hx-target="#main-container" hx-trigger="click">여러 개 업데이트</button>

<button hx-get="/multi-update2" hx-swap="none">여러 개 업데이트2</button>

<div>
    <div id="header" hx-swap-oob="true">기존 헤더</div>
</div>

<div id="content" hx-swap-oob="true">기존 콘텐츠</div>

<div id="main-container">
    <div id="content2">
        ????
    </div>
</div>

</body>
</html>
