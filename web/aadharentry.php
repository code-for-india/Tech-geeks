<html>
<body>
<form  enctype="multipart/form-data" method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
  <div class="form-group">
    <label for="pid"><h4>Patient id:</h4></label>
     <input type="text" class="form-control" id="pid" name="pid">
  </div>
  <div class="form-group">
    <label for="pname"><h4>Patient Name:</h4></label>
    <input type="text" class="form-control" id="pname" name="pname">
  </div>
   <div class="form-group">
    <label for="dob"><h4>Date Of Birth:</h4></label>
    <input type="text" class="form-control" id="dob" name="dob" />
  </div>
  <div class="form-group">
    <label class="radio-inline"><input type="radio" name="gender" value="Male">Male</label>
<label class="radio-inline"><input type="radio" name="gender" value="Female">Female</label>

  </div>
  
  <div class="form-group">
    <label for="add"><h4>Address</h4></label>
    <input type="text"  id="add" name="add" />
  </div>
  <div class="form-group">
    <label for="city"><h4>City:</h4></label>
    <input type="text"  id="city" name="city" />
  </div>
  <div class="form-group">
    <label for="pin"><h4>Pin</h4></label>
    <input type="text"  id="pin" name="pin" />
  </div>
  <div class="form-group">
    <label for="contact"><h4>Contact:</h4></label>
    <input type="text"  id="contact" name="contact" />
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
    $pid = $_POST['pid'];
    $pname = $_POST['pname'];
	$gender= $_POST['gender'];
	$dob= $_POST['dob'];
	$add= $_POST['add'];
	$city= $_POST['city'];
	$pin= $_POST['pin'];
	$contact = $_POST['contact'];
  $report = $_FILES['report']['name'];
    $report_type = $_FILES['report']['type'];
    $report_size = $_FILES['report']['size'];
	
     if (!empty($pid) && !empty($pname) && !empty($report) && !empty($gender)&& !empty($dob) && !empty($add)) {
     
        if ($_FILES['report']['error'] == 0) {
          // Move the file to the target upload folder
          $target = 'files/' . $report;
          if (move_uploaded_file($_FILES['report']['tmp_name'], $target)) {
            // Connect to the database
            $dbc = mysqli_connect('localhost','root', '', 'government');

            // Write the data to the database
            $query = "INSERT INTO aadhar(aadhar_id,name,gender,dob,address,city,pincode,contact,image) VALUES ( '$pid', '$pname','$gender','$dob','$add', '$city','$pin','$contact','$report')";
            mysqli_query($dbc, $query);

            // Confirm success with the user
            echo '<p>Thanks for adding your new high score! It will be reviewed and added to the high score list as soon as possible.</p>';
            echo '<p><strong>ID:<strong> ' . $pid . '<br />';
            echo '<strong>Name:</strong> ' . $pname . '<br />';
            echo '<a href="' . 'files/' . $report . '" target="_blank" />view file</a></p>';
             

            // Clear the score data to clear the form
            $pid = "";
            $pname  = "";
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