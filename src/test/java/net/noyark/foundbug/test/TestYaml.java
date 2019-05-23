package net.noyark.foundbug.test;

import net.noyark.www.utils.ex.ParseException;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestYaml {

    @Test
    public void testYamlParse(){
        try{
            Yaml yaml = new Yaml();
            InputStream in = new FileInputStream("key.yml");
            Map key = yaml.load(in);
            String key1 = key.get("key").toString();
            System.out.println(key1);
        }catch (IOException e){
            throw new ParseException("the yaml config is wrong",e);
        }
    }

}
