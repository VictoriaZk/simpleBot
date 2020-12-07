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
    </div>
</nav>
<br>
<div class="container">
    <table class="table table-hover table-light">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Выполнить</th>
        </tr>
        </thead>
        <tbody>
        <#list countries as country>
            <tr class="text-left">
                <td>${country.name}</td>
                <td>
                    <a class="badge badge-success" data-toggle="modal" data-target="#update${country.id}">Обновить</a>
                    <!-- The Modal -->
                    <form action="/countries/update/${country.id}" method="post">
                        <div class="modal fade" id="update${country.id}">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Обновление</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        <form>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Название</span>
                                                </div>
                                                <input type="text" name="name" value="${country.name}"
                                                       class="form-control">
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
                    <a href="/countries/delete/${country.id}" class="badge badge-danger">Удалить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a class="btn btn-default btn-lg btn-block" data-toggle="modal" data-target="#myModal">Добавить новую страну</a>
    <!-- The Modal -->
    <form action="/countries/add" method="post">
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
                                    <span class="input-group-text">Название</span>
                                </div>
                                <input type="text" name="name" class="form-control">
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
</div>
</body>
<style>
    .table td {

        text-align: center;
    }

    .table th {
        text-align: center;
    }
</style>
</html>
