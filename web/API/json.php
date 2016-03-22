<?php require_once("connectvars.php");
$aadhar=$_POST['aadhar'];
$dbc = mysqli_connect('mysql.hostinger.in', 'u684030433_gov', 'fastrack', 'u684030433_gov');
	  $query = "SELECT image FROM aadhar WHERE aadhar_id='$aadhar'";
      $data = mysqli_query($dbc, $query)or die("error querying");
	   while($row=mysqli_fetch_array($data)){
	$x= "bloodbanksys.esy.es/bloodbank/files". $row['image'];
	   }
	  }
	   echo json_encode($x);

?>