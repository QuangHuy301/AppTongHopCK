package com.example.btgkapptonghop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Button signup;
    ImageView back;
    EditText user,pass,confirmpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup = (Button) findViewById(R.id.button3);
        back = (ImageView) findViewById(R.id.backsignin);
        user = (EditText) findViewById(R.id.editTextTextPersonName);
        pass = (EditText) findViewById(R.id.editTextTextPassword);
        confirmpass = (EditText) findViewById(R.id.editTextTextPassword2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean next =true;
                if (user.getText().toString().equals("")
                        || pass.getText().toString().equals("")
                        || confirmpass.getText().toString().equals(""))
                {
                    Toast.makeText(SignUp.this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    next =false;
                }

                if (next && !pass.getText().toString().equals(confirmpass.getText().toString()))
                {
                    Toast.makeText(SignUp.this,"Xác nhận sai",Toast.LENGTH_SHORT).show();
                    next =false;
                }

                if (next)
                {
                    Account newacc = new Account(user.getText().toString(),pass.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("newacc",newacc);
                    Intent resultIntent  = new Intent();
                    resultIntent.putExtra("bundle",bundle);
                    setResult(RESULT_OK,resultIntent);
                    finish();
                }
            }
        });
    }
}