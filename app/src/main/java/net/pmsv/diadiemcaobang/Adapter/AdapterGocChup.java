package net.pmsv.diadiemcaobang.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.pmsv.diadiemcaobang.DTO.DiaDiemDTO;
import net.pmsv.diadiemcaobang.DTO.GocChupDTO;
import net.pmsv.diadiemcaobang.DTO.HinhAnhDTO;
import net.pmsv.diadiemcaobang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 20/06/2017.
 */

public class AdapterGocChup extends PagerAdapter{

    //private int[] image = {R.drawable.cb01_1,R.drawable.cb01_2,R.drawable.cb01_3,R.drawable.cb02_1,};

    private Context context;
    private List<HinhAnhDTO> hinhAnhlist = new ArrayList<>();
    private List<GocChupDTO> gocChuplist = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public AdapterGocChup(Context context,List<HinhAnhDTO> hinhAnhlist,List<GocChupDTO> gocChuplist){
        this.context = context;
        this.layoutInflater = (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.hinhAnhlist = hinhAnhlist;
        this.gocChuplist = gocChuplist;
    }

    @Override
    public int getCount() {
        //return image.length;
        return hinhAnhlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        TextView textView = (TextView) item_view.findViewById(R.id.textViewGocChup);

        //goc chup
//        imageView.setImageResource(image[position]);
//        textView.setText("image :" +position);
        imageView.setImageResource(this.hinhAnhlist.get(position).getIdGocChup());
        textView.setText(this.gocChuplist.get(position).getDiaDiem());

        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
