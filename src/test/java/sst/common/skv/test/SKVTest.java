package sst.common.skv.test;

import org.junit.Assert;
import org.junit.Test;

import sst.common.skv.Bucket;
import sst.common.skv.Context;
import sst.common.skv.SimpleKeyValue;

public class SKVTest {

    private static final String BUCKET22 = "BUCKET2";
    private static final String FIRST_CONTEXT = "FIRST";
    private static final String VALUE1 = "VALUE1";
    private static final String KEY1 = "KEY1";

    @Test
    public void firstTest() {
	SimpleKeyValue skv = SimpleKeyValue.me();
	Assert.assertNotNull(skv);
    }

    @Test
    public void createContext() {
	SimpleKeyValue skv = SimpleKeyValue.me();
	Context context = skv.createContext(FIRST_CONTEXT);
	Assert.assertNotNull(context);
    }

    @Test
    public void getBucket() {
	SimpleKeyValue skv = SimpleKeyValue.me();
	Context context = skv.createContext(FIRST_CONTEXT);
	Bucket bucket = skv.getBucket(context);
	Assert.assertNotNull(bucket);
    }

    @Test
    public void newEntry() {
	SimpleKeyValue skv = SimpleKeyValue.me();
	Context context = skv.createContext(FIRST_CONTEXT);
	Bucket bucket = skv.getBucket(context);
	bucket.addEntry(KEY1, VALUE1);

	Assert.assertNotNull(bucket.getEntry(KEY1));
	Assert.assertEquals("Value retrieved is not correct !", VALUE1, bucket.getEntry(KEY1).value());
    }

    @Test
    public void getEntry() {
	SimpleKeyValue skv = SimpleKeyValue.me();
	Context context = skv.createContext(FIRST_CONTEXT);
	Bucket bucket = skv.getBucket(context);
	Assert.assertNull(bucket.getEntry(KEY1));
    }

    @Test
    public void getEntry2() {
	SimpleKeyValue skv = SimpleKeyValue.me();
	Bucket bucket = skv.getBucket(FIRST_CONTEXT);
	Assert.assertNull(bucket);
    }

    @Test
    public void newBucket() {
	SimpleKeyValue skv = SimpleKeyValue.me();
	Context context = skv.createContext(FIRST_CONTEXT);
	Bucket bucket = skv.getBucket(context);

	Bucket bucket2 = bucket.createBucket(BUCKET22);
	bucket2.addEntry(KEY1, VALUE1);

	Assert.assertNull(bucket.getEntry(KEY1));
	Assert.assertNotNull(bucket2.getEntry(KEY1));
	Assert.assertEquals("Value retrieved is not correct !", VALUE1, bucket2.getEntry(KEY1).value());
    }

    @Test
    public void getNewBucket() {
	SimpleKeyValue skv = SimpleKeyValue.me();
	Context context = skv.createContext(FIRST_CONTEXT);
	Bucket bucket = skv.getBucket(context);

	Bucket bucket2 = bucket.createBucket(BUCKET22);
	bucket2.addEntry(KEY1, VALUE1);

	Bucket bucket2Prim = bucket.getBucket(BUCKET22);

	Assert.assertNotNull(bucket2Prim);
	Assert.assertEquals(bucket2, bucket2Prim);
	Assert.assertNotNull(bucket2Prim.getEntry(KEY1));
	Assert.assertEquals("Value retrieved is not correct !", VALUE1, bucket2Prim.getEntry(KEY1).value());
    }
}
