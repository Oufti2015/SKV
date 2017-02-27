package sst.common.skv.persistence;

public interface PersistenceProfile extends Persistable {

    public Root root();

    public void initRoot(Root bucket);

    public String string();

    public void createFromString(String buffer);

}
