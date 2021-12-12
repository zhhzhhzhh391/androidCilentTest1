package com.test.natsuyasumi.testdemo21;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class textShowAdapter extends BaseAdapter {
    private Context context;
    private List<dialogText> textList;
    public String road = "0";

    public textShowAdapter(Context context, List<dialogText> textList){
            this.context = context;
            this.textList = textList;
    }

    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return textList.size();
    }

    @Override
    public Object getItem(int position) {
        return textList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView dialog;
        Button buttonA;
        Button buttonB;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
          ViewHolder mHolder;
          if(convertView == null){
              if(textList.get(position).getType().equals("dialog")){
                  convertView = LayoutInflater.from(context).inflate(R.layout.adapter_text,null);
                  mHolder = new ViewHolder();
                  mHolder.dialog = (TextView)convertView.findViewById(R.id.dialog);
                  mHolder.dialog.setText(textList.get(position).getText());
                  convertView.setTag(mHolder);
              }else if(textList.get(position).getType().equals("button")){
                  convertView = LayoutInflater.from(context).inflate(R.layout.adapter_button,null);
                  mHolder = new ViewHolder();
                  int newIdA = Integer.parseInt(textList.get(position).getButtonAId());
                  int newIdB = Integer.parseInt(textList.get(position).getButtonBId());
                  mHolder.buttonA = (Button)convertView.findViewById(R.id.buttonA);
                  mHolder.buttonB = (Button)convertView.findViewById(R.id.buttonB);

                  mHolder.buttonA.setId(newIdA);
                  mHolder.buttonB.setId(newIdB);

                  mHolder.buttonA.setText(textList.get(position).getTextA());
                  mHolder.buttonB.setText(textList.get(position).getTextB());

                  convertView.setTag(mHolder);
              }else if(textList.get(position).getType().equals("threadStop")) {
                  convertView = LayoutInflater.from(context).inflate(R.layout.adapter_border,null);
              }
          } else{
              mHolder = (ViewHolder)convertView.getTag();
          }
        return convertView;
    }


}
