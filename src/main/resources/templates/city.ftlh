<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
>
<head>

    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/momentjs/2.24.0/moment.js"></script>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
        </ul>
        <a class="nav-link" style = "padding-left: 35vw; color: lightgrey" data-toggle="modal" data-target="#plan">Пожелания пользователей</a>
        <div class="modal fade" id="plan">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header" >
                        <h4 class="modal-title" style = "padding-left: 10vw">Список городов</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="table-wrapper-scroll-y my-custom-scrollbar">
                        <table class="table table-hover table-info">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">Название</th>
                                <th scope="col">Частота запросов пользователей</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list plans as plan>
                            <tr class="text-left">
                                <td>${plan.name}</td>
                                <td>${plan.amountOfRequests}</td>
                            </tr>
                        </#list>
                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>
<br>
<div class="container">
    <table class="table table-hover table-light">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Описание</th>
            <th scope="col">Стоит посетить</th>
            <th scope="col">Негативные отзывы</th>
            <th scope="col">Страна</th>
            <th scope="col">Выполнить</th>
        </tr>
        </thead>
        <tbody>
        <#list cities as city>
            <tr class="text-left">
                <td>${city.name}</td>
                <td>${city.description}</td>
                <td>${city.recommendToVisit}</td>
                <td>${city.notRecommendToVisit}</td>
                <td>${city.country.name}</td>
                <td>
                    <a class="badge badge-success" data-toggle="modal" data-target="#reduct${city.id}">Обновить</a>
                    <!-- The Modal -->
                    <div class="modal fade" id="reduct${city.id}">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Обновление</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    <form action="/cities/update/${city.id}" method="post">
                                        <div class="input-group mb-3 input-group-sm">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Название</span>
                                            </div>
                                            <input type="text" name="name" value="${city.name}"
                                                   class="form-control">
                                        </div>
                                        <div class="input-group mb-3 input-group-sm">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Описание</span>
                                            </div>
                                            <input type="text" name="description" value="${city.description}"
                                                   class="form-control">
                                        </div>
                                        <div class="input-group mb-3 input-group-sm">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Стоит посетить</span>
                                            </div>
                                            <input type="text" name="recommendToVisit" value="${city.recommendToVisit}"
                                                   class="form-control">
                                        </div>
                                        <div class="input-group mb-3 input-group-sm">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Негативные отзывы</span>
                                            </div>
                                            <input type="text" name="notRecommendToVisit" value="${city.notRecommendToVisit}"
                                                   class="form-control">
                                        </div>
                                        <!-- Modal footer -->
                                        <div class="modal-footer">
                                            <button class="btn btn-outline-success p-2" type="submit">Сохранить</button>
                                        </div>
                                    </form>
                                </div>


                            </div>
                        </div>
                    </div>
                    <a href="/cities/delete/${city.id}" class="badge badge-danger">Удалить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a class="btn btn-default btn-lg btn-block" data-toggle="modal" data-target="#myModal">Добавить новый город</a>
    <!-- The Modal -->
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
                    <form action="/cities/add" method="post">
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Страна</span>
                            </div>
                            <input type="text" name="country" class="form-control">
                        </div>
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Название</span>
                            </div>
                            <input type="text" name="name" class="form-control">
                        </div>
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Описание</span>
                            </div>
                            <input type="text" name="description" class="form-control">
                        </div>
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Стоит посетить</span>
                            </div>
                            <input type="text" name="recommendToVisit" class="form-control">
                        </div>
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Негативные отзывы</span>
                            </div>
                            <input type="text" name="notRecommendToVisit" class="form-control">
                        </div>
                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button class="btn btn-outline-success p-2" type="submit">Сохранить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
