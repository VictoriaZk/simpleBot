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
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="https://www.booking.com/">Go to the most popular apartments cite Booking.com</a>
            </li>
        </ul>
    </div>
</nav>
<br>
<div class="container">
    <table class="table table-hover table-light">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Stars</th>
            <th scope="col">City</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <#list hotels as hotel>
            <tr class="text-left">
                <td>${hotel.name}</td>
                <td>${hotel.amountOfStars}</td>
                <td>${hotel.city.name}</td>
                <td>
                    <a class="badge badge-info" data-toggle="modal" data-target="#myModal">Add</a>
                    <!-- The Modal -->
                    <form action="/hotels/add" method="post">
                        <div class="modal fade" id="myModal">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Adding</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        <form>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Name</span>
                                                </div>
                                                <input type="text" name="name" class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Stars</span>
                                                </div>
                                                <input type="number" name="stars" class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">City</span>
                                                </div>
                                                <input type="text" name="city" class="form-control">
                                            </div>
                                        </form>
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button class="btn btn-outline-success p-2" type="submit">Save</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </form>
                    <a class="badge badge-success" data-toggle="modal" data-target="#update${hotel.id}">Update</a>
                    <!-- The Modal -->
                    <form action="/hotels/update/${hotel.id}" method="post">
                        <div class="modal fade" id="update${hotel.id}">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Updating</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        <form>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Name</span>
                                                </div>
                                                <input type="text" name="name" value="${hotel.name}"
                                                       class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Stars</span>
                                                </div>
                                                <input type="number" name="stars" value="${hotel.amountOfStars}"
                                                       class="form-control">
                                            </div>
                                        </form>
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button class="btn btn-outline-success p-2" type="submit">Update</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </form>
                    <a href="/hotels/delete/${hotel.id}" class="badge badge-danger">Delete</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>
