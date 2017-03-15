package sst.common.skv;

import lombok.ToString;

@ToString
public class ContextImplementation implements Context {
    private String id;

    ContextImplementation() {
    }

    @Override
    public Context id(String id) {
	this.id = id;
	return this;
    }

    @Override
    public String id() {
	return id;
    }
}
