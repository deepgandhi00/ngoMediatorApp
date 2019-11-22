package com.example.deepgandhi.ngoapp.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class SignUpFragment extends Fragment implements Injectable, GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 888;
    private EditText edSignUpName,edSignUpPhone,edSignUpEmail,edSignUpPassword,edSignUpDob;
    private Button btnSignUpSignUp,btnSignUpWithGoogle;
    private CommonViewModel viewModel;
    private int mYear, mMonth, mDay;
    private int sYear,sMonth,sDay;
    private boolean isEmail,isPassword,isName,isPhone,isDOB;
    private Calendar myCalendar;
    private ProgressDialog dialog;
    private GoogleApiClient googleApiClient;
    private TextView txtSignUpLogin;

    @Inject
    ViewModelProvider.Factory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup,container,false);
        edSignUpName = view.findViewById(R.id.edSignUpName);
        edSignUpPhone = view.findViewById(R.id.edSignUpPhone);
        edSignUpEmail = view.findViewById(R.id.edSignUpEmail);
        edSignUpPassword = view.findViewById(R.id.edSignUpPassword);
        edSignUpDob = view.findViewById(R.id.edSignUpDob);
        btnSignUpSignUp = view.findViewById(R.id.btnSignUpSignUp);
        btnSignUpWithGoogle = view.findViewById(R.id.btnSignUpWithGoogle);
        txtSignUpLogin = view.findViewById(R.id.txtSignUpLogin);

        Calendar myCalendar = Calendar.getInstance();

        edSignUpDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, day);

                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        edSignUpDob.setText(sdf.format(myCalendar.getTime()));
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        txtSignUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,new LoginFragment())
                        .commit();
            }
        });

        btnSignUpSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInputs()){
                    dialog = ProgressDialog.show(getActivity(),"","loading please wait",true);
                    RequestUser user = new RequestUser();
                    user.setEmail(edSignUpEmail.getText().toString().trim());
                    user.setName(edSignUpName.getText().toString().trim());
                    user.setPassword(edSignUpPassword.getText().toString().trim());
                    user.setDate_of_birth(edSignUpDob.getText().toString().trim());
                    user.setPhone(edSignUpPhone.getText().toString().trim());
                    setUpViewModel(user);
                }
                else {
                    Toast.makeText(getActivity(), "Enter Valid Inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUpWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                googleApiClient=new GoogleApiClient.Builder(getContext())
                        .enableAutoManage(getActivity(),SignUpFragment.this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                        .build();

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });

        return view;
    }

    private boolean validateInputs() {
        boolean check = false;
        if(!TextUtils.isEmpty(edSignUpEmail.getText().toString().trim()) && Patterns.EMAIL_ADDRESS.matcher(edSignUpEmail.getText().toString().trim()).matches()) {
            isEmail = true;
            check = true;
        }
        else {
            check = false;
            isEmail = false;
        }

        if(edSignUpPhone.getText().toString().trim().length() == 10) {
            isPhone = true;
            check = true;
        }
        else {
            isPhone = false;
            check = false;
        }

        if(!TextUtils.isEmpty(edSignUpName.getText().toString().trim())){
            isName = true;
            check = true;
        }
        else {
            isName = false;
            check = false;
        }

        if(!(mDay == myCalendar.DAY_OF_MONTH && myCalendar.MONTH == mMonth && myCalendar.YEAR == mYear)) {
            check = true;
            isDOB = true;
        }
        else {
            check = false;
            isDOB = false;
        }

        if(!TextUtils.isEmpty((edSignUpPassword.getText().toString().trim())) && edSignUpPassword.getText().toString().trim().length() > 6) {
            isPassword = true;
            check = true;
        }
        else {
            isPassword = false;
            check = false;
        }
        return check;
    }

    private void setUpViewModel(RequestUser user) {
        viewModel = ViewModelProviders.of(this,factory).get(CommonViewModel.class);
        viewModel.getSignInUser(user).observe(getViewLifecycleOwner(), new Observer<Resource<Users>>() {
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
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                dialog = ProgressDialog.show(getActivity(),"","loading please wait",true);
                RequestUser user = new RequestUser();
                user.setEmail(result.getSignInAccount().getEmail());
                user.setName(result.getSignInAccount().getDisplayName());
                user.setPassword("default");
                user.setDate_of_birth(null);
                user.setPhone(null);

                setUpViewModel(user);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
