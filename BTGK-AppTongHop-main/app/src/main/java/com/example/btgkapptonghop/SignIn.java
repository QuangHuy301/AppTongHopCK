package com.example.btgkapptonghop;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignIn extends AppCompatActivity {

    Button signin,signup;
    EditText username,password;
    List<Account> listacc;
    TextView forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        listacc = new ArrayList<>();
        listacc.add(new Account("admin","admin"));
        listacc.add(new Account("huy","123"));
        listacc.add(new Account("test","1"));

        signin = (Button) findViewById(R.id.signin);
        username = (EditText) findViewById(R.id.editTextTextPersonName);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        forgotpass = (TextView) findViewById(R.id.textView14) ;
        signup = (Button) findViewById(R.id.button3);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkpass()){
                    password.setText("");
                    Toast.makeText(SignIn.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent profile = new Intent(SignIn.this,HomePage.class);
                    startActivity(profile);
                }
                else {
                    Toast.makeText(SignIn.this,"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(SignIn.this, SignUp.class);
//                startActivityForResult(signup,1); cách cũ
                getResult.launch(signup);//cách mới
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignIn.this,"Chưa hỗ trợ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private ActivityResultLauncher<Intent> getResult =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Bundle dataacc =  data.getBundleExtra("bundle");
                        Account newacc = (Account) dataacc.getSerializable("newacc");
                        listacc.add(newacc);
                        username.setText(newacc.getUserName());
                        password.setText(newacc.getPassWord());
                    }
                    if (result.getResultCode() == Activity.RESULT_CANCELED){
                        Toast.makeText(SignIn.this,"Bạn chưa tạo tài khoản",Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    public boolean checkpass(){
        boolean result = false;
        for (Account acc : listacc) {
            boolean check1 = String.valueOf(username.getText()).equals(acc.getUserName());
            boolean check2 = String.valueOf(password.getText()).equals(acc.getPassWord());
            if (check1 && check2){
                result = true;
            }
        }
        return result;
    }
}