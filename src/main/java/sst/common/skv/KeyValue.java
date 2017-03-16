package sst.common.skv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyValue implements SimpleKeyValue {

    public KeyValue() {
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

    @Override
    public List<String> text() {
	List<String> result = new ArrayList<>();
	for (Context context : maps.keySet()) {
	    Bucket bucket = bucket(context);
	    result.addAll(bucketText(bucket, context.id() + "::"));
	}
	return result;
    }

    private List<String> bucketText(Bucket bucket, String bucketId) {
	List<String> result = new ArrayList<>();
	for (Entry entry : bucket.entries()) {
	    result.add(bucketId + "/" + bucket.id() + "::" + entry.key() + "=" + entry.value());
	}
	for (Bucket subBucket : bucket.buckets()) {
	    result.addAll(bucketText(subBucket, bucketId + "/" + bucket.id()));
	}
	return result;
    }
}
