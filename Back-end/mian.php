<?php

echo "start";

$servername = "localhost";  
$username = "root";         
$password="";  
$conn = mysql_connect ($servername , $username , $password) or die("unable to connect to host");  
$sql = mysql_select_db ('alma18int',$conn) or die("unable to connect to database");

$queryd = mysql_query("TRUNCATE TABLE csvTable2");
if($queryd){
	echo "WOW1";
}else{
	echo "cry";
}

$query2d = mysql_query("TRUNCATE TABLE csvTable");
if($query2d){
	echo "WOW";
}else{
	echo "cry";
}


echo "2";

$csv_file = "prediction.csv"; 
$csv_file2 = "data.csv";
if (($handle = fopen($csv_file, "r")) !== FALSE) { 

   while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
        $num = count($data);
        
        for ($c=0; $c < $num; $c++) {
          $col[$c] = $data[$c];
        }

 		$col_1 = $col[0];
 		$col_2 = $col[1];
 		$col_3 = $col[2];
 		$col_4 = $col[3];
 		$col_5 = $col[4];


 		$query = "INSERT INTO csvTable2(ONE,TWO,THREE,FOUR,FIVE) VALUES ('$col_1','$col_2','$col_3','$col_4', '$col_5') ";

		if (mysql_query($query, $conn) ==TRUE){
			echo "WOW!";
		}else{
			echo "crymore";
		}
 	}

    fclose($handle);

 }else{
 	echo "f";
 }

 echo "cool";
$conn2 = mysql_connect ($servername , $username , $password) or die("unable to connect to host");  
if (($handle = fopen($csv_file2, "r")) !== FALSE) { 

   while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
        $num = count($data);
        
        for ($c=1; $c < $num; $c++) {
          $col[$c-1] = $data[$c];
        }

 		$col_1 = $col[0];
 		$col_2 = $col[1];
 		$col_3 = $col[2];
 		$col_4 = $col[3];
 		//$col_5 = $col[4];
 		echo"print";

 		$query = "INSERT INTO csvTable(OPEN,HIGH,LOW,CLOSE) VALUES ('$col_1','$col_2','$col_3','$col_4') ";

		if (mysql_query($query, $conn2) ==TRUE){
			echo "WOW!";
		}else{
			echo "crymore";
		}
 	}

    fclose($handle);

 }else{
 	echo "f";
 }



?>