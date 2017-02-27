package sst.common.skv.persistence;

import java.io.File;

public interface Persistable {

    public void load(File file);

    public void save(File file);
}
