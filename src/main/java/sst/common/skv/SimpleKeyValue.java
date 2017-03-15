package sst.common.skv;

import sst.common.skv.persistence.PersistenceProfile;
import sst.textfile.SerializableToTextFile;

public interface SimpleKeyValue extends SerializableToTextFile {

    public Bucket bucket(Context context);

    public Bucket bucket(String context);

    public Context context(String contextId);

    public void addPersistenceProfile(PersistenceProfile profile);
}
