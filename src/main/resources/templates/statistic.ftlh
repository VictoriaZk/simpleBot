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
            <a class="navbar-brand" style = "padding-left: 30vw" href="https://coronavirus-monitor.info/">
                <img src="https://www.flaticon.com/svg/static/icons/svg/2760/2760147.svg" width="30" height="30" class="d-inline-block align-top" alt="">
                Covid-19 - режим реального времени
            </a>
        </ul>
    </div>
</nav>
<br>
<div class="container">
    <table class="table table-hover table-light">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Количество зараженных</th>
            <th scope="col">Необходимость карантина по приезду</th>
            <th scope="col">Страна</th>
            <th scope="col">Выполнить</th>
        </tr>
        </thead>
        <tbody>
        <#list statistics as statistic>
            <tr class="text-left">
                <td>${statistic.amount} <p style="color:Red">(+${statistic.day})</p></td>
                <#if statistic.isQuarantineNeeded>
                    <td style="color: red; padding-left: 7vw">Карантин обязателен</td>
                <#else>
                    <td style="color: green; padding-left: 7vw">Карантин не нужен</td>
                </#if>
                <td>${statistic.country.name}</td>
                <td>
                    <a class="badge badge-success" data-toggle="modal" data-target="#update${statistic.id}">Обновить</a>
                    <!-- The Modal -->
                    <form action="/statistics/update/${statistic.id}" method="post">
                        <div class="modal fade" id="update${statistic.id}">
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
                                                    <span class="input-group-text">Количество зараженных за все время</span>
                                                </div>
                                                <input type="number" name="amount" min="0" value="${statistic.amount}"
                                                       class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Количество зараженных за сутки</span>
                                                </div>
                                                <input type="number" name="day" min="0" value="${statistic.day}"
                                                       class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Необходимость карантина</span>
                                                </div>
                                                <form>
                                                    <select name="isQuarantineNeeded" class="custom-select mb-1">
                                                        <option selected value="false">Нет</option>
                                                        <option value="true">Да</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </form>
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-outline-success p-2">Сохранить</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </form>
                    <a href="/statistics/delete/${statistic.id}" class="badge badge-danger">Удалить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a class="btn btn-default btn-lg btn-block" data-toggle="modal" data-target="#myModal">Добавить новую статистику</a>
    <!-- The Modal -->
    <form action="/statistics/add" method="post">
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
                                    <span class="input-group-text">Страна</span>
                                </div>
                                <input type="text" name="country" class="form-control">
                            </div>
                            <div class="input-group mb-3 input-group-sm">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Количество зараженных за все время</span>
                                </div>
                                <input type="number" name="amount" min="0" class="form-control">
                            </div>
                            <div class="input-group mb-3 input-group-sm">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Количество зараженных за сутки</span>
                                </div>
                                <input type="number" name="day" min="0" class="form-control">
                            </div>
                            <div class="input-group mb-3 input-group-sm">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Необходимость карантина</span>
                                </div>
                                <form>
                                    <select name="isQuarantineNeeded" class="custom-select mb-1">
                                        <option selected value="false">Нет</option>
                                        <option value="true">Да</option>
                                    </select>
                                </form>
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
</html>
