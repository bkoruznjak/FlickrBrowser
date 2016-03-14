package hr.from.bkoruznjak.flickrbrowser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Borna on 13.3.16.
 */
public class FlickrImageViewholder extends RecyclerView.ViewHolder{

    protected ImageView thumbnail;
    protected TextView title;

    public FlickrImageViewholder(View view) {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        this.title = (TextView)view.findViewById(R.id.title);
    }
}
