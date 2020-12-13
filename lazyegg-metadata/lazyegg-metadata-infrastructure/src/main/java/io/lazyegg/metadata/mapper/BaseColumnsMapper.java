package io.lazyegg.metadata.mapper;

import java.util.List;

/**
 * columnsMapper
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:12 下午
 */
public interface BaseColumnsMapper {

    /**
     * 获取表中的全部字段
     *
     * @param tableName 表名
     * @return
     */
    List<ColumnsDO> listByTableName(String tableName);
}
