package sst.common.skv;

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
