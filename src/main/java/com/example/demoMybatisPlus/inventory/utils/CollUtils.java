package com.example.demoMybatisPlus.inventory.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 集合帮助类
 */
public class CollUtils extends CollectionUtils {

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 对象转List
     *
     * @param obj 对象
     * @return List
     */
    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

    /**
     * 是否是集合
     *
     * @param obj 对象
     * @return True 是，反之 False
     */
    public static boolean isCollection(Object obj) {
        return obj.getClass().isArray() || obj instanceof Collection;
    }

    /**
     * 是否不是集合
     *
     * @param obj 对象
     * @return True 是，反之 False
     */
    public static boolean isNotCollection(Object obj) {
        return !isCollection(obj);
    }

    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {

        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() < fromIndex) {
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    public static <T> void doPaginated(Collection<T> fullList, Integer pageSize, Page<T> pageInterface) {

        final List<T> list = new ArrayList<T>(fullList);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size()) {
            pageSize = list.size();
        }

        final int numPages = (int) Math.ceil((double) list.size() / (double) pageSize);
        for (int pageNum = 0; pageNum < numPages; ) {
            final List<T> page = list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size()));
            pageInterface.run(page);
        }
    }

    public interface Page<T> {
        void run(List<T> item);
    }
}
