package sst.common.skv.persistence;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sst.common.skv.Bucket;

public class JSonPersistenceProfile implements PersistenceProfile {
    private Bucket bucket = null;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String string() {
	try {
	    return objectMapper.writeValueAsString(bucket);
	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public Bucket bucket() {
	return bucket;
    }

    @Override
    public void initBucket(Bucket bucket) {
	this.bucket = bucket;
    }

    @Override
    public void createFromString(String buffer) {
	try {
	    objectMapper.readValue(buffer, Bucket.class);
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
    }

    @Override
    public void load(File file) {
	// TODO Auto-generated method stub

    }

    @Override
    public void save(File file) {
	// TODO Auto-generated method stub

    }
}
