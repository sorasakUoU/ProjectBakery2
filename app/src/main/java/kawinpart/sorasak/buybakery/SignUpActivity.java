package kawinpart.sorasak.buybakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, userEditText, passwordEditText, IdCardEditText;
    private String nameString, userString, passwordString, IdCardString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();


    } //Main Method

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        IdCardString = IdCardEditText.getText().toString().trim();

        // Check Space
        if (checkspace()) {
            // Have Space
            Toast.makeText(this, "มีช่องว่าง", Toast.LENGTH_SHORT).show();

        } else {
            // No Space
            updateToServer();
            checkIdCard();
        }

    } // clickSignUp

    private void updateToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("User", userString)
                .add("Password", passwordString)
                .add("ID_Card", IdCardString)
                .build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/bic/php_add_user_nice.php").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });

    } // updateToServer

    private void myToast(String strToast) {
        Toast.makeText(SignUpActivity.this, strToast, Toast.LENGTH_SHORT).show();
    }

    private boolean checkspace() {

        boolean result = true;
        result = nameString.equals("") || userString.equals("") ||
                passwordString.equals("") || IdCardString.equals("");
        return result;
    }


    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        IdCardEditText = (EditText) findViewById(R.id.editText4);
    }

    private void checkIdCard() {
        if (IdCardString.length() == 13) {

        } else {
            // IdCard False
            myToast("รหัสบัตรไม่ถูกต้อง ค่ะ");
        }
    } // checkIdCard


} //Main Class
