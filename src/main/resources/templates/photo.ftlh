<!DOCTYPE html>
<html lang="ru">
<head>
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/momentjs/2.24.0/moment.js"></script>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Галерея</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- fancyBox JS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.css" rel="stylesheet">
    <style>
        .thumb img {
            -webkit-filter: grayscale(0);
            filter: none;
            border-radius: 5px;
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 5px;
        }

        .thumb img:hover {
            -webkit-filter: grayscale(1);
            filter: grayscale(1);
        }

        .thumb {
            padding: 5px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <a class="navbar-brand" style="padding-left: 30vw" href="https://ru.depositphotos.com/">
                <img src="https://blog.depositphotos.com/wp-content/uploads/2020/09/depositphotos_blog_logo.svg"
                     width="300" height="30">
            </a>

            <!-- The Modal -->
            <form action="/photos/add" method="post">
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Добавление</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body">
                                <form>
                                    <div class="input-group mb-3 input-group-sm">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Город</span>
                                        </div>
                                        <input type="text" name="city" class="form-control">
                                    </div>
                                    <div class="input-group mb-3 input-group-sm">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Путь к картинке</span>
                                        </div>
                                        <input type="text" name="url" class="form-control">
                                    </div>
                                    <div class="input-group mb-3 input-group-sm">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Тип</span>
                                        </div>
                                        <select name="isVideo">
                                            <option value="true">Видео</option>
                                            <option value="false">Фото</option>
                                        </select>
                                    </div>
                                </form>
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button class="btn btn-outline-success p-2" type="submit">Сохранить</button>
                            </div>

                        </div>
                    </div>
                </div>
            </form>
        </ul>
    </div>
</nav>
<div class="container">
    <h1 class="h2 text-center">Фотографии городов</h1>
    <div class="row">
        <#list photos as photo>
            <#if photo.isVideo>
                <div class="col-md-3 col-sm-4 col-xs-6 thumb" style=" overflow: hidden; margin-top: 30px">
                    <iframe
                            style="height: 200px"
                            src="${photo.url}" frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen></iframe>
                </div>
            <#else>
                <div class="col-md-3 col-sm-4 col-xs-6 thumb" style="overflow: hidden">
                    <form action="/photos/delete/${photo.id}" method="get">
                        <button class="glyphicon glyphicon-remove"
                                type="submit"
                                style="
                                  border: none;
                                  background-color: white;
                                    color: red;
                              margin-right: 16px;
                              margin-top: 8px;
                              text-align: right;
                            "></button>
                    </form>
                    <a data-fancybox="gallery" href="${photo.url}" data-toggle="tooltip" title="${photo.city.name}">
                        <img style="height: 200px" class="img-responsive" src="${photo.url}" alt="${photo.city.name}">
                    </a>
                </div>
            </#if>
        </#list>
        <div class="col-md-3 col-sm-4 col-xs-6 thumb" style="height: 200px; display: flex;
            justify-content: center; /*Центрирование по горизонтали*/
            align-items: center;">
            <a class="glyphicon glyphicon-plus"
               data-toggle="modal"
               data-target="#myModal"
               aria-hidden="true"
               style="font-size: 7em">
            </a>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- fancyBox JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.js"></script>
</body>
</html>							
						