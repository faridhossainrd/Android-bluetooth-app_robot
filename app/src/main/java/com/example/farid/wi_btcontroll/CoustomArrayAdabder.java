package com.example.farid.wi_btcontroll;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CoustomArrayAdabder extends BaseAdapter {



    public ArrayList<createlist> demo=new ArrayList<createlist>();
    Context context;

    CoustomArrayAdabder(Context context)
    {

        this.context=context;
    }

    LayoutInflater inflater;

    @Override
    public int getCount() {
        return demo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.btd_layout,parent,false);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.name);
        TextView textView1=(TextView)convertView.findViewById(R.id.addr);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.circulimg);
        textView.setText(demo.get(position).getName());
        textView1.setText(demo.get(position).getAddress());
        imageView.setImageResource(R.drawable.ovil);
    //    imageView.getDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        return convertView;
    }
}
