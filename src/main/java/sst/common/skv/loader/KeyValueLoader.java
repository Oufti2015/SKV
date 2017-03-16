package sst.common.skv.loader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import sst.common.skv.Bucket;
import sst.common.skv.Context;
import sst.common.skv.KeyValue;
import sst.common.skv.SimpleKeyValue;
import sst.textfile.InputTextFile;
import sst.textfile.InputTextFileImpl;

public class KeyValueLoader {

    public static SimpleKeyValue keyValue(File file) throws IOException {
	SimpleKeyValue skv = new KeyValue();
	InputTextFile textFile = new InputTextFileImpl(file);
	KeyValueLoader.parse(skv, textFile.lines());
	return skv;
    }

    private static void parse(SimpleKeyValue skv, List<String> lines) {
	for (String line : lines) {
	    String[] array = line.split("::", 3);

	    String contextId = array[0];
	    String bucketId = array[1];
	    String keyValue = array[2];

	    Bucket mainBucket = skv.bucket(contextId);
	    if (mainBucket == null) {
		Context context = skv.context(contextId);
		mainBucket = skv.bucket(context);
	    }

	    Bucket bucket = mainBucket;
	    String[] bucketIds = bucketId.split("/");
	    for (String id : bucketIds) {
		if (id.length() > 0 && !id.equals(mainBucket.id())) {
		    Bucket bucket2 = bucket.bucket(id);
		    if (bucket2 == null) {
			bucket2 = bucket.newBucket(id);
		    }
		    bucket = bucket2;
		}
	    }

	    String[] keyValues = keyValue.split("=", 2);
	    bucket.newEntry(keyValues[0], keyValues[1]);
	}

    }
}
