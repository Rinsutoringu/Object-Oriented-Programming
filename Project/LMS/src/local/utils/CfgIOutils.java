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

/**
 * 单实例模式工具类，实现配置文件读写
 */
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

    /**
     * 读
     * @param filepath 配置文件所处文件路径
     * @return 返回一个List<Map<String, String>>，每个Map代表一行数据
     * @exception FileNotExist 文件不存在
     * @exception CfgError 读取文件错误
     */
    public static Map<String,Map<String, String>> readjson(String filepath) throws FileNotExist, CfgError{
        try {
            File file = new File(filepath);
            
            // 文件不存在就创建一个
            if (!file.exists()) file.createNewFile();

            // 读取文件内容
            String buffer = new String(Files.readAllBytes(Paths.get(filepath)));

            // 如果文件里面的值是空的就用默认值，不作提示
            if (buffer.trim().isEmpty()) return null;

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(buffer, new TypeReference<Map<String,Map<String, String>>>() {});

        } catch (Exception e) {
            throw new CfgError("Error reading file: " + filepath, e);
        }
    }


    /**
     * 写
     * @param filepath 配置文件所处文件路径
     * @param data 要写入的数据
     * @exception CfgError 写入文件错误
     */
    public static void writejson(String filepath, Map<String,Map<String, String>> data) throws CfgError{
        try {
            ObjectMapper mapper = new ObjectMapper();
            // 格式化数据
            String jsonString = mapper.writeValueAsString(data);
            // 数据转化为编码写入
            Files.write(Paths.get(filepath), jsonString.getBytes());
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    /**
     * 删
     * @param filepath 配置文件所处文件路径
     * @return 返回删除结果 true表示删除成功，false表示删除失败
     */
    public static boolean deletejson(String filepath) {
        try {
            Files.deleteIfExists(Paths.get(filepath));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 获取一组功能配置
     * @param filepath
     * @return
     */

    // /**
    //  * 查 (传入内存中json对象)
    //  * @param data 待查询数据
    //  * @param key 查询的键
    //  * @return 返回查询结果 null表示没有找到
    //  */
    // public static String searchjson(List<Map<String, String>> data, String key) {
    //     for (Map<String, String> map : data) {
    //         if (map.containsKey(key)) {
    //             return map.get(key);
    //         }
    //     }
    //     return null;
    // }

    // /**
    //  * 查 (传入文件路径)
    //  * @param filepath 配置文件所处文件路径
    //  * @param key 查询的键
    //  * @return 返回查询结果 null表示没有找到
    //  */
    // public static String searchjson(String filepath, String key) {
    //     try {
    //         List<Map<String, String>> data = readjson(filepath);
    //         return searchjson(data, key);
    //     } catch (Exception e) {
    //         return null;
    //     }
    // }



}