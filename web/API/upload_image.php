<?php
$target_path = "/uploads/";
$base=$_REQUEST['image'];
$name=$_REQUEST['cmd'];
echo $base;
echo $name;
// base64 encoded utf-8 string
$binary=base64_decode($base);
// binary, utf-8 bytes
header('Content-Type: bitmap; charset=utf-8');
// print($binary);
//$theFile = base64_decode($image_data);
$file = fopen($target_path, 'wb');
$file = fopen($name, 'wb');
fwrite($file, $binary);
fclose($file);
//move_uploaded_file($file,$target_path.$name);
copy($name,$target_path.$name);
?>