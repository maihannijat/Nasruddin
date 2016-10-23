package com.revittechnology.mullahnasruddin.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.revittechnology.mullahnasruddin.R;
import com.revittechnology.mullahnasruddin.model.Joke;

public class ItemListBaseAdapter extends BaseAdapter {

    Context contex;
    private static List<Joke> itemDetailsrrayList;

    private LayoutInflater l_Inflater;

    public ItemListBaseAdapter(Context context, List<Joke> results) {
        this.contex = context;
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itemDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return itemDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txt_joke = (TextView) convertView.findViewById(R.id.text_joke_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_joke.setText(itemDetailsrrayList.get(position).getTitle());
        return convertView;
    }

    static class ViewHolder {
        TextView txt_joke;
    }
}
