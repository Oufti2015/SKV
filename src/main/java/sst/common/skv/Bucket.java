package sst.common.skv;

public interface Bucket {

    public Bucket id(String bucketId);

    public String getId();

    public Bucket getBucket(String bucketId);

    public Entry getEntry(String key);

    public Bucket createBucket(String bucketId);

    public Entry addEntry(String key, String value);
}
