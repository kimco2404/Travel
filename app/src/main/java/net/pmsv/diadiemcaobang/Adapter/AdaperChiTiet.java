package net.pmsv.diadiemcaobang.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.pmsv.diadiemcaobang.DTO.ChiTiet;
import net.pmsv.diadiemcaobang.R;

import java.util.List;

/**
 * Created by May1 on 5/31/2017.
 */

public class AdaperChiTiet extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ChiTiet> chiTiets;

    public AdaperChiTiet(Context context, int layout, List<ChiTiet> chiTiets) {
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
        return chiTiets.get(position).getId();
    }

    private class Viewholder {
        TextView ten;
        ImageView hinh;
        TextView thongtin;
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
        int hinh;
        try {
            ChiTiet ct = chiTiets.get(position);
            Log.e("MyTag", "Hinh" + ct.getHinh());
            Log.e("MyTag", "Ten" + ct.getTen());
            Log.e("MyTag", "All " + chiTiets.toString());

            viewholder.hinh.setImageResource(ct.getHinh());
            viewholder.ten.setText("Ten: " +  ct.getTen());
            viewholder.thongtin.setText("Thông Tin: " + ct.getThongTin());

        } catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }
        return convertView;
    }
}
