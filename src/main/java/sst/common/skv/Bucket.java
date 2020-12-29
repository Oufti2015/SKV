package sst.common.skv;

import java.util.Collection;

public interface Bucket {

    Bucket id(String bucketId);

    String id();

    Bucket bucket(String bucketId);

    Entry entry(String key);

    Bucket newBucket(String bucketId);

    Entry newEntry(String key, String value);

    Collection<Entry> entries();

    Collection<Bucket> buckets();
}
