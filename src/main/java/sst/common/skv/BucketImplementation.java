package sst.common.skv;

import java.util.HashMap;
import java.util.Map;

public class BucketImplementation implements Bucket {

    private String id;
    private Map<String, Bucket> buckets = new HashMap<>();
    private Map<String, Entry> entries = new HashMap<>();

    BucketImplementation() {
    }

    @Override
    public Bucket getBucket(String bucketId) {
	return buckets.get(bucketId);
    }

    @Override
    public Entry getEntry(String key) {
	return entries.get(key);
    }

    @Override
    public Bucket createBucket(String bucketId) {
	Bucket bucket = new BucketImplementation().id(bucketId);
	buckets.put(bucket.getId(), bucket);
	return bucket;
    }

    @Override
    public Entry addEntry(String key, String value) {
	Entry entry = new EntryImplementation().key(key).value(value);
	entries.put(entry.key(), entry);
	return entry;
    }

    @Override
    public Bucket id(String bucketId) {
	this.id = bucketId;
	return this;
    }

    @Override
    public String getId() {
	return id;
    }
}
