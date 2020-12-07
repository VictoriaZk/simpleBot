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
            <a class="navbar-brand" style = "padding-left: 30vw" href="https://www.booking.com/">
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/Eo_circle_blue_letter-b.svg/1200px-Eo_circle_blue_letter-b.svg.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Booking.com
            </a>

            <a class="navbar-brand" style = "padding-left: 3vw" href="https://www.airbnb.com/">
                <img src="https://image.freepik.com/free-vector/letter-logo-design-template_23987-86.jpg" width="30" height="30" class="d-inline-block align-top" alt="">
                Airbnb.com
            </a>
        </ul>
    </div>
</nav>
<br>
<div class="container">
    <table class="table table-hover table-light">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Количество звезд</th>
            <th scope="col">Цена за сутки</th>
            <th scope="col">Город</th>
            <th scope="col">Рейтинг</th>
            <th scope="col">Выполнить</th>
        </tr>
        </thead>
        <tbody>
        <#list hotels as hotel>
            <tr class="text-left">
                <td>${hotel.name}</td>
                <td>${hotel.amountOfStars}</td>
                <td>${hotel.cost}</td>
                <td>${hotel.city.name}</td>
                <td>${hotel.rating}</td>
                <td>
                    <a class="badge badge-success" data-toggle="modal" data-target="#update${hotel.id}">Обновить</a>
                    <!-- The Modal -->
                    <form action="/hotels/update/${hotel.id}" method="post">
                        <div class="modal fade" id="update${hotel.id}">
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
                                                <input type="text" name="name" value="${hotel.name}"
                                                       class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Количество звезд</span>
                                                </div>
                                                <input type="number" name="stars" min="1" max="5" value="${hotel.amountOfStars}"
                                                       class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Цена за сутки</span>
                                                </div>
                                                <input type="text" name="cost" value="${hotel.cost}"
                                                       class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Рейтинг</span>
                                                </div>
                                                <input type="number" min="1" name="rating" value="${hotel.rating}"
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
                    <a href="/hotels/delete/${hotel.id}" class="badge badge-danger">Удалить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a class="btn btn-default btn-lg btn-block" data-toggle="modal" data-target="#myModal">Добавить новый отель</a>
    <!-- The Modal -->
    <form action="/hotels/add" method="post">
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
                            <div class="input-group mb-3 input-group-sm">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Количество звезд</span>
                                </div>
                                <input type="number" name="stars" min="1" max="5" class="form-control">
                            </div>
                            <div class="input-group mb-3 input-group-sm">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Цена за сутки</span>
                                </div>
                                <input type="text" name="cost" class="form-control">
                            </div>
                            <div class="input-group mb-3 input-group-sm">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Город</span>
                                </div>
                                <input type="text" name="city" class="form-control">
                            </div>
                            <div class="input-group mb-3 input-group-sm">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Рейтинг</span>
                                </div>
                                <input type="number" name="rating" class="form-control">
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
