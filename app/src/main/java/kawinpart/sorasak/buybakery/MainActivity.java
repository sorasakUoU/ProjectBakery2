package kawinpart.sorasak.buybakery;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.http.HttpResponseCache;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    // Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private MyManage myManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind widget
        bindWidget();

        myManage = new MyManage(this);

        // Delete SQLite
        deleteSQLite();

        // Syn Json to SQLite
        synJSon();

    } // Main Metthod

    private void synJSon() {

        // Connected Http
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intIndex = 0;
        while (intIndex <= 1) {

            //1 Create InputSteam
            InputStream inputStream = null;
            String[] urlJSON = {""};

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlJSON[intIndex]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

            } catch (Exception e) {
                Log.d("31March", "Input ==> " + e.toString());
            }

            //2 Create JSON Sting
            String strJSON = null;

            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String strLite = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((strLite = bufferedReader.readLine()) != null) {
                    stringBuilder.append(strLite);
                }
                inputStream.close();
                strJSON = stringBuilder.toString();

            } catch (Exception e) {
                Log.d("31March", "strJSON ==> " + e.toString());
            }


            //3 Update To SQLite

            try {
                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    switch (intIndex) {
                        case 0:
                            // for userTABLE

                            String strUser = jsonObject.getString(MyManage.column_User);
                            String strPassword = jsonObject.getString(MyManage.column_Password);
                            String strName = jsonObject.getString(MyManage.column_Name);
                            String strIDcard = jsonObject.getString(MyManage.column_IDcard);

                            myManage.addUser(strUser, strPassword, strName, strIDcard);

                            break;
                        case 1:
                            // for serviceTABLE

                            String strCategory = jsonObject.getString(MyManage.column_Category);
                            String strImage1 = jsonObject.getString(MyManage.column_Image1);
                            String strImage2 = jsonObject.getString(MyManage.column_Image2);
                            String strImage3 = jsonObject.getString(MyManage.column_Image3);
                            String strImage4 = jsonObject.getString(MyManage.column_Image4);
                            String strImage5 = jsonObject.getString(MyManage.column_Image5);
                            String strDescription = jsonObject.getString(MyManage.column_Description);
                            String strPrice = jsonObject.getString(MyManage.column_Price);
                            String strItem = jsonObject.getString(MyManage.column_Item);

                            myManage.addService(strCategory, strImage1, strImage2,
                                    strImage3, strImage4, strImage5, strDescription,
                                    strPrice, strItem);

                            break;
                    }

                }

            } catch (Exception e) {
                Log.d("31March", "Update ==> " + e.toString());
            }


            intIndex += 1;
        }

    } // SynJSON

    private void deleteSQLite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
        sqLiteDatabase.delete(MyManage.service_table, null, null);
    }

    public void clickSignInMain(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        // Check Space
        if (userString.equals("") || passwordString.equals("")) {
            // Have space
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่องครับ");
        } else {

            // No Space
            checkUser();

        }

    } // clickSignIn

    private void checkUser() {

        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User = " + "'" + userString + "'", null);
            cursor.moveToFirst();
            String[] resultStrings = new String[cursor.getColumnCount()];
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                resultStrings[i] = cursor.getString(i);

            }
            cursor.close();

            // check Password
            if (passwordString.equals(resultStrings[2])) {
                // Password True
                Toast.makeText(this,
                        "ยินดีต้อนรับ" + resultStrings[3],
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ShowServiceActivity.class);
                intent.putExtra("NameUser", resultStrings[3]);
                startActivity(intent);
                finish();

            } else {
                // Password false
                MyAlertDialog myAlertDialog = new MyAlertDialog();
                myAlertDialog.myDialog(this, "Password ผิด", "กรุณาพิมพ์ใหม่ Password ผิด");

            }


        } catch (Exception e) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, "User ผิด", "ไม่มี User นี้ในฐานข้อมูลของเรา");
        }

    } // chcekUser

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.txtUser);
        passwordEditText = (EditText) findViewById(R.id.txtPassword);
    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

} // Main Class
