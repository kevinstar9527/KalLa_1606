package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * Created by Administrator on 2016/6/19.
 */
public class ScanQrActivity extends AppCompatActivity {
    private TextView resultTextView;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private CheckBox mCheckBox;
    private Button scanBarCodeButton;
    private Button generateQRCodeButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        resultTextView = (TextView)findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) findViewById(R.id.iv_qr_image);
        mCheckBox = (CheckBox) findViewById(R.id.logo);

        scanBarCodeButton = (Button)findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(ScanQrActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        generateQRCodeButton = (Button)findViewById(R.id.btn_add_qrcode);
        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String contentString = qrStrEditText.getText().toString();
                if (!contentString.equals("")) {
                    //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350,
                            mCheckBox.isChecked() ?
                                    BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher) :
                                    null);
                    qrImgImageView.setImageBitmap(qrCodeBitmap);
                } else {
                    Toast.makeText(ScanQrActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            resultTextView.setText(scanResult);
        }
    }
}
