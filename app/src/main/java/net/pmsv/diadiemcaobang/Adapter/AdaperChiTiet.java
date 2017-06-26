package net.pmsv.diadiemcaobang.Adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.pmsv.diadiemcaobang.DTO.ChiTiet;
import net.pmsv.diadiemcaobang.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by May1 on 5/31/2017.
 */

public class AdaperChiTiet extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ChiTiet> chiTiets;

    public AdaperChiTiet(Context context, int layout, ArrayList<ChiTiet> chiTiets) {
        this.context = context;
        this.layout = layout;
        this.chiTiets = chiTiets;
    }

    @Override
    public int getCount() {
        return chiTiets.size();
    }

    @Override
    public Object getItem(int position) {
        return chiTiets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class Viewholder {
        TextView ten;
        TextView thongtin;
        ImageView hinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewholder = new Viewholder();
            viewholder.hinh = (ImageView) convertView.findViewById(R.id.imageViewChiTiet);
            viewholder.ten = (TextView) convertView.findViewById(R.id.txtTen);
            viewholder.thongtin = (TextView) convertView.findViewById(R.id.txtThongTin);

            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        ChiTiet ct = chiTiets.get(position);
        Log.e("MyTag", "Hinh" + ct.getHinh());
        Log.e("MyTag", "Ten" + ct.getTen());
        Log.e("MyTag", "All " + chiTiets.toString());
        int hinh;
        try {
            hinh = context.getResources().getIdentifier(ct.getHinh(), "drawable", context.getPackageName());
            viewholder.hinh.setImageResource(hinh);
            viewholder.ten.setText("Tên: " + ct.getTen());
                viewholder.thongtin.setText("Thông Tin: " + ct.getThongTin());
        } catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }
        return convertView;
    }
}
