package com.williamkiely.todo;


        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
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
public View getView(final int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        final TextView name = (TextView) convertView.findViewById(R.id.textView1);
        final CheckBox box = (CheckBox) convertView.findViewById(R.id.checkBox1);

        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    modelItems.set(position, new Model(modelItems.get(position).getName(), 1));
                }
                else {
                    modelItems.set(position, new Model(modelItems.get(position).getName(), 0));
                }
                mainActivity.writeToDoItems();
            }
        });

        name.setText(modelItems.get(position).getName());
        if(modelItems.get(position).getValue() == 1)
        cb.setChecked(true);
        else
        cb.setChecked(false);

        RadioGroup categoryGroup = (RadioGroup) convertView.findViewById(R.id.category_group);
        categoryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioButton:
                        // 'Incident' checked
                        mainActivity.modelItems.remove(position);
                        mainActivity.adapter.notifyDataSetChanged();
                        mainActivity.writeToDoItems();
                        break;
                }
            }
        });
        return convertView;
        }
 }