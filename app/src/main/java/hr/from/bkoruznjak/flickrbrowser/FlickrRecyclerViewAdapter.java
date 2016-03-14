package hr.from.bkoruznjak.flickrbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Borna on 13.3.16.
 */
public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrImageViewholder> {

    private List<Photo> mPhotoList;
    private Context mContext;
    private final String LOG_TAG = "FlickerRecAdapter";

    public FlickrRecyclerViewAdapter(Context context, List<Photo> mPhotoList) {
        this.mContext = context;
        this.mPhotoList = mPhotoList;
    }

    @Override
    public FlickrImageViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, null);
        FlickrImageViewholder flickrImageViewholder = new FlickrImageViewholder(view);
        return flickrImageViewholder;
    }

    @Override
    public void onBindViewHolder(FlickrImageViewholder holder, int position) {
//important override, handles object updates on screen


        Photo photoItem = mPhotoList.get(position);
        Log.d(LOG_TAG, "Processing: " + photoItem.getmTitle() + " --> " + photoItem.getmAuthor());
        Picasso.with(mContext).load(photoItem.getmImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.title.setText(photoItem.getmTitle());

    }

    @Override
    public int getItemCount() {
        return (null != mPhotoList ? mPhotoList.size() : 0);
    }

    public void loadNewData(List<Photo> newPhotos) {
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return (null != mPhotoList ? mPhotoList.get(position) : null);
    }
}
