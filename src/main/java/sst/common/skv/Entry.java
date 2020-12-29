package sst.common.skv;

public interface Entry {

    String key();

    String value();

    Entry key(String key);

    Entry value(String value);
}
