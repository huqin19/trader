package io.renren.common.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.ho.yaml.Yaml;
import org.junit.Test;

public class ReadYmlTest {

	@Test
	public void test() {
        File dumpFile=new File("E:\\0_xz\\2_quanshang\\git\\testdev\\trader\\renren-fastplus\\src\\main\\resources\\const.yml");
        HashMap ml = new HashMap<>();
		try {
			ml = Yaml.loadType(dumpFile, HashMap.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println((String) ml.get("JOB_SENDWX"));
	}

}
