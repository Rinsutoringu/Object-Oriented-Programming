package local.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.utils.error.CfgError;
import local.utils.error.FileNotExist;

public class CfgIOutils implements standard.StandardUTIL {

    private static CfgIOutils instance;
    private static errorHandler eh = errorHandler.getInstance();

    private CfgIOutils() {
    }
    

    public static CfgIOutils getInstance() {
        if (instance == null) {
            instance = new CfgIOutils();
        }
        return instance;
    }


    public static Map<String,Map<String, String>> readjson(String filepath) throws FileNotExist, CfgError{
        try {
            File file = new File(filepath);
            
            if (!file.exists()) file.createNewFile();
            String buffer = new String(Files.readAllBytes(Paths.get(filepath)));
            if (buffer.trim().isEmpty()) return null;
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(buffer, new TypeReference<Map<String,Map<String, String>>>() {});

        } catch (Exception e) {
            throw new CfgError("Error reading file: " + filepath, e);
        }
    }



    public static void writejson(String filepath, Map<String,Map<String, String>> data) throws CfgError{
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(data);
            Files.write(Paths.get(filepath), jsonString.getBytes());
            
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    public static boolean deletejson(String filepath) {
        try {
            Files.deleteIfExists(Paths.get(filepath));
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}