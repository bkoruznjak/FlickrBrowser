package hr.from.bkoruznjak.flickrbrowser;

import java.io.Serializable;

/**
 * Created by Borna on 13.3.16.
 */
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mLink;
    private String mTags;
    private String mImage;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmAuthorId() {
        return mAuthorId;
    }

    public String getmLink() {
        return mLink;
    }

    public String getmTags() {
        return mTags;
    }

    public String getmImage() {
        return mImage;
    }

    public Photo(String mTitle, String mAuthor, String mAuthorId, String mLink, String mTags, String mImage) {

        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mAuthorId = mAuthorId;
        this.mLink = mLink;
        this.mTags = mTags;
        this.mImage = mImage;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAuthorId='" + mAuthorId + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mTags='" + mTags + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
