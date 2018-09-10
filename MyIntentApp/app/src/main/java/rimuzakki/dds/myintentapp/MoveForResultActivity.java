package rimuzakki.dds.myintentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MoveForResultActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnChoose;
    private RadioGroup rgNumber;
    public static String EXTRA_SELECTED_VALUE = "extra_selected_value";
    public static int RESULT_CODE = 110; //pemberian nilai bebas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_for_result);
        btnChoose = (Button)findViewById(R.id.btn_choose);
        btnChoose.setOnClickListener(this);
        rgNumber = (RadioGroup)findViewById(R.id.rg_number);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_choose) {
            if (rgNumber.getCheckedRadioButtonId() != 0) { //validasi apakah ada nilai dr obyek RadioButton
                int value = 0;

                switch (rgNumber.getCheckedRadioButtonId()) {
                    case R.id.rb_50:
                        value = 50;
                        break;
                    case R.id.rb_100:
                        value = 100;
                        break;
                    case R.id.rb_150:
                        value = 150;
                        break;
                    case R.id.rb_200:
                        value = 200;
                        break;
                }

                // Kita membuat sebuah intent tanpa ada inputan apapun di konstruktornya.
                // Kemudian kita meletakkan variabel value kedalam metode putExtra(Key, Value) dengan EXTRA_SELECTED_VALUE bertipekan static string dan bernilai “extra_selected_value”.
                // Kemudian kita jadikan obyek resultIntent yang telah dibuat sebelumnya menjadi parameter dari setResult(RESULT_CODE, Intent).
                // Setelah itu, kita panggil method finish() untuk menutup MoveForResultActivity.

                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value);
                setResult(RESULT_CODE, resultIntent);
                finish();
            }
        }
    }
}
