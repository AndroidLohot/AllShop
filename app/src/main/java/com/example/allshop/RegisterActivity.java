package com.example.allshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.allshop.FragmentContainer.SignInFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        changeFragment(new SignInFragment());
    }

    // this function by default going Sign In fragment
    private void changeFragment(Fragment fragment){

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containers_register_fragment,fragment);
        ft.commit();
    }
}
