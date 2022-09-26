package com.example.lab7_4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab7_4.databinding.M000FrgLoginBinding;

public class M000LoginFragment extends Fragment implements View.OnClickListener {
    private M000FrgLoginBinding binding;
    private Context mContext;
    TextView textView;
    LinearLayout layout;
    View v;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = M000FrgLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();

        return view;
    }

    private void initView() {
        binding.btnLogin.setOnClickListener(M000LoginFragment.this);
        binding.tvRegister.setOnClickListener(M000LoginFragment.this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            login(binding.edtEmail.getText().toString().trim(), binding.edtPass.getText().toString().trim());
        } else if (v.getId() == R.id.tv_register) {
            ((MainActivity) mContext).gotoRegisterScreen();
        }

    }

    private void login(String mail, String pass) {
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.SAVE_PREF, Context.MODE_PRIVATE);
        String savePass = pref.getString(mail, null);
        //Kiểm tra tài khoản đã đăng ký chưa
        if (savePass == null) {
            Toast.makeText(mContext, "Email is not existed!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Kiểm tra mật khẩu có đúng không
        if (!pass.equals(savePass)) {
            Toast.makeText(mContext, "Password is not correct!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Thỏa mãn điều kiện thì đăng nhập
        Toast.makeText(mContext, "Login account successfully!", Toast.LENGTH_SHORT).show();
    }
}
