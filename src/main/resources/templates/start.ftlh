<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
>
<head>
    <title>admin panel</title>

    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/momentjs/2.24.0/moment.js"></script>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>


</head>
<body>
<div class="jumbotron text-center">
    <h1 style="color:Gray">Travelling</h1>
    <div class="container-fluid">
        <div class="d-flex justify-content-center mt-5">
            <a href="/countries" class="btn btn-outline-secondary m-2">Countries</a>
            <a href="/cities" class="btn-outline-secondary btn m-2">Cities</a>
            <a href="/hotels" class="btn-outline-secondary btn m-2">Hotels</a>
            <a href="/statistics" class="btn-outline-secondary btn m-2">Statistics</a>
        </div>
    </div>
</div>
</body>
<style>
    /* Make the image fully responsive */
    .carousel-inner img {
        width: 100%;
        height: 100%;
        margin-top: -140px;
        z-index: -1;
    }
</style>
</head>
<body>

<div id="demo" class="carousel slide" data-ride="carousel">

    <!-- Indicators -->
    <ul class="carousel-indicators">
        <li data-target="#demo" data-slide-to="0" class="active"></li>
        <li data-target="#demo" data-slide-to="1"></li>
    </ul>

    <!-- The slideshow -->
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://previews.123rf.com/images/kasto/kasto1506/kasto150600223/41402670-casually-dressed-young-stylish-female-traveller-checking-a-departures-board-at-the-airport-terminal-.jpg"
                 alt="Le" width="1100" height="500">
        </div>
        <div class="carousel-item">
            <img src="https://previews.123rf.com/images/kantver/kantver1907/kantver190700019/127283555-business-private-jet-airplane-parked-at-terminal-luxury-tourism-and-business-travel-transportation-c.jpg"
                 alt="Por" width="1100" height="500">
        </div>
        <div class="carousel-item">
            <img src="https://previews.123rf.com/images/mihtiander/mihtiander1501/mihtiander150100245/35423665-two-hikers-on-top-of-the-mountain-enjoying-sunrise-over-the-tropical-valley.jpg"
                 alt="P" width="1100" height="500">
        </div>
    </div>

    <!-- Left and right controls -->
    <a class="carousel-control-prev" href="#demo" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#demo" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>
</div>
</body>
</html>
