package com.williamkiely.todo;


        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.TextView;

        import java.util.List;

public class CustomAdapter extends ArrayAdapter<Model>{
    private final MainActivity mainActivity;
    List<Model> modelItems = null;
        Context context;
public CustomAdapter(MainActivity mainActivity, List<Model> resource) {
        super(mainActivity,R.layout.row,resource);
        this.mainActivity = mainActivity;
// TODO Auto-generated constructor stub
        this.context = mainActivity;
        this.modelItems = resource;
        }
@Override
public View getView(int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mainActivity.writeToDoItems();
            }
        });
        name.setText(modelItems.get(position).getName());
        if(modelItems.get(position).getValue() == 1)
        cb.setChecked(true);
        else
        cb.setChecked(false);
        return convertView;
        }
        }