package sst.common.skv.test;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import sst.common.skv.Bucket;
import sst.common.skv.Context;
import sst.common.skv.KeyValue;
import sst.common.skv.SimpleKeyValue;
import sst.textfile.OutputTextFile;
import sst.textfile.OutputTextFileImpl;

public class SKVTest {

    private static final String BUCKET22 = "BUCKET2";
    private static final String FIRST_CONTEXT = "FIRST";
    private static final String VALUE1 = "VALUE1";
    private static final String KEY1 = "KEY1";

    @Test
    public void firstTest() {
	SimpleKeyValue skv = KeyValue.me();
	Assert.assertNotNull(skv);
    }

    @Test
    public void createContext() {
	SimpleKeyValue skv = KeyValue.me();
	Context context = skv.context(FIRST_CONTEXT);
	Assert.assertNotNull(context);
    }

    @Test
    public void getBucket() {
	SimpleKeyValue skv = KeyValue.me();
	Context context = skv.context(FIRST_CONTEXT);
	Bucket bucket = skv.bucket(context);
	Assert.assertNotNull(bucket);
    }

    @Test
    public void newEntry() {
	SimpleKeyValue skv = KeyValue.me();
	Context context = skv.context(FIRST_CONTEXT);
	Bucket bucket = skv.bucket(context);
	bucket.newEntry(KEY1, VALUE1);

	Assert.assertNotNull(bucket.entry(KEY1));
	Assert.assertEquals("Value retrieved is not correct !", VALUE1, bucket.entry(KEY1).value());
    }

    @Test
    public void getEntry() {
	SimpleKeyValue skv = KeyValue.me();
	Context context = skv.context(FIRST_CONTEXT);
	Bucket bucket = skv.bucket(context);
	Assert.assertNull(bucket.entry(KEY1));
    }

    @Test
    public void getEntry2() {
	SimpleKeyValue skv = KeyValue.me();
	Bucket bucket = skv.bucket(FIRST_CONTEXT);
	Assert.assertNull(bucket);
    }

    @Test
    public void newBucket() {
	SimpleKeyValue skv = KeyValue.me();
	Context context = skv.context(FIRST_CONTEXT);
	Bucket bucket = skv.bucket(context);

	Bucket bucket2 = bucket.newBucket(BUCKET22);
	bucket2.newEntry(KEY1, VALUE1);

	Assert.assertNull(bucket.entry(KEY1));
	Assert.assertNotNull(bucket2.entry(KEY1));
	Assert.assertEquals("Value retrieved is not correct !", VALUE1, bucket2.entry(KEY1).value());
    }

    @Test
    public void getNewBucket() {
	SimpleKeyValue skv = KeyValue.me();
	Context context = skv.context(FIRST_CONTEXT);
	Bucket bucket = skv.bucket(context);

	Bucket bucket2 = bucket.newBucket(BUCKET22);
	bucket2.newEntry(KEY1, VALUE1);

	Bucket bucket2Prim = bucket.bucket(BUCKET22);

	Assert.assertNotNull(bucket2Prim);
	Assert.assertEquals(bucket2, bucket2Prim);
	Assert.assertNotNull(bucket2Prim.entry(KEY1));
	Assert.assertEquals("Value retrieved is not correct !", VALUE1, bucket2Prim.entry(KEY1).value());
    }

    @Test
    public void save() {
	String prefix = "foobar";
	String suffix = ".tmp";

	// this temporary file remains after the jvm exits
	try {
	    File tempFile = File.createTempFile(prefix, suffix);
	    // tempFile.deleteOnExit();

	    SimpleKeyValue skv = KeyValue.me();
	    Context context = skv.context(FIRST_CONTEXT);
	    Bucket bucket = skv.bucket(context);

	    Bucket bucket2 = bucket.newBucket(BUCKET22);
	    bucket2.newEntry(KEY1, VALUE1);

	    Bucket bucket2Prim = bucket.bucket(BUCKET22);

	    Assert.assertNotNull(bucket2Prim);
	    Assert.assertEquals(bucket2, bucket2Prim);
	    Assert.assertNotNull(bucket2Prim.entry(KEY1));
	    Assert.assertEquals("Value retrieved is not correct !", VALUE1, bucket2Prim.entry(KEY1).value());

	    try (OutputTextFile textFile = new OutputTextFileImpl(tempFile)) {
		textFile.serialize(skv);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    System.out.println("Temp File = [" + tempFile.getAbsolutePath() + "] length = <" + tempFile.length() + ">");

	    Assert.assertTrue(tempFile.exists());
	    Assert.assertNotEquals(2, tempFile.length());
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
