package sst.common.skv;

import java.util.Collection;

public interface Bucket {

    public Bucket id(String bucketId);

    public String id();

    public Bucket bucket(String bucketId);

    public Entry entry(String key);

    public Bucket newBucket(String bucketId);

    public Entry newEntry(String key, String value);

    public Collection<Entry> entries();

    public Collection<Bucket> buckets();
}
