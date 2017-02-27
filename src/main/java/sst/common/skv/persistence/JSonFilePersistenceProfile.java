package sst.common.skv.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSonFilePersistenceProfile extends JSonPersistenceProfile {

    @Override
    public void load(File file) {
	try {
	    createFromString(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void save(File file) {
	try (PrintWriter out = new PrintWriter(file)) {
	    out.print(string());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }
}
