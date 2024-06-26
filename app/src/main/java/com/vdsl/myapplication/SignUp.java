package com.vdsl.myapplication;

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
import com.vdsl.myapplication.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding signUpBinding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        signUpBinding.btnBack.setOnClickListener(v -> {
            finish();
        });

        signUpBinding.btnRegister.setOnClickListener(v -> {
            String username = signUpBinding.edtUsername.getText().toString();
            String password = signUpBinding.edtPassword.getText().toString();

            mAuth.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("Main", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Đăng Ký Thành Công", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Log.w("Main", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUp.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}
