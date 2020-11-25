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
            <a class="nav-link" href="https://coronavirus-monitor.info/">Statistic of Covid-19 in real time</a>
            </li>
        </ul>
    </div>
</nav>
<br>
<div class="container">
    <table class="table table-hover table-light">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Amount of infected</th>
            <th scope="col">Need quarantine</th>
            <th scope="col">Country</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <#list statistics as statistic>
            <tr class="text-left">
                <td>${statistic.amount}</td>
                <td>${statistic.isQuarantineNeeded?c}</td>
                <td>${statistic.country.name}</td>
                <td>
                    <a class="badge badge-info" data-toggle="modal" data-target="#myModal">Add</a>
                    <!-- The Modal -->
                    <form action="/statistics/add" method="post">
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
                                                    <span class="input-group-text">Country</span>
                                                </div>
                                                <input type="text" name="country" class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Amount</span>
                                                </div>
                                                <input type="number" name="amount" class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Quarantine</span>
                                                </div>
                                                <form>
                                                    <select name="isQuarantineNeeded" class="custom-select mb-1">
                                                        <option selected value="false">No</option>
                                                        <option value="true">Yes</option>
                                                    </select>
                                                </form>
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
                    <a class="badge badge-success" data-toggle="modal" data-target="#update${statistic.id}">Update</a>
                    <!-- The Modal -->
                    <form action="/statistics/update/${statistic.id}" method="post">
                        <div class="modal fade" id="update${statistic.id}">
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
                                                    <span class="input-group-text">Amount</span>
                                                </div>
                                                <input type="number" name="amount" value="${statistic.amount}"
                                                       class="form-control">
                                            </div>
                                            <div class="input-group mb-3 input-group-sm">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Quarantine</span>
                                                </div>
                                                <form>
                                                    <select name="isQuarantineNeeded" class="custom-select mb-1">
                                                        <option selected value="false">No</option>
                                                        <option value="true">Yes</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </form>
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-outline-success p-2">Update</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </form>
                    <a href="/statistics/delete/${statistic.id}" class="badge badge-danger">Delete</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>
