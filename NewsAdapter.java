package com.example.disastermanagementfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class NewsAdapter extends PagerAdapter {

    List<Alldetailsinfo> details;
    Context context;
    @Override
    public int getCount() {
        return details.size();
    }

    public NewsAdapter(List<Alldetailsinfo> details,Context context) {
          this.context=context;
          this.details=details;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater=LayoutInflater.from(this.context);
        View v=layoutInflater.inflate(R.layout.card,container,false);
        ImageView image=v.findViewById(R.id.imageid);
        TextView title=v.findViewById(R.id.titleid);
        TextView desc=v.findViewById(R.id.descid);
        image.setImageResource(this.details.get(position).getImages());
        title.setText(this.details.get(position).getTitle());
        desc.setText(this.details.get(position).getDescription());
        container.addView(v,0);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
