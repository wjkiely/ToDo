package com.williamkiely.todo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity implements OnClickListener {

    EditText txtItem;
    Button btnAdd;
    ListView listItems;

    ArrayList<String> toDoItems;
    ArrayAdapter<String> aa;


    // Called when the activity is first created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtItem = (EditText) findViewById(R.id.txtItem);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        listItems = (ListView) findViewById(R.id.listItems);

        btnAdd.setOnClickListener(this);
        txtItem.setOnClickListener(this);

        toDoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoItems);
        listItems.setAdapter(aa);

    }

    private void addItem(String item) {
        if (item.length() > 0) {
            this.toDoItems.add(item);
            this.aa.notifyDataSetChanged();
            this.txtItem.setText("");
        }
    }

    public void onClick(View v) {
        if (v == this.btnAdd) {
            this.addItem(this.txtItem.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
