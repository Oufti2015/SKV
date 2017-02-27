package sst.common.skv.persistence;

import sst.common.skv.Bucket;

public interface PersistenceProfile extends Persistable {

    public Bucket bucket();

    public void initBucket(Bucket bucket);

    public String string();

    public void createFromString(String buffer);

}
