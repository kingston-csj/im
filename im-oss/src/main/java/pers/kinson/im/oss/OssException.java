package pers.kinson.im.oss;

public class OssException extends RuntimeException {

    public OssException(String msg) {
        super(msg);
    }

    public OssException(Exception e) {
        super(e);
    }

}
