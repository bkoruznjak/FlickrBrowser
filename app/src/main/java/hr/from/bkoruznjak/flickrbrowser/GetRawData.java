package hr.from.bkoruznjak.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Borna on 13.3.16.
 */

enum DownloadStatus {
    IDLE, PROCESSING, NOT_INTIALISED, FAILED_OR_EMPTY, OK
}

public class GetRawData {
    private String LOG_TAG = GetRawData.class.getSimpleName();
    private String mRawUrl;
    private String mData;
    private DownloadStatus mDownloadStatus;

    public GetRawData(String mRawUrl) {
        this.mRawUrl = mRawUrl;
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    public void reset() {
        this.mDownloadStatus = DownloadStatus.IDLE;
        this.mRawUrl = null;
        this.mData = null;
    }

    public void setmRawUrl(String mRawUrl) {
        this.mRawUrl = mRawUrl;
    }

    public String getmRawUrl() {
        return mRawUrl;
    }

    public String getmData() {
        return mData;
    }

    public DownloadStatus getmDownloadStatus() {
        return mDownloadStatus;
    }

    public void execute(){
        mDownloadStatus = DownloadStatus.PROCESSING;
        DownloadRawData downloadRawData = new DownloadRawData();
        downloadRawData.execute(mRawUrl);
    }

    public class DownloadRawData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String webData) {
            //TODO: Fill in later
            mData = webData;
            Log.v(LOG_TAG, "data returned was: " + webData);
            if(mData == null) {
                if(mRawUrl == null){
                    mDownloadStatus = DownloadStatus.NOT_INTIALISED;
                } else {
                    mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
                }
            } else {
                //success
                mDownloadStatus = DownloadStatus.OK;
            }
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if (params == null)
                return null;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (IOException ioEx) {

            } finally{
                if (urlConnection != null) {
                    Log.d(LOG_TAG, "disconnecting connection!");
                    urlConnection.disconnect();
                }

                if(reader!= null){
                    try {
                        reader.close();
                    } catch(IOException ioEx){
                        Log.d(LOG_TAG, "exception while closing: " + ioEx.toString());
                    }
                }

            }
            return null;
        }
    }
}
