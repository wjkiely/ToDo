package com.williamkiely.todo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener {

    final String FILENAME = "goal_list_file";


    ListView lv;
    List<Model> modelItems;
    EditText txtItem;
    Button btnAdd;
    ListView listItems;

    CustomAdapter adapter;


    // Called when the activity is first created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView1);
        modelItems = new ArrayList<Model>(0);
        adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);

        txtItem = (EditText) findViewById(R.id.txtItem);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        listItems = (ListView) findViewById(R.id.listItems);

        btnAdd.setOnClickListener(this);
        txtItem.setOnClickListener(this);

    }

    private void addItem(String item) {
        if (item.length() > 0) {
            this.modelItems.add(new Model(item, 0));
            this.txtItem.setText("");
            this.txtItem.setHint("Add another goal");
            this.adapter.notifyDataSetChanged();
        }
    }

    public void onClick(View v) {
        if (v == this.btnAdd) {
            this.addItem(this.txtItem.getText().toString());
        }
    }

    private void writeToDoItems(ArrayList<String> newToDoItems) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newToDoItems);
            oos.close();
            fos.close();
        } catch (java.io.IOException e1) {
            Log.e("DATA", "I/O Exception", e1);
            e1.printStackTrace();
        }
    }

    private ArrayList<String> readToDoItems() throws ClassNotFoundException, IOException {
        ArrayList<String> newToDos = new ArrayList<String>();
        FileInputStream fis = openFileInput(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        newToDos = (ArrayList<String>) ois.readObject();
        fis.close();
        ois.close();
        return newToDos;
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
