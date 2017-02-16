package sst.common.skv;

public interface Entry {

    public String key();

    public String value();

    public Entry key(String key);

    public Entry value(String value);
}
