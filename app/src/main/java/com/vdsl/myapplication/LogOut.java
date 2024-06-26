package com.vdsl.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.vdsl.myapplication.databinding.ActivityLogOutBinding;

public class LogOut extends AppCompatActivity {

    FirebaseAuth mAuth;
    ActivityLogOutBinding logOutBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        logOutBinding = ActivityLogOutBinding.inflate(getLayoutInflater());
        setContentView(logOutBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        logOutBinding.btnLogOut.setOnClickListener(v -> {
            mAuth.signOut();
        });
    }
}