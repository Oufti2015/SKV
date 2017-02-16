package sst.common.skv.persistence;

public interface PersistenceProfile {

    public String transformToString();

    public void fromString(String buffer);

    public void load();

    public void save();
}
