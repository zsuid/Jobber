package calc.aca.android.zidansuid.com.jobber;

/**
 * Created by zidansuid on 11/2/16.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerActivity extends ListActivity  implements android.view.View.OnClickListener{

    Button btnAdd,btnGetAll;
    TextView customer_Id;
    EditText mSearchInput;
    ListAdapter mListAdapter;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,CustomerDetail.class);
            intent.putExtra("customer_Id",0);
            startActivity(intent);

        }else {

            CustomerRepo repo = new CustomerRepo(this);

            ArrayList<HashMap<String, String>> customerList =  repo.getCustomerList();
            if(customerList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        customer_Id = (TextView) view.findViewById(R.id.customer_Id);
                        String customerId = customer_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),CustomerDetail.class);
                        objIndent.putExtra("customer_Id", Integer.parseInt( customerId));
                        startActivity(objIndent);
                    }
                });
                mListAdapter = new SimpleAdapter( CustomerActivity.this,customerList, R.layout.view_customer_entry, new String[] { "id","name"}, new int[] {R.id.customer_Id, R.id.customer_name});
                setListAdapter(mListAdapter);
            }else{
                Toast.makeText(this,"No Customer!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        mSearchInput = (EditText) findViewById(R.id.inputSearch);

        mSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ((SimpleAdapter)CustomerActivity.this.mListAdapter).getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



}