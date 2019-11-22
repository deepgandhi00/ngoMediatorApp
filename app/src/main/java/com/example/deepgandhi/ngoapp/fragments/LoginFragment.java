package com.example.deepgandhi.ngoapp.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deepgandhi.ngoapp.R;
import com.example.deepgandhi.ngoapp.Utils.Resource;
import com.example.deepgandhi.ngoapp.Utils.SharedPreferenceHelper;
import com.example.deepgandhi.ngoapp.Utils.Status;
import com.example.deepgandhi.ngoapp.di.Injectable;
import com.example.deepgandhi.ngoapp.models.Request.RequestUser;
import com.example.deepgandhi.ngoapp.models.Response.Users;
import com.example.deepgandhi.ngoapp.viewModels.CommonViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class LoginFragment extends Fragment implements Injectable, GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 999;
    private EditText edLoginEmail,edLoginPassword;
    private Button btnLoginLogin,btnLoginWithGoogle;
    private CommonViewModel viewModel;
    private ProgressDialog dialog;
    private GoogleApiClient googleApiClient;
    private TextView txtLoginSignUp;

    @Inject
    ViewModelProvider.Factory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login,container,false);

        edLoginEmail = view.findViewById(R.id.edLoginEmail);
        edLoginPassword = view.findViewById(R.id.edLoginPassword);
        btnLoginLogin = view.findViewById(R.id.btnLoginLogin);
        btnLoginWithGoogle = view.findViewById(R.id.btnLoginWithGoogle);
        txtLoginSignUp = view.findViewById(R.id.txtLoginSignUp);

        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInputs()){
                    dialog = ProgressDialog.show(getActivity(),"","loading please wait",true);
                    setUpViewModel();
                }
            }
        });

        btnLoginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                googleApiClient=new GoogleApiClient.Builder(getContext())
                        .enableAutoManage(getActivity(),LoginFragment.this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                        .build();


                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });

        txtLoginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,new SignUpFragment())
                        .commit();
            }
        });
        return view;
    }

    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(this,factory).get(CommonViewModel.class);
        RequestUser requestUser = new RequestUser();
        requestUser.setPhone(null);
        requestUser.setDate_of_birth(null);
        requestUser.setName(null);
        requestUser.setEmail(edLoginEmail.getText().toString().trim());
        requestUser.setPassword(edLoginPassword.getText().toString().trim());

        viewModel.getLoginUser(requestUser).observe(getViewLifecycleOwner(), new Observer<Resource<Users>>() {
            @Override
            public void onChanged(Resource<Users> usersResource) {
                if(usersResource.status == Status.SUCCESS){
//                    SharedPreferenceHelper helper = new SharedPreferenceHelper(getActivity());
//                    if(helper.saveUser(usersResource.data)){
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.container,new HomeFragment())
//                                .commit();
//                    }
//                    else {
//                        Toast.makeText(getActivity(), "error saving user...", Toast.LENGTH_SHORT).show();
//                    }
                    dialog.dismiss();
                }
                else if(usersResource.status == Status.ERROR){
                    dialog.dismiss();
                    Toast.makeText(getActivity(), usersResource.errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInputs() {
        boolean check = false;
        if(!TextUtils.isEmpty(edLoginEmail.getText().toString().trim()) && Patterns.EMAIL_ADDRESS.matcher(edLoginEmail.getText().toString().trim()).matches()) {
            check = true;
        }

        if(!TextUtils.isEmpty((edLoginPassword.getText().toString().trim())) && edLoginPassword.getText().toString().trim().length() > 6) {
            check = true;
        }

        return check;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                Toast.makeText(getActivity(), result.getSignInAccount().getEmail(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
