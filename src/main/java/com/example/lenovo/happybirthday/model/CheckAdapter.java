package com.example.lenovo.happybirthday.model;

import java.util.List;

import com.example.lenovo.happybirthday.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckAdapter extends BaseAdapter {

    List<Teacher> list;//这是用来适配的列表数据
    Context context;

    //通过创建适配器时把列表传进来
    public CheckAdapter(List<Teacher> list, Context context) {
        // TODO Auto-generated constructor stub
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item, parent,false);
            //convertView = View.inflate(context, R.layout.activity_main, null);
            viewHolder = new ViewHolder();
            Teacher teacher = list.get(position);
            /* TextView username = (TextView)view.findViewById(R.id.username); */
            View temp = convertView.findViewById(R.id.usercname);
            viewHolder.usercname= (TextView) temp;

            // usercname.setsi(15);
            viewHolder.birthday = (TextView) convertView.findViewById(R.id.birthday);

            viewHolder.phone = (TextView) convertView.findViewById(R.id.phone);

            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.ckb);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String ss =list.get(position).getUsercname().toString();
        viewHolder.usercname.setText(list.get(position).getUsercname().toString());
        viewHolder.phone.setText(list.get(position).getPhone().toString());
        viewHolder.birthday.setText(list.get(position).getBirthday().toString());
        // 显示checkBox
        viewHolder.checkBox.setChecked(list.get(position).isChecked());

        return convertView;

    }

    //用来复用视图,当再次加载或者返回时,有缓存数据,所以会会提高速度
    public static class ViewHolder{
        public TextView usercname;
        public TextView phone;
        public TextView birthday;

        public CheckBox checkBox;

    }


}
