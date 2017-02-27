package sst.common.skv;

import sst.common.skv.persistence.Persistable;
import sst.common.skv.persistence.PersistenceProfile;

public interface SimpleKeyValue extends Persistable {

    public Bucket bucket(Context context);

    public Bucket bucket(String context);

    public Context context(String contextId);

    public void addPersistenceProfile(PersistenceProfile profile);
}
