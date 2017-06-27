package net.pmsv.diadiemcaobang.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.pmsv.diadiemcaobang.DTO.GocChupDTO;
import net.pmsv.diadiemcaobang.DTO.HinhAnhDTO;
import net.pmsv.diadiemcaobang.R;

import java.util.List;

public class GocChupAdapter extends PagerAdapter {
    private int[] images = {R.drawable.ag_image_1,R.drawable.ag_image_2,R.drawable.ag_image_3,R.drawable.ag_image_4,R.drawable.ag_image_5,R.drawable.ag_image_6,R.drawable.ag_image_7,R.drawable.ag_image_8,R.drawable.ag_image_9,R.drawable.ag_image_10,R.drawable.ag_image_11,R.drawable.ag_image_12,R.drawable.ag_image_13};
    private Context ctx;
    private LayoutInflater inflater;
    private List<GocChupDTO> gocchupList;
    private List<HinhAnhDTO> hinhAnhList;
    public GocChupAdapter (Context mContext, List<GocChupDTO> gocchupList, List<HinhAnhDTO> hinhAnhList) {
        this.ctx = mContext;
        this.gocchupList = gocchupList;
        this.hinhAnhList=hinhAnhList;
    }
    public GocChupAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.swipe_layout,container,false);
        ImageView img =(ImageView)v.findViewById(R.id.image_view);
        TextView tv  = (TextView)v.findViewById(R.id.textViewGocChup);
        Log.d("countAAA",""+gocchupList.size());
        String imageFileName = hinhAnhList.get(position).getTenHinh();
        img.setBackground(getImage(imageFileName));
        tv.setText(gocchupList.get(position).getNoiDung());
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        container.refreshDrawableState();
    }
    Drawable getImage(String img)
    {
        Resources resources = ctx.getResources();
        int imgResId = resources.getIdentifier(String.valueOf(img), "drawable",ctx.getPackageName());
        Drawable dr;
        if(imgResId!=0) {
            dr = ctx.getResources().getDrawable(imgResId);
        }else
        {
            dr = ctx.getResources().getDrawable(R.drawable.no_image);
        }
        return dr;
    }
}