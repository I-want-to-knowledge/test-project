package com.geo.source.head_first.design_mode.adapter.EnumIteratorAdapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 枚举适配迭代器
 *
 * @author YanZhen
 * @since 2019-01-26 13:56
 */
public class EnumerationIterator implements Iterator<String> {
    private Enumeration<String> enumeration;

    public EnumerationIterator(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public String next() {
        return enumeration.nextElement();
    }
}
