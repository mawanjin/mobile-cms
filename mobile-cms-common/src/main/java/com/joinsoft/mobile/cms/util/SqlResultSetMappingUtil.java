package com.joinsoft.mobile.cms.util;

import com.google.common.collect.Lists;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * User: wujun
 * Date: 2014/9/2
 */
public class SqlResultSetMappingUtil {
    public static <T> T map(Class<T> type, Object[] tuple) {
        List<Object> tupleTypes = Lists.newArrayList();
        for (Object field : tuple) {
            tupleTypes.add(field.getClass());
        }
        try {
            Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
            return ctor.newInstance(tuple);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> map(Class<T> type, List<Object[]> records) {
        List<T> result = Lists.newLinkedList();
        for (Object[] record : records) {
            result.add(map(type, record));
        }
        return result;
    }
}
