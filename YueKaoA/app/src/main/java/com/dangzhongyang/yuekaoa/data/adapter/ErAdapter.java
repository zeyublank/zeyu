package com.dangzhongyang.yuekaoa.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.dangzhongyang.yuekaoa.R;
import com.dangzhongyang.yuekaoa.data.beans.MBean;

import java.util.List;


public class ErAdapter extends BaseExpandableListAdapter {
    List<MBean.BodyBean.ResultBean> list;

    Context context;

    public ErAdapter(List<MBean.BodyBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.zu,null);
            holder=new GroupHolder();
            holder.name=convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else {
            holder= (GroupHolder) convertView.getTag();
        }
        holder.name.setText(list.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.lie,null);
            holder=new ChildHolder();
            holder.name=convertView.findViewById(R.id.name);
            holder.time=convertView.findViewById(R.id.time);
            holder.size=convertView.findViewById(R.id.size);
            convertView.setTag(holder);
        }else {
            holder= (ChildHolder) convertView.getTag();
        }
        holder.name.setText(list.get(groupPosition).getChildren().get(childPosition).getName());
        int partDuration = list.get(groupPosition).getChildren().get(childPosition).getPartDuration();
        int time = partDuration / 60;
        holder.time.setText(time+"分钟");
        holder.size.setText(list.get(groupPosition).getChildren().get(childPosition).getPartSize()+"M");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupHolder{
        TextView name;
    }
    class ChildHolder{
        TextView name,time,size;
    }
}
