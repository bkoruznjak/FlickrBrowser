package hr.from.bkoruznjak.flickrbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewPhotoDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_details);
        activateToolbarWithHomeEnabled();

        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        TextView photoTitle = (TextView) findViewById(R.id.photo_title);
        photoTitle.setText("Title: " + photo.getmTitle());

        TextView photoTags = (TextView) findViewById(R.id.photo_tags);
        photoTags.setText("Tags: " + photo.getmTags());

        TextView photoAuthor = (TextView) findViewById(R.id.photo_author);
        photoAuthor.setText("Author: " + photo.getmAuthor());

        ImageView photoImage = (ImageView) findViewById(R.id.photo_image);
        Picasso.with(this).load(photo.getmLink())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(photoImage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_details, menu);
        return true;
    }
}
