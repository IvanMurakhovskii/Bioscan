package com.murik.lite.ui.fragment.login;

import android.accounts.AuthenticatorException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.murik.lite.App;
import com.murik.lite.R;
import com.murik.lite.Screens;
import com.murik.lite.configuration.AuthService;
import com.murik.lite.ui.activity.start.StartActivity;

import java.util.Objects;

public class LoginFragment extends MvpAppCompatFragment {

    public static final String TAG = "LOGIN_FRAGMENT";

    private EditText login;
    private EditText password;
    private Button btnLogin;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login = view.findViewById(R.id.et_login);
        password = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            if (isAuth()) {
                App.INSTANCE.getRouter().navigateTo(Screens.REALM_FRAGMENT);
                ((StartActivity) Objects.requireNonNull(getActivity())).visibleAdminMenuItems(true);
            }
        });

    }

    private boolean isAuth() {
        try {
            AuthService.getInstance().login(login.getText().toString(), password.getText().toString());
            return true;
        } catch (AuthenticatorException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
