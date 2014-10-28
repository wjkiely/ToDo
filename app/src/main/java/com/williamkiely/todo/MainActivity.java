package com.williamkiely.todo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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

    CustomAdapter adapter;


    // Called when the activity is first created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView1);
        modelItems = new ArrayList<Model>(0);
        try {
            modelItems = readToDoItems();
        } catch (ClassNotFoundException e) {
            Log.e("DATA", "ClassNotFoundException", e);
            // TODO make this right
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("DATA", "IOException", e);
            // TODO make this right
            e.printStackTrace();
        }
        adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);

        txtItem = (EditText) findViewById(R.id.txtItem);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
        txtItem.setOnClickListener(this);

    }

    private void addItem(String item) {
        if (item.length() > 0) {
            this.modelItems.add(new Model(item, 0));
            writeToDoItems(modelItems);
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

    void writeToDoItems() {
        this.writeToDoItems(modelItems);
    }

    private void writeToDoItems(List<Model> newToDoItems) {
        ArrayList<Model> modelToSave;
        if (newToDoItems instanceof ArrayList) {
            modelToSave = (ArrayList<Model>) newToDoItems;
        } else {
            modelToSave = new ArrayList<Model>(newToDoItems);
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(modelToSave);
            oos.close();
            fos.close();
        } catch (java.io.IOException e1) {
            Log.e("DATA", "I/O Exception", e1);
            e1.printStackTrace();
        }
    }

    private List<Model> readToDoItems() throws ClassNotFoundException, IOException {
        List<Model> newToDos = new ArrayList<Model>();
        FileInputStream fis = openFileInput(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        newToDos = (ArrayList<Model>) ois.readObject();
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
