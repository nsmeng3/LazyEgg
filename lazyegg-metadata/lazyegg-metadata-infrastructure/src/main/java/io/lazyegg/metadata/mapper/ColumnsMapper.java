package io.lazyegg.metadata.mapper;

import io.lazyegg.metadata.domain.metadata.Columns;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * columnsMapper
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:12 下午
 */
@Mapper
public interface ColumnsMapper {

    /**
     * 获取表中的全部字段
     *
     * @param tableName 表名
     * @return
     */
    List<ColumnsDO> listByTableName(String tableName);
}
