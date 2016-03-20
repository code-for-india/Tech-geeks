<html>
<body>
<form  enctype="multipart/form-data" method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
<div class="form-group">
    <label for="mid"><h4>M id</h4></label>
     <input type="text" class="form-control" id="mid" name="mid">
  </div>
  <div class="form-group">
    <label for="mname"><h4>M name</h4></label>
     <input type="text" class="form-control" id="mname" name="mname">
  </div>
  <div class="form-group">
    <label for="loc"><h4>location</h4></label>
    <input type="text" class="form-control" id="loc" name="loc">
  </div>
  <div class="form-group">
    <label for="desc"><h4>Monument Description:</h4></label>
    <input type="text"  id="desc" name="desc" />
  </div>
  <div>
   <label for="report"><h4>Patient Reports:</h4></label>
	<input type="file" id="report" name="report">
  </div>
  <button type="submit" name="submit" class="btn btn-primary">Upload</button><br/>
  
</form>
<?php
 

  if (isset($_POST['submit'])) {
    // Grab the score data from the POST
    $mid = $_POST['mid'];
	$mname = $_POST['mname'];
	$loc= $_POST['loc'];
	$desc= $_POST['desc'];
  $report = $_FILES['report']['name'];
    $report_type = $_FILES['report']['type'];
    $report_size = $_FILES['report']['size'];
	
     if (!empty($mname) && !empty($report)) {
     
        if ($_FILES['report']['error'] == 0) {
          // Move the file to the target upload folder
          $target = 'mfiles/' . $report;
          if (move_uploaded_file($_FILES['report']['tmp_name'], $target)) {
            // Connect to the database
            $dbc = mysqli_connect('localhost','root', '', 'government')or die("error connecting");

            // Write the data to the database
            $query = "INSERT INTO 
			monument(m_id,mname,loc,desc,image) VALUES ('$mid', '$mname','$loc','$desc','$report')";
            mysqli_query($dbc, $query)or die("error");

            // Confirm success with the user
            echo '<p>Thanks for adding your new high score! It will be reviewed and added to the high score list as soon as possible.</p>';
           
            echo '<a href="' . 'mfiles/' . $report . '" target="_blank" />view file</a></p>';
             

            // Clear the score data to clear the form
           
            $mname  = "";
            $report = "";

            mysqli_close($dbc);
          }
          else {
            echo '<p class="error">Sorry, there was a problem uploading your screen shot image.</p>';
          }
        }
      }


      // Try to delete the temporary screen shot image file
      @unlink($_FILES['report']['tmp_name']);
    }
    else {
      
    }
  
?>
</body>
</html>