package com.example.allshop.FragmentContainer;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allshop.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText edFullName, edEmail, edPassword, edConfirmPassword;
    private TextView tvSignIn;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private String fullName, email, password, confirmPassword;
    private CheckBox checkBox;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    // Required empty public constructor
    public SignUpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);

        initializationViews(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkBox.setOnCheckedChangeListener(this);
        btnSignUp.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.sign_Up_btnSignUp:

                if (checkingViewsEmpty()){

                    if (checkingPasswordEmail()){

                        changeFragment(new SignInFragment());

                    }

                }

                break;
            case R.id.sign_Up_tvSignIn:

                changeFragment(new SignInFragment());

                break;
        }

    }



    // change the fragment
    private void changeFragment(Fragment fragment){

        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containers_register_fragment,fragment).addToBackStack(null);
        ft.commit();

    }

    // checking this function is or not
    private boolean checkingViewsEmpty(){

        if (TextUtils.isEmpty(edFullName.getText())) {

            edFullName.setError("Enter the full name");
            return false;
        }
        if (TextUtils.isEmpty(edEmail.getText())) {
            edEmail.setError("Enter the email");
            return false;
        }
        if (TextUtils.isEmpty(edPassword.getText())) {

            edPassword.setError("Enter the password");
            return false;

        }
        if (TextUtils.isEmpty(edConfirmPassword.getText())) {

            edConfirmPassword.setError("Enter the confirm password");
            return false;

        }

        if(edEmail.getText().toString().isEmpty()) {
            Toast.makeText(getContext(),"enter email address",Toast.LENGTH_SHORT).show();
        }else {
        }
        return true;
    }

    // checking email and password
    private boolean checkingPasswordEmail(){
        if (edEmail.getText().toString().trim().matches(emailPattern)) {

            if (edPassword.getText().toString().trim().equals(edConfirmPassword.getText().toString().trim())){

                if (edPassword.length()>8){

                    return true;
                }else {
                    edPassword.setError("Enter the minimum 8 characters");
                    return false;
                }
            }else {

                edConfirmPassword.setError("Confirm password is not correct");
                return false;
            }
        } else {

            edEmail.setError("Enter the correct email");
            return false;
        }


    }

    // initialization views
    private void initializationViews(View view){


        edFullName=view.findViewById(R.id.sign_Up_edFullName);
        edEmail=view.findViewById(R.id.sign_Up_edEmail);
        edPassword=view.findViewById(R.id.sign_Up_edPassword);
        edConfirmPassword=view.findViewById(R.id.sign_Up_edConfirmPassword);
        tvSignIn=view.findViewById(R.id.sign_Up_tvSignIn);
        progressBar=view.findViewById(R.id.sign_Up_Process);
        btnSignUp=view.findViewById(R.id.sign_Up_btnSignUp);
        checkBox=view.findViewById(R.id.checkBox);

        progressBar.setVisibility(View.INVISIBLE);

        // initialization of input values
        fullName=edFullName.getText().toString().trim();
        email=edEmail.getText().toString().trim();
        password=edPassword.getText().toString().trim();
        confirmPassword=edConfirmPassword.getText().toString().trim();


    }

    // this method show password and confirm password to user
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked){

            edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            edConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


        }else {

            edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            edConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        }

    }
}
