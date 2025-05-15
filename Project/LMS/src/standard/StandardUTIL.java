package standard;

public interface StandardUTIL {

    /**
     * 获取工具类实例
     * @return 对应工具类的实例
     */
    public static StandardUTIL getInstance() {
        throw new RuntimeException("This Utility class need create getInstance method");
    }
}
