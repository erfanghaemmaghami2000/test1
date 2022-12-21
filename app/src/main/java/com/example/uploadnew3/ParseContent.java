package com.example.uploadnew3;

import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

//https://demonuts.com/android-upload-image/

public class ParseContent {
//  worked

    private final String KEY_SUCCESS = "status";
    private final String KEY_MSG = "message";
    private Activity activity;

    ArrayList<HashMap<String, String>> arraylist;

    public ParseContent(Activity activity) {
        this.activity = activity;
    }

    public boolean isSuccess(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString(KEY_SUCCESS).equals("true")) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(KEY_MSG);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public String getURL(String response) {
        String url="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            jsonObject.toString().replace("\\\\","");
            if (jsonObject.getString(KEY_SUCCESS).equals("true")) {

                arraylist = new ArrayList<HashMap<String, String>>();
                JSONArray dataArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    url = dataobj.optString("pathToFile");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }

}
//    <?php
//    define('HOST','efikhatar.ir');// domain site ya fek konam ftp site
//    define('USER','efikhata');  //user name ke to email
//    define('PASS',':7w1XYpD6iXx3+');	// password ke to email
//    define('DB','efikhata_users1');  // data base ke bayad to database(ehtemal ziad data base na php admin) dorostesh koni khodet
//
//$con = mysqli_connect(HOST,USER,PASS,DB);
//
//// Check connection
//        if (mysqli_connect_errno())
//        {
//        echo "Failed to connect to MySQL: " . mysqli_connect_error();
//        }else{  //echo "Connect";
//
//        }
//
//        if($_SERVER['REQUEST_METHOD']=='POST'){
//        // echo $_SERVER["DOCUMENT_ROOT"];  // /home1/demonuts/public_html
//        //including the database connection file
//
//
//        //$_FILES['image']['name']   give original name from parameter where 'image' == parametername eg. city.jpg
//        //$_FILES['image']['tmp_name']  temporary system generated name
//
//        $originalImgName= $_FILES['filename']['name'];
//        $tempName= $_FILES['filename']['tmp_name'];
//        $folder="uploads3/";
//        //$url = "http://www.demonuts.com/Demonuts/JsonTest/Tennis/uploadedFiles/".$originalImgName; //update path as per your directory structure
//        $url = "http://efikhatar.ir/uploads3".$originalImgName; //update path as per your directory structure
//
//        if(move_uploaded_file($tempName,$folder.$originalImgName)){
//        $query = "INSERT INTO upload3 (pathToFile) VALUES ('$url')";// inja bayad ye table database dorost she ke esmesh upload3 bashe ke ye id(int) v ye pathToFile(varchar) dashte bashe
//        if(mysqli_query($con,$query)){
//
//        $query= "SELECT * FROM upload_image_video WHERE pathToFile='$url'";
//        $result= mysqli_query($con, $query);
//        $emparray = array();
//        if(mysqli_num_rows($result) > 0){
//        while ($row = mysqli_fetch_assoc($result)) {
//        $emparray[] = $row;
//        }
//        echo json_encode(array( "status" => "true","message" => "Successfully file added!" , "data" => $emparray) );
//
//        }else{
//        echo json_encode(array( "status" => "false","message" => "Failed!") );
//        }
//
//        }else{
//        echo json_encode(array( "status" => "false","message" => "Failed!") );
//        }
//        //echo "moved to ".$url;
//        }else{
//        echo json_encode(array( "status" => "false","message" => "Failed!") );
//        }
//        }
//// inja dar kol miad name v image ro migire va dar poshe image ro be esm name mirize v url kolesho be data base mide