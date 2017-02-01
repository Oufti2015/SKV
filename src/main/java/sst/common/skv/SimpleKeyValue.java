package sst.common.skv;

import java.util.HashMap;
import java.util.Map;

public class SimpleKeyValue {

    private static SimpleKeyValue instance = null;

    public static SimpleKeyValue me() {
	return instance;
    }

    static {
	instance = new SimpleKeyValue();
    }

    private SimpleKeyValue() {
    }

    private Map<Context, Bucket> maps = new HashMap<>();

    public Bucket getBucket(Context context) {
	return maps.get(context);
    }

    public Bucket getBucket(String context) {
	return maps.get(new ContextImplementation().id(context));
    }

    public Context createContext(String contextId) {
	Context context = new ContextImplementation().id(contextId);
	maps.put(context, new BucketImplementation().id("MAINBUCKET"));
	return context;
    }
}
