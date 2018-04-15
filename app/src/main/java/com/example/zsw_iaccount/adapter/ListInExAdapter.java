package com.example.zsw_iaccount.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.entity.Account;

import java.util.List;

/**
 * Created by 赵舒文 on 2018-4-15.
 */

public class ListInExAdapter extends BaseAdapter {
    private Context context ;
    private List<Account> list;
    public ListInExAdapter(Context context, List<Account> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            String type;
            String address;
            int pay;
            double money;
            type = list.get(position).getType();
            address = list.get(position).getSpaddress();
            pay = list.get(position).getPay();
            money = list.get(position).getMoney();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_inex_list, null);//实例化一个布局文件
            viewHolder = new ViewHolder();
            viewHolder.tv_type = (TextView)convertView.findViewById(R.id.item_inex_tv_type);
            viewHolder.tv_address = (TextView)convertView.findViewById(R.id.item_inex_tv_address);
            viewHolder.tv_pay = (TextView)convertView.findViewById(R.id.item_inex_tv_pay);
            viewHolder.tv_money = (TextView)convertView.findViewById(R.id.item_inex_tv_money);
            convertView.setTag(viewHolder);
            viewHolder.tv_type.setText(type);
            viewHolder.tv_address.setText(address);
            viewHolder.tv_pay.setText(String.valueOf(pay));
            viewHolder.tv_money.setText(String.valueOf(money));//将double数据放到textView中

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{
        TextView tv_type;
        TextView tv_address;
        TextView tv_pay;
        TextView tv_money;
    }
}
