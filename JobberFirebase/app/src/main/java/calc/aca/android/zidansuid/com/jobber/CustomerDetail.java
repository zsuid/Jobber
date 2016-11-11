package calc.aca.android.zidansuid.com.jobber;

/**
 * Created by zidansuid on 11/2/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerDetail extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editCustName;
    EditText editCustBusiness;
    EditText editAddress1;
    EditText editAddress2;
    EditText editPhone;
    EditText editEmail;

    private int _Customer_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editCustName = (EditText) findViewById(R.id.editCustName);
        editCustBusiness = (EditText) findViewById(R.id.editCustBusiness);
        editAddress1 = (EditText) findViewById(R.id.editAddress1);
        editAddress2 = (EditText) findViewById(R.id.editAddress2);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _Customer_Id =0;
        Intent intent = getIntent();
        _Customer_Id =intent.getIntExtra("customer_Id", 0);
        CustomerRepo repo = new CustomerRepo(this);
        Customer customer = new Customer();
        customer = repo.getCustomerById(_Customer_Id);

        editCustName.setText(customer.name);
        editCustBusiness.setText(customer.business);
        editAddress1.setText(customer.address1);
        editAddress2.setText(customer.address2);
        editPhone.setText(customer.phone);
        editEmail.setText(customer.email);
    }



    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            CustomerRepo repo = new CustomerRepo(this);
            Customer customer = new Customer();
            customer.name = editCustName.getText().toString();
            customer.business = editCustBusiness.getText().toString();
            customer.address1 = editAddress1.getText().toString();
            customer.address2 = editAddress2.getText().toString();
            customer.phone = editPhone.getText().toString();
            customer.email = editEmail.getText().toString();
            customer.customer_ID = _Customer_Id;

            if (_Customer_Id==0){
                _Customer_Id = repo.insert(customer);

                Toast.makeText(this,"New Customer Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(customer);
                Toast.makeText(this,"Customer Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            CustomerRepo repo = new CustomerRepo(this);
            repo.delete(_Customer_Id);
            Toast.makeText(this, "Customer Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}
