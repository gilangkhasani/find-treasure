<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Treasure Hunt Spring</title>

        <c:url var="home" value="/" scope="request" />

        <spring:url value="/resources/core/css/bootstrap.min.css"
            var="bootstrapCss" />
        <link href="${bootstrapCss}" rel="stylesheet" />

        <spring:url value="/resources/core/js/jquery.1.10.2.min.js"
            var="jqueryJs" />
        <script src="${jqueryJs}"></script>

        <spring:url value="/resources/core/js/bootstrap.min.js"
                    var="bootstrapJs" />
         <script src="${bootstrapJs}"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Treasure Hunt Spring</a>
                </div>
            </div>
        </nav>

        <div class="container" style="min-height: 500px">

            <div class="starter-template">
                <h3 style="text-align:center;">Setup Grid</h3>

                <form class="form-horizontal" id="search-form">
                    <div class="form-group form-group-lg">
                        <label class="col-sm-2 control-label">Grid Size</label>
                        <div class="col-sm-10">
                            <input type=text class="form-control" id="gridSize">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col text-center">
                            <button type="submit" id="bth-search"
                                class="btn btn-primary btn-lg">Start Game</button>
                        </div>
                    </div>
                </form>
                </br>
                <div id="feedback"></div>
            </div>

        </div>

        <div class="container">
            <footer>
                <p>
                    &copy; Simple Treasure Hunt Game , Gilang Khasani 2021 <a href="#" data-toggle="modal" data-target="#exampleModalLong">Readme for Playing the Game</a>
                </p>
            </footer>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">How To Play this game?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <ul>
                    <li>Set Grid Size then press Start Game (ex:5 equal (5x5))</li>
                    <li>There is 4 Command Button (Up, Down, Left, Right)</li>
                    <li>The Player will start in top left of the grid.</li>
                    <li>Click Command Button to move the Player until the Treasure found or eaten by the Monster.</li>
                </ul>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>

        <script>
            jQuery(document).ready(function($) {
                $("#search-form").submit(function(event) {
                    // Disble the search button
                    // enableSearchButton(false);
                    // Prevent the form from submitting via the browser.
                    event.preventDefault();
                    showGridGame();
                });

            });

            function initGame() {
                var dataRequest = {}
                dataRequest["gridSize"] = $("#gridSize").val();
                $.ajax({
                    type : "POST",
                    contentType : "application/json",
                    url : "${home}search/api/initGame",
                    data : JSON.stringify(dataRequest),
                    dataType : 'json',
                    timeout : 100000,
                    success : function(data) {
                        $('#table-game').find('td').eq(0).text('Player');
                        enableCommandButton(false);
                        console.log("SUCCESS: ", data);
                    },
                    error : function(e) {
                        console.log("ERROR: ", e);
                    },
                    done : function(e) {
                        console.log("DONE");
                    }
                });
            }

            function showGridGame(){
                var gridSize = $("#gridSize").val();
                if( gridSize && gridSize > 1 ) {
                    var theTable = "";
                    theTable += "<center>";
                        theTable += "<button type='button' class='btn btn-primary btn-command' onclick='setCommand(this)' id='w'>Up</button>";
                        theTable += "<button type='button' class='btn btn-danger btn-command' onclick='setCommand(this)' id='s'>Down</button>";
                        theTable += "<button type='button' class='btn btn-success btn-command' onclick='setCommand(this)' id='a'>Left</button>";
                        theTable += "<button type='button' class='btn btn-info btn-command' onclick='setCommand(this)' id='d'>Right</button>";
                    theTable += "</center></br></br>";

                    theTable += "<table id='table-game' class='table table-bordered'>";
                    for(var i = 0; i < parseInt(gridSize); i++){
                        theTable += '<tr>';
                        for(var j = 0; j < parseInt(gridSize); j++){
                            theTable += '<td>***</td>';
                        }
                        theTable += '</tr>';
                    }
                    theTable += '</table>';
                    $('#feedback').html(theTable);

                    initGame();
                } else {
                    alert("Your Grid Size is Empty or only 1");
                }
            }

            function setCommand(button) {
                var dataRequest = {}
                dataRequest["command"] = button.id;
                $.ajax({
                    type : "POST",
                    contentType : "application/json",
                    url : "${home}search/api/command",
                    data : JSON.stringify(dataRequest),
                    dataType : 'json',
                    timeout : 100000,
                    success : function(data) {
                        $('#table-game').find('td').text('***');
                        var x = parseInt(data.result.x);
                        var y = parseInt(data.result.y);
                        var currentLocation = (x * $("#gridSize").val()) + y;
                        var locationDetail = data.result.locationDetail;
                        var status = data.result.status;
                        if(!status){
                            alert(data.result.locationDetail);
                            enableCommandButton(true);
                        }
                        $('#table-game').find('td').eq(currentLocation).text('Player');
                        console.log("SUCCESS: ", data);
                    },
                    error : function(e) {
                        console.log("ERROR: ", e);
                    },
                    done : function(e) {
                        console.log("DONE");
                    }
                });
            }

            function enableCommandButton(flag) {
                $(".btn-command").prop("disabled", flag);
            }
        </script>

    </body>
</html>