package com.example.jkost_android;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransaksiActivity extends Activity {

    private EditText editTextNIK ,editTextName, editTextPhone, editTextAlamat, editTextDate1, editTextDate2;
    private Button buttonSubmit;
    private AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2;
    ArrayAdapter<String> adapter,adapter2;
    String[] suggestions = {"P", "L"};
    String[] suggestions2 = {"1 bulan", "2 bulan", "3 bulan", "4 bulan", "5 bulan", "6 bulan", "7 bulan" ,
            "8 bulan", "9 bulan", "10 bulan", "11 bulan", "12 bulan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        editTextNIK = findViewById(R.id.editTextNIK);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextDate1 = findViewById(R.id.editTextDate1);
        editTextDate2 = findViewById(R.id.editTextDate2);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        adapter = new ArrayAdapter<String>(this,R.layout.list_dropdown, suggestions);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView2 = findViewById(R.id.autoCompleteTextView2);
        adapter2 = new ArrayAdapter<String>(this,R.layout.list_dropdown, suggestions2);
        autoCompleteTextView2.setAdapter(adapter2);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NIK = editTextNIK.getText().toString();
                String name = editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();
                String Alamat = editTextAlamat.getText().toString();
                String date1 = editTextDate1.getText().toString();
                String date2 = editTextDate2.getText().toString();


                if ( !NIK.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !date1.isEmpty() && !date2.isEmpty() && !Alamat.isEmpty()) {
                    // Lakukan proses pemesanan kos atau operasi lain sesuai kebutuhan Anda
                    // ...

                    Toast.makeText(TransaksiActivity.this, "Pemesanan berhasil: " + name, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TransaksiActivity.this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String adapter = adapterView.getItemAtPosition(i).toString();
            }
        });

        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String adapter2 = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
}