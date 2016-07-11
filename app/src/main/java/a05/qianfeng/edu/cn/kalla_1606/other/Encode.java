package a05.qianfeng.edu.cn.kalla_1606.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.se7en.endecryption.DES;
import com.se7en.endecryption.MD5;

import java.io.UnsupportedEncodingException;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * Created by Administrator on 2016/6/22.
 */
public class Encode extends AppCompatActivity implements View.OnClickListener{

    private Button md5,base64,des;
    private TextView tvMd5,tvBase64,tvDes;
    private EditText edtUsername,edtPassword;
    private String content,key;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encode_demo);
        md5 = (Button) findViewById(R.id.btn_md5);

        base64 = (Button) findViewById(R.id.btn_base64);
        des = (Button) findViewById(R.id.btn_des);

        tvMd5 = (TextView) findViewById(R.id.tv_md5);
        tvBase64 = (TextView) findViewById(R.id.tv_base64);
        tvDes = (TextView) findViewById(R.id.tv_des);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        initView();
    }

    private void initView(){
        md5.setOnClickListener(this);
        edtUsername.setOnClickListener(this);
        edtPassword.setOnClickListener(this);
        des.setOnClickListener(this);
        base64.setOnClickListener(this);
       // md5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.edt_username:
                    edtUsername.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                                content =s.toString().trim();
                        }
                    });
                    break;
                case R.id.edt_password:
                    edtPassword.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                                key = s.toString().trim();
                        }
                    });
                    break;
                case R.id.btn_md5:
                    String s = MD5.md5(content);
                    tvMd5.setText(s);
                    break;
                case R.id.btn_base64:
                    //加密
                    byte[] encode = Base64.encode(content.getBytes(),Base64.DEFAULT);
                    try {
                        String str = new String(encode,"utf-8");
                        tvBase64.setText(str);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //解密
                    byte[] decode = Base64.decode(encode,Base64.DEFAULT);
                    try {
                        String de = new String(decode,"utf-8");
                        Log.e("decode","=="+de);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btn_des:
                    byte[] encrypt = DES.encrypt(content.getBytes(),key);

            }
    }
}
