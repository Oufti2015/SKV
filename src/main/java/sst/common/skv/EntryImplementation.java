package sst.common.skv;

import lombok.ToString;

@ToString
public class EntryImplementation implements Entry {

    private String key = null;
    private String value = null;

    EntryImplementation() {
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Entry key(String key) {
        this.key = key;
        return this;
    }

    @Override
    public Entry value(String value) {
        this.value = value;
        return this;
    }
}
