package com.example.deepgandhi.ngoapp.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deepgandhi.ngoapp.R;
import com.example.deepgandhi.ngoapp.di.Injectable;

import javax.inject.Inject;

public class SplashFragment extends Fragment implements Injectable {
    private Button btnWelcomeSignUp,btnWelcomeLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_splash,container,false);
        btnWelcomeLogin = view.findViewById(R.id.btnWelcomeLogin);
        btnWelcomeSignUp = view.findViewById(R.id.btnWelcomeSignUp);

        btnWelcomeSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container,new SignUpFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnWelcomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container,new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}
