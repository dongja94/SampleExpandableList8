package com.example.dongja94.sampleexpandablelist;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class MyAdapter extends BaseExpandableListAdapter {
    List<GroupItem> items = new ArrayList<GroupItem>();

    public void add(String groupName, String child) {
        GroupItem g = null;
        for (GroupItem item : items) {
            if (item.groupName.equals(groupName)) {
                g = item;
                break;
            }
        }
        if (g == null) {
            g = new GroupItem();
            g.groupName = groupName;
            items.add(g);
        }

        if (child != null) {
            ChildItem item = new ChildItem();
            item.child = child;
            g.children.add(item);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return (long)groupPosition << 32 | 0xFFFFFFFF;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return (long)groupPosition << 32 | childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupItemView v;
        if (convertView != null) {
            v = (GroupItemView)convertView;
        } else {
            v = new GroupItemView(parent.getContext());
        }
        v.setGroupItem(items.get(groupPosition));
        v.setIsExpand(isExpanded);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildItemView v;
        if (convertView != null) {
            v = (ChildItemView)convertView;
        } else {
            v = new ChildItemView(parent.getContext());
        }
        v.setChildItem(items.get(groupPosition).children.get(childPosition));
        if (isLastChild) {
            v.setBackgroundColor(Color.YELLOW);
        } else {
            v.setBackgroundColor(Color.TRANSPARENT);
        }
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
