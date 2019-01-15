package com.geo.source.head_first.design_mode.decorator.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName LowerCaseInputStream
 * @Author YanZhen
 * 2019-01-09 19:57
 * @Description 大小写转换类
 */
public class LowerCaseInputStream extends FilterInputStream {

    LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int read = super.read();
        return read == -1 ? read : Character.toLowerCase((char) read);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int read = super.read(b, off, len);
        for (int i = off; i < off + len; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return read;
    }
}
