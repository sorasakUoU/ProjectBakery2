package kawinpart.sorasak.buybakery;

import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    //private


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        BindWidget();

        //Delete SQLite
        //deleteSQLite();

        // Syn Json to SQLite
        synJSon();

    } //Main Method

    private void synJSon() {

        // Connected Http
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intIndex = 0;
        while (intIndex <= 1) {

            //1 Create InputSteam
            InputStream inputStream = null;
            String[] urlJSON = {};

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlJSON[intIndex]);
                HttpResponse httpResponse = new httpClient.execute(httpPost);
                HttpEntity httpEntity.getContent();

            } catch (Exception e) {
                Log.d("31March", "Input ==> " + e.toString());
            }

            //2 Create JSON String
            String strJSON = null;

            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String strLite = null;
                StringBuffer stringBuffer = new StringBuffer();
                while ((strLite = bufferedReader.readLine()) != null) {
                    stringBuffer.append(strLite);
                }
                inputStream.close();
                strJSON = stringBuffer.toString();

            } catch (Exception e) {
                Log.d("31March", "strJSON ==>" + e.toString());
            }

            //3 Update To SQLite

            try {
                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    switch (intIndex) {
                        case 0:
                            //for userTABLE

                            String strUser = jsonObject.getString(MyManage).

                    }

                }

            }



            }


            //2
            //3


        }






    private void BindWidget() {
        userEditText = (EditText) findViewById(R.id.txtUser);
        passwordEditText = (EditText) findViewById(R.id.txtPassword);
    }

    public void clickSingInMain(View view) {
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            myAlert("โปรดกรอกให้ ครบทุกช่องค่ะ");
        }

    } // ClickSignInMain

    private void myAlert(String strAlert) {

    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }


} // Main Class
