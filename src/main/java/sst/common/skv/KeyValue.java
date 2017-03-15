package sst.common.skv;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import sst.common.skv.persistence.PersistenceProfile;
import sst.common.skv.persistence.Root;

@JsonSerialize
public class KeyValue implements Root, SimpleKeyValue {

    private static KeyValue instance = null;

    public static KeyValue me() {
	return instance;
    }

    static {
	instance = new KeyValue();
    }

    private KeyValue() {
    }

    protected Map<Context, Bucket> maps = new HashMap<>();

    @Override
    public Bucket bucket(Context context) {
	return maps.get(context);
    }

    @Override
    public Bucket bucket(String context) {
	return maps.get(new ContextImplementation().id(context));
    }

    @Override
    public Context context(String contextId) {
	Context context = new ContextImplementation().id(contextId);
	maps.put(context, new BucketImplementation().id("MAINBUCKET"));
	return context;
    }

    private List<PersistenceProfile> profiles = new ArrayList<>();

    @Override
    public void addPersistenceProfile(PersistenceProfile profile) {
	profiles.add(profile);
    }

    public void load(File file) {
    }

    @Override
    public Object data() {
	return maps;
    }

    @Override
    public List<String> text() {
	List<String> result = new ArrayList<>();
	for (Context context : maps.keySet()) {
	    System.out.println("text() : " + context);
	    Bucket bucket = bucket(context);
	    System.out.println("text() : " + bucket);
	    result.addAll(bucketText(bucket, context.id() + "::"));
	}
	return result;
    }

    private List<String> bucketText(Bucket bucket, String bucketId) {
	List<String> result = new ArrayList<>();
	for (Entry entry : bucket.entries()) {
	    System.out.println("text() : " + entry);
	    result.add(bucketId + "/" + bucket.id() + "::" + entry.key() + "=" + entry.value());
	}
	for (Bucket subBucket : bucket.buckets()) {
	    result.addAll(bucketText(subBucket, bucketId + "/" + bucket.id()));
	}
	return result;
    }
}
