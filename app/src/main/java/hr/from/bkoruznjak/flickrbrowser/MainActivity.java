package hr.from.bkoruznjak.flickrbrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = "MainActivity";
    private List<Photo> mPhotoList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FlickrRecyclerViewAdapter flickrRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateToolbar();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        flickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(MainActivity.this, new ArrayList<Photo>());
        mRecyclerView.setAdapter(flickrRecyclerViewAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Normal tap", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ViewPhotoDetailsActivity.class);
                intent.putExtra(PHOTO_TRANSFER, flickrRecyclerViewAdapter.getPhoto(position));
//                Toast.makeText(MainActivity.this, "Long tap", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume called");
        String query = getSavedPreferenceData(FLICKR_QUERY);
        if (query.length() > 0) {
            Log.d(LOG_TAG, "processing photos for query:" + query);
            ProcessPhotos processPhotos = new ProcessPhotos(query, true);
            processPhotos.execute();
        }
    }

    private String getSavedPreferenceData(String flickrQuery) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getString(flickrQuery, "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.menu_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ProcessPhotos extends GetFlickerJsonData {
        public ProcessPhotos(String searchCriteria, boolean matchAll) {
            super(searchCriteria, matchAll);
        }

        public void execute() {
            super.execute();
            ProcessData processData = new ProcessData();
            processData.execute();
        }

        public class ProcessData extends DownloadJsonData {

            protected void onPostExecute(String webData) {
                super.onPostExecute(webData);
                flickrRecyclerViewAdapter.loadNewData(getPhotos());

            }
        }
    }
}
