package com.example.noce30.danhbadienthoai.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noce30.danhbadienthoai.R;
import com.example.noce30.danhbadienthoai.model.Contact;

import java.lang.reflect.Array;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private List<Contact> arrayContact;
    public CustomAdapter( Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrayContact=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_contact_listview,parent,false);
            viewHolder.imgAvartarContact=(ImageView) convertView.findViewById(R.id.img_avatar);
            viewHolder.tvName=(TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvNumber=(TextView) convertView.findViewById(R.id.tv_number);

             convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact= arrayContact.get(position);
        viewHolder.tvName.setText(contact.getmName());
        viewHolder.tvNumber.setText(contact.getmNumber());

        if(contact.isMale())
        {
            viewHolder.imgAvartarContact.setBackgroundResource(R.drawable.male);
        }
        else
        {
            viewHolder.imgAvartarContact.setBackgroundResource(R.drawable.female);
        }
        return convertView;
    }
    public class ViewHolder{
        ImageView imgAvartarContact;
        TextView tvName;
        TextView tvNumber;
    }
}
