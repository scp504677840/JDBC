package utils;

import java.lang.reflect.ParameterizedType;

public class ReflectUtils {
    private ReflectUtils() {
    }

    /**
     * 获取父类泛型的参数化类型
     * 如：List<String>中的String
     *
     * @param clazz Class
     * @param <T>   泛型
     * @return 父类泛型的参数化类型
     */
    public static <T> Class<T> getGenericTypeSuperclass(Class clazz) {
        ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
        return (Class<T>) pt.getActualTypeArguments()[0];
    }
}
