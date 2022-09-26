package com.example.lab7_4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab7_4.databinding.M001FrgRegisterBinding;

import javax.xml.XMLConstants;

/**
 * Đăng ký bằng SharePreference
 */

public class M001RegisterFragment extends Fragment implements View.OnClickListener {
    private M001FrgRegisterBinding binding;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = M001FrgRegisterBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        initView();
        return rootView;

    }

    private void initView() {
        binding.ivBack.setOnClickListener(M001RegisterFragment.this);
        binding.btnRegister.setOnClickListener(M001RegisterFragment.this);
    }

    /**
     * Sự kiện bấm lên giao diện
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_back) {//Bấm lên nút back
            ((MainActivity) mContext).gotoLoginScreen();
        } else if (v.getId() == R.id.btn_register) { //Bấm lên nút đăng ký
            register(binding.edtEmail.getText().toString().trim(), binding.edtPass.getText().toString().trim(),
                    binding.edtPass2.getText().toString().trim());
        }
    }

    private void register(String mail, String pass1, String pass2) {
        //Kiểm tra đã điền đầy đủ thông tin chưa
        if(mail.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            Toast.makeText(mContext, "Empty value", Toast.LENGTH_SHORT).show();
            return;
        }
        //Kiểm tra 2 pass có trùng nhau không
        if(!pass1.equals(pass2)) {
            Toast.makeText(mContext, "Password is not match", Toast.LENGTH_SHORT).show();
            return;
        }
        //Lấy pass đã lưu trong sharedPreference theo email đăng ký. Xem email này đã tồn tại chưa
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.SAVE_PREF, Context.MODE_PRIVATE);
        String savePass = pref.getString(mail, null);
        if(savePass != null ) {
            Toast.makeText(mContext, "Email is existed!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Thỏa mãn điều kiện thì lưu pass theo key là email
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(mail, pass1);
        editor.apply();

        ((MainActivity)mContext).gotoLoginScreen();
    }

}

