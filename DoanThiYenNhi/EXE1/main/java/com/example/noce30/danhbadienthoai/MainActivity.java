package com.example.noce30.danhbadienthoai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.noce30.danhbadienthoai.Adapter.CustomAdapter;
import com.example.noce30.danhbadienthoai.model.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> arrayContact;
    private CustomAdapter adapter;

    private EditText edtName;
    private EditText edtNumber;
    private RadioButton radMale;
    private RadioButton radFemale;
    private Button btnAdd;
    private ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidget();
        arrayContact= new ArrayList<>();
        adapter = new CustomAdapter(this,R.layout.item_contact_listview,arrayContact);
        lvContact.setAdapter(adapter);

    }
    public void setWidget()
    {
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_number);
        radMale = (RadioButton) findViewById(R.id.rad_male);
        radFemale = (RadioButton) findViewById(R.id.rad_female);
        btnAdd=(Button) findViewById(R.id.btn_add);
        lvContact=(ListView) findViewById(R.id.lv_contact);
    }
    public void addContact(View view)
    {
        if(view.getId()==R.id.btn_add)
        {
            String name=edtName.getText().toString().trim();
            String number=edtNumber.getText().toString().trim();
            boolean isMale= true;
            if(radMale.isChecked())
            {
                isMale=true;
            }
            else
            {
                isMale=false;
            }
            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(number))
            {
                Toast.makeText(this, "Vui long nhap thong tin",Toast.LENGTH_SHORT).show();
            }
            else {
                Contact contact = new Contact(isMale,name,number);
                arrayContact.add(contact);
            }
            adapter.notifyDataSetChanged();

        }
    }
}
