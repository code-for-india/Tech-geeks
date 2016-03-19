<?php require_once("connectvars.php");
if(isset($_POST['submit']))
{
$aadhar=$_POST['aadhar'];
$dbc = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
	  $query = "SELECT * FROM aadhar ";
      $data = mysqli_query($dbc, $query)or die("error querying");
	   while($row=mysqli_fetch_array($data)){
	echo json_encode($row);
	   }
	  }
	   

?>