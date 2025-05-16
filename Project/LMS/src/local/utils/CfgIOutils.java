package local.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import local.utils.error.CfgError;
import local.utils.error.FileNotExist;

/**
 * 单实例模式工具类，实现配置文件读写
 */
public class CfgIOutils implements standard.StandardUTIL {

    private static CfgIOutils instance;

    private CfgIOutils() {
    }
    

    public static CfgIOutils getInstance() {
        if (instance == null) {
            instance = new CfgIOutils();
        }
        return instance;
    }

    /**
     * 读
     * @param filepath 配置文件所处文件路径
     * @return 返回一个List<Map<String, String>>，每个Map代表一行数据
     * @exception FileNotExist 文件不存在
     * @exception CfgError 读取文件错误
     */
    public static List<Map<String, String>> read(String filepath) throws FileNotExist, CfgError{
        try {
            File file = new File(filepath);
            
            // 文件不存在就创建一个
            if (!file.exists()) file.createNewFile();

            // 读取文件内容
            String buffer = new String(Files.readAllBytes(Paths.get(filepath)));

            // 如果文件里面的值是空的就用默认值，不作提示
            if (buffer.trim().isEmpty()) return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(buffer, new TypeReference<List<Map<String, String>>>() {});

        } catch (Exception e) {
            throw new CfgError("Error reading file: " + filepath, e);
        }
    }


    /**
     * 写
     */

    /**
     * 查
     */

    /**
     * 删
     */
    
}
