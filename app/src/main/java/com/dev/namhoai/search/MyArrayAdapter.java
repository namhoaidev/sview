package com.dev.namhoai.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<String> {
    private static final String TAG = "MyArrayAdapter";
    private Context context;
    private int resource;
    private List<String> data;
    private List<String> dataOriginal;

    public MyArrayAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.data = objects;
        this.dataOriginal = new ArrayList<>(data);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ConvertUnicode convertUnicode = new ConvertUnicode();
                List<String> result = new ArrayList<>();
                for (String s : dataOriginal) {
                    if(convertUnicode.ConvertString(s.toUpperCase()).contains(
                            convertUnicode.ConvertString(constraint.toString().toUpperCase()))) {
                        result.add(s);
                    }
                }

                filterResults.values = result;
                filterResults.count = result.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {


                data.clear();
                data.addAll((ArrayList<String>)results.values);
                notifyDataSetChanged();


            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(resource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(data.get(position));

        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }

    //TODO: Khi filter với arrayadapter không được thay đổi vùng nhớ của mảng data được truyền vào constructor
}
