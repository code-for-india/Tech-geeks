 <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<div class="container">
	<div class="row">

		<div class=" col-md-offset-3 col-md-4">
			<div class="form-group">
  <label for="sel1">Select list:</label>
  <select class="form-control" id="sel1">
    <option>Taj Mahal</option>
    <option>Qutub Minar</option>
    <option>Jantar Mantar</option>
    <option>Lotus Temple</option>
  </select>

</div>
	</div>
</div>
  <div class="row">
    <button ng-click="book()" class=" col-md-offset-3 col-md-4 btn btn-primary btn-md">Book Your Ticket</button>
    <button ng-click="compare()" style="margin-top:2%"class=" col-md-offset-3 col-md-4 btn btn-primary btn-md">Compare</button>
  
  </div>

    <div ng-show="status" style="margin:1%;padding:1%;border-radius:2%" class ="well row">
      Your booking is confirmed
    </div>
    <div class="jumbotron">
     <div ng-repeat="i in compareddata">
      {{i}}
     </div>
   </div>
</div>
