package com.example.k.diadiembinhphuoc.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.k.diadiembinhphuoc.DTO.DiaDiemDTO;
import com.example.k.diadiembinhphuoc.GocChupActivity;
import com.example.k.diadiembinhphuoc.R;

import java.util.List;


/**
 * Created by may38 on 5/29/2017.
 */

public class AdapterDiaDiem extends RecyclerView.Adapter<AdapterDiaDiem.ViewHolder> {

    private Context mContext;
    private List<DiaDiemDTO> diaDiemList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tenDiaDiem, gocChup;
        public ImageView hinh, overflow;

        public ViewHolder(View view) {
            super(view);
            tenDiaDiem = (TextView) view.findViewById(R.id.title);
            gocChup = (TextView) view.findViewById(R.id.count);
            hinh = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    public AdapterDiaDiem(Context mContext, List<DiaDiemDTO> diaDiemList) {
        this.mContext = mContext;
        this.diaDiemList = diaDiemList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dia_diem_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DiaDiemDTO diaDiemDTO = diaDiemList.get(position);
        holder.tenDiaDiem.setText(diaDiemDTO.getTen());
        //holder.gocChup.setBackground(Drawable.createFromPath("R.drawable" + diaDiemDTO.getHinhdaidien()));
        if (diaDiemDTO.getSogocchup() <= 0) {
            holder.gocChup.setText("Chưa có góc chụp");
        }else{
            holder.gocChup.setText("Có "+diaDiemDTO.getSogocchup() + " góc chụp");
        }

        Resources resources = mContext.getResources();
        int resID = resources.getIdentifier(diaDiemDTO.getHinhdaidien(), "drawable", mContext.getPackageName());
        // loading album cover using Glide library
        Glide.with(mContext).load(resID).into(holder.hinh);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow, diaDiemDTO);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, DiaDiemDTO diaDiemDTO) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new AdapterDiaDiem.MyMenuItemClickListener(diaDiemDTO));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private DiaDiemDTO diaDiemDTO;

        public MyMenuItemClickListener(DiaDiemDTO diaDiemDTO) {
            this.diaDiemDTO = diaDiemDTO;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            DialogThongTin(diaDiemDTO, menuItem.getItemId());
            return false;
        }
    }

    private void DialogThongTin(DiaDiemDTO diadiem, int id) {
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_thong_tin);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        TextView txtTitle = (TextView) dialog.findViewById(R.id.textViewTitle);
        TextView txtThongTin = (TextView) dialog.findViewById(R.id.textViewThongTin);
        txtThongTin.setMovementMethod(new ScrollingMovementMethod());
        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        switch (id) {
            case R.id.thongtin:
                txtTitle.setText(R.string.thong_tin);
                txtThongTin.setText(diadiem.getThongtin());
                break;
            case R.id.thoidiem:
                txtTitle.setText(R.string.thoi_diem);
                txtThongTin.setText(diadiem.getThoidiem());
                break;
            case R.id.duongdi:
                txtTitle.setText(R.string.duong_di);
                txtThongTin.setText(diadiem.getDuongdi());
                break;
            case R.id.gochup:
                Intent intent = new Intent(dialog.getContext(), GocChupActivity.class);
                //Toast.makeText(dialog.getContext(),"A",Toast.LENGTH_SHORT).show();
                intent.putExtra("data", diadiem);
                dialog.getContext().startActivity(intent);
                return;
            case R.id.machnho:
                txtTitle.setText(R.string.mach_nho);
                txtThongTin.setText(diadiem.getMachnho());
                break;
            default:
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return diaDiemList.size();
    }


}
