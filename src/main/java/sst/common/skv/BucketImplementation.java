
package sst.common.skv;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

@ToString
public class BucketImplementation implements Bucket {

    private String id;
    private Map<String, Bucket> buckets = new HashMap<>();
    private Map<String, Entry> entries = new HashMap<>();

    BucketImplementation() {
    }

    @Override
    public Bucket bucket(String bucketId) {
	return buckets.get(bucketId);
    }

    @Override
    public Entry entry(String key) {
	return entries.get(key);
    }

    @Override
    public Bucket newBucket(String bucketId) {
	Bucket bucket = new BucketImplementation().id(bucketId);
	buckets.put(bucket.id(), bucket);
	return bucket;
    }

    @Override
    public Entry newEntry(String key, String value) {
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
    public String id() {
	return id;
    }

    @Override
    public Collection<Entry> entries() {
	return entries.values();
    }

    @Override
    public Collection<Bucket> buckets() {
	return buckets.values();
    }
}
