package site.sixteen.demo.enums;

/**
 * @author panhainan@yeah.net(@link http://sixteen.site)
 **/
public enum AlgorithmEnum {
    MD5("MD5", "MD5加密算法"),
    SHA1("SHA1", "SHA1加密算法");
    private String value;
    private String info;

    AlgorithmEnum(String value, String info) {
        this.value = value;
        this.info = info;
    }

    /**
     * 值
     *
     * @return
     */
    public String value() {
        return this.value;
    }

    /**
     * 值说明
     *
     * @return
     */
    public String info() {
        return this.info();
    }
}
