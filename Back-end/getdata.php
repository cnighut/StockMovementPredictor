<?php
    //connect and select the database

$servername = "localhost";  
$username = "root";         
$password="";  
$database1 = "alma18int";
$conn = new mysqli($servername, $username, $password, $database1);

if($conn->connect_error){
    die("COnnection failed ".$conn->connect_error);
}

$indicators = array();
$sql = "SELECT * FROM csvTable2";
$stmt = $conn->prepare($sql);
$stmt->execute();
$stmt->bind_result($ONE, $TWO,$THREE, $FOUR, $FIVE);

while($stmt->fetch()){
    $temp = [
    'ONE'=>$ONE,
    'TWO'=>$TWO,
    'THREE'=>$THREE,
    'FOUR'=>$FOUR,
    'FIVE'=>$FIVE
    ];

    array_push($indicators, $temp);
}

echo json_encode($indicators);
?>