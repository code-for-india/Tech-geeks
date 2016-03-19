<html >
    <head>
	<?php require_once("connectvars.php");
?>
      <!-- SCROLLS -->
      <!-- load bootstrap and fontawesome via CDN -->
      
      <!-- SPELLS -->
      <!-- load angular and angular route via CDN -->
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
      <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.0.min.js"></script>
      <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body >
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
<!-- Modal -->

      </ul>
    </div><!-- /.navbar-collapse --><!-- /.container-fluid -->
</nav>
<form class="form-inline" role="form" action="" method="post">
  <div class="form-group">
    <label for="aadhar">Aadhar Number</label>
    <input type="aadhar"  name="aadhar" class="form-control" id="aadhar">
  </div>
  <button type="submit" name="submit"class="btn btn-default">Submit</button>
</form>         

</div>
<?php
if(isset($_POST['submit']))
{
$aadhar=$_POST['aadhar'];
$dbc = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
	  $query = "SELECT * FROM aadhar WHERE aadhar_id='$aadhar'";
      $data = mysqli_query($dbc, $query)or die("error querying");
	   while($row=mysqli_fetch_array($data)){
	   echo $row['aadhar_id'];
	   echo $row['name'];
	   echo $row['dob'];
	   echo $row['gender'];
	   echo $row['address'];
	   echo $row['city'];
	   echo '<img src="'.'files/' . $row['image'] . '" alt="image" />';
	   }
	  }
?>

    </body>
    </html>