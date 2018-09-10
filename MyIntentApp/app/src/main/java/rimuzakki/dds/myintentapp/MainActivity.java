package rimuzakki.dds.myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnMoveActivity;
    private Button btnMoveWithDataActivity;
    private Button btnMoveWithObject;
    private Button btnDialPhone;
    private Button btnMoveForResult;
    private TextView tvResult;
    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveActivity = (Button)findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        btnMoveWithDataActivity = (Button)findViewById(R.id.btn_move_activity_data);
        btnMoveWithDataActivity.setOnClickListener(this);

        btnMoveWithObject = (Button)findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        btnDialPhone = (Button)findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);

        btnMoveForResult = (Button)findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);
        tvResult = (TextView)findViewById(R.id.tv_result);
    }

    //code untuk perpindahan activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // pindah activity, intent explicit
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;

            // pindah activity membawa data
            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveWithDataIntent);
                break;

            // pindah activity dengan membawa object
            case R.id.btn_move_activity_object:
                Person mPerson = new Person();
                mPerson.setName("DicodingAcademy");
                mPerson.setAge(5);
                mPerson.setEmail("academy@dicoding.com");
                mPerson.setCity("Bandung");
                Intent moveWithObejctIntent = new Intent(MainActivity.this, MoveWithObjectActivity.class);
                moveWithObejctIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, mPerson);
                startActivity(moveWithObejctIntent);
                break;

            // memanggil intent implicit, dial number
            case R.id.btn_dial_number:
                String phoneNumber = "08562711435";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;

            // pindah activity dan membawa data dari activity selanjutnya
            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }
    }


    // Ketika MoveForResultActivity telah tertutup sempurna, maka metode onActivityResult() pada MainActivity akan dijalankan.
    // Di sinilah MainActivity akan merespon terhadap nilai balik yang dikirimkan oleh MoveForResultActivity.
    // Pada baris 4 di atas, dilakukan perbandingan apakah requestCode sama dengan yang dikirimkan oleh MainActivity.
    // Kemudian pada baris 5, diperiksa apakah nilai resultCode sama dengan resultCode yang dikirim oleh MoveForResultActivity.
    // Bila iya, maka data RadioButton yang dipilih akan ditampilkan di textview tvResult.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText("Hasil : "+selectedValue);
            }
        }
    }
}
