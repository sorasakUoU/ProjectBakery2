package kawinpart.sorasak.buybakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
            myToast("กรุณากรอกให้ครบ ค่ะ");

        } else {
            // No Space
            checkIdCard();
        }

    } // clickSignUp

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
