package com.example.allshop.FragmentContainer;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allshop.HomeActivity;
import com.example.allshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements View.OnClickListener {

    private EditText edEmail, edPassword;
    private TextView tvForgetPassword, tvCreatingNewAccount;
    private ProgressBar progressBar;
    private Button btnSignIn;
    private String email, password;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_in, container, false);

        initializationViews(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSignIn.setOnClickListener(this);

        tvCreatingNewAccount.setOnClickListener(this);

        tvForgetPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            // this code has been going user sign in the account
            case R.id.sign_in_btnSignIn:

                if (checkingViews()){

                    loginAccount();

                }

                break;
            // this code has been going (user creating new account) fragment
            case R.id.sign_in_tvCreatingNewAccount:

                changeFragment(new SignUpFragment());

                break;
            // this code has been going (user forget your account) fragment
            case R.id.sign_In_tvForgetPassword:

                changeFragment(new ForgetPasswordFragment());

                break;
        }
    }

    // this code is login account and going home activity
    private void loginAccount(){

        getActivity().startActivity(new Intent(getContext(), HomeActivity.class));
        Toast.makeText(getContext(),"Data is save",Toast.LENGTH_LONG).show();
    }

    // change the fragment
    private void changeFragment(Fragment fragment){

        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containers_register_fragment,fragment).addToBackStack(null);
        ft.commit();

    }

    // initialization views
    private void initializationViews(View view){

        edEmail=view.findViewById(R.id.sign_in_edEmail);
        edPassword=view.findViewById(R.id.sign_in_edPassword);

        tvCreatingNewAccount=view.findViewById(R.id.sign_in_tvCreatingNewAccount);
        tvForgetPassword=view.findViewById(R.id.sign_In_tvForgetPassword);

        progressBar=view.findViewById(R.id.sign_in_progressBar);

        btnSignIn=view.findViewById(R.id.sign_in_btnSignIn);

        progressBar.setVisibility(View.INVISIBLE);

        email=edEmail.getText().toString().trim();
        password=edPassword.getText().toString().trim();

    }

    // checking views are empty or not
    private boolean checkingViews(){

        if (TextUtils.isEmpty(edEmail.getText())){ edEmail.setError("Enter the email"); return false; }
        if (TextUtils.isEmpty(edPassword.getText())){ edPassword.setError("Enter the password"); return false; }

        return true;
    }


}
