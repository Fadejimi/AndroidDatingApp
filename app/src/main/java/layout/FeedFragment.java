package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shixelsexcercise.R;

import java.util.ArrayList;
import java.util.List;

import adapter.FeedAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    /*private final String android_image_urls[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };*/

    private final int android_cars[] = {
            R.drawable.car1,
            R.drawable.car2,
            R.drawable.car3,
            R.drawable.car4,
            R.drawable.car5,
            R.drawable.car6,
            R.drawable.car7
    };

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.feed_recycler_view);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);

        List<Integer> loadImageList = prepareData();
        FeedAdapter adapter = new FeedAdapter(getContext(), loadImageList);
        rv.setAdapter(adapter);
        return view;
    }

    private List<Integer> prepareData() {
        List<Integer> result = new ArrayList<>();

        for (int i : android_cars) {
            result.add(i);
        }

        return result;
    }
}
