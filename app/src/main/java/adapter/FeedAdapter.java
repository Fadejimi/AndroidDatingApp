package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shixelsexcercise.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//import model.LoadImage;

/**
 * Created by Fadejimi on 2/24/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {
    private List<Integer> loadImageList;
    private Context context;
    private final static String TAG = FeedAdapter.class.getSimpleName();

    public FeedAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.loadImageList = imageList;
    }

    @Override
    public FeedAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_cardview,
                viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        int width = viewHolder.image_view.getWidth();
        Log.d(TAG, "Image width is " + width);
        System.out.println("Image width is " + width);
        Picasso.with(context).load(loadImageList.get(i)).resize(width, 150).into(viewHolder.image_view);
        //Picasso.with(context).load(loadImageList.get(i).getImageName()).resize(120,60).into(viewHolder.image_view);
    }

    @Override
    public int getItemCount() {
        return loadImageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view;
        ImageView love_image_view, message_image_view, share_image_view;
        TextView love_textview, message_textview, share_textview;

        public MyViewHolder(View view) {
            super(view);

            image_view = (ImageView) view.findViewById(R.id.image_view);

            love_image_view = (ImageView) view.findViewById(R.id.love_image_view);
            message_image_view = (ImageView) view.findViewById(R.id.message_view);
            share_image_view = (ImageView) view.findViewById(R.id.share_view);

            love_textview = (TextView) view.findViewById(R.id.love_textview);
            message_textview = (TextView) view.findViewById(R.id.message_textview);
            share_textview = (TextView) view.findViewById(R.id.share_textview);
        }
    }
}
