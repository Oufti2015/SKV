package sst.common.skv;

import sst.textfile.SerializableToTextFile;

public interface SimpleKeyValue extends SerializableToTextFile {

    Bucket bucket(Context context);

    Bucket bucket(String context);

    Context context(String contextId);
}
