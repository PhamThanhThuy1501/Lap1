package com.vdsl.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vdsl.myapplication.databinding.ActivityLoginEmailBinding;
import com.vdsl.myapplication.databinding.ActivityMainBinding;

public class LoginEmail extends AppCompatActivity {

    ActivityLoginEmailBinding loginEmailBinding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        loginEmailBinding = ActivityLoginEmailBinding.inflate(getLayoutInflater());
        setContentView(loginEmailBinding.getRoot());

        loginEmailBinding.btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginEmail.this,SignUp.class);
            startActivity(intent);
        });

        mAuth = FirebaseAuth.getInstance();


        loginEmailBinding.btnLogin.setOnClickListener(v -> {
                String username = loginEmailBinding.edtUsername.getText().toString();
                String password = loginEmailBinding.edtPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Main", "SignInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(LoginEmail.this, LogOut.class));
                                } else {
                                    Log.w("Main", "SignInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginEmail.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        });

        loginEmailBinding.txtForgetPass.setOnClickListener(v -> {
            String email = loginEmailBinding.edtUsername.getText().toString();
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginEmail.this, "Kiểm tra gmail để đặt lại mật khẩu !!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginEmail.this, "Lỗi khi gửi mail", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}