<?php
    // Get image string posted from Android App
    $base=$_REQUEST['image'];
    // Get file name posted from Android App
    $filename = $_REQUEST['filename'];
    // Decode Image
    $binary=base64_decode($base);
    header('Content-Type: bitmap; charset=utf-8');
    // Images will be saved under 'www/imgupload/uplodedimages' folder
    $file = fopen('files/'.$filename, 'wb');
    // Create File
    fwrite($file, $binary);
    fclose($file);
    echo 'Image upload complete, Please check your php file directory';
?>