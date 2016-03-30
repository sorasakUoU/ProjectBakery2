package kawinpart.sorasak.buybakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        BindWidget();

    } //Main Method

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
