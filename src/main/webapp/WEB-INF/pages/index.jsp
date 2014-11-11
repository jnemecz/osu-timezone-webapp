<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/KU4/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Časové zóny (klient)</a>
        </div>
    </div>
</nav>

<div class="container">

    <div class="starter-template" style="padding-top: 50px">

        <h2>Přidat časovou zónu</h2>

        <form class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="tzInput">Časová zóna</label>
                <input type="text" class="form-control" id="tzInput" placeholder="Vložte časovou zónu">
            </div>
            <button type="submit" id="addBtn" class="btn btn-primary">Přidat</button>
        </form>

        <h4>Náhodně vybrané</h4>

        <p id="random"></p>

        <h2>Zavedené časové zóny
            <button type="button" id="clearBtn" style="display: none" class="btn btn-primary btn-sm">Vymazat</button>
        </h2>
        <p>Referenční časová zóna: ${tz.displayName} (${tz.ID}) </p>
        <table class="table table-hover" id="existing"></table>

    </div>

</div>

<script src="/KU4/resources/js/jquery-1.11.1.min.js"></script>
<script src="/KU4/resources/js/bootstrap.min.js"></script>


<script>

    $(document).ready(function () {

        $("#addBtn").click(
                function (e) {
                    e.preventDefault();
                    $.get("/KU4/add?tz=" + $("#tzInput").val(), function () {
                        getActive();
                        $("#tzInput").val("");
                    }).fail(function () {
                        alert("Akce se nezdařila (časová zóna pravděpodobně neexistuje).")
                    });

                }
        );

        function getActive() {

            console.log("zavolano getActive()");

            $.getJSON("/KU4/getActive", function (data) {

                var items = [];
                $.each(data, function (key, val) {
                    items.push("<tr><td>" + val.id + "</td><td>" + val.displayName + "</td><td>" + val.actualTime + "</td></tr> ");
                });
                $("#existing").html(items.join(""));

                // cistici button
                if (items.length > 0) {
                    $("#clearBtn").show();
                    $("#clearBtn ").click(
                            function (e) {
                                e.preventDefault();
                                $.get("/KU4/clear", function () {
                                    getActive();
                                });
                            }
                    );

                } else {
                    $("#clearBtn").hide();
                }

            });

        }

        function getRandom() {

            $.getJSON("/KU4/getRandom", function (data) {

                var items = [];
                $.each(data, function (key, val) {
                    items.push("<a href='/KU4/add?tz=" + val.id + "'>" + val.id + "</a>");
                });

                $("#random").html("<p>" + items.join(", ") + "</p>");

                $("#random a").click(
                        function (e) {
                            e.preventDefault();
                            $.get($(this).attr("href"), function () {
                                getActive();
                            });
                        }
                );

                getActive();

            });

        }

        getActive();
        getRandom();

    });


</script>


</body>
</html>