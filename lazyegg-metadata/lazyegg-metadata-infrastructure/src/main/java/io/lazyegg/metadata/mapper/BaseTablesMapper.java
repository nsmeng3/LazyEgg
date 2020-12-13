package io.lazyegg.metadata.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * metadata table mapper
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:02 上午
 */
public interface BaseTablesMapper {

    TablesDO getByTableName(String tableName);

    List<TablesDO> listTable(@Param(value = "tableSchema") String tableSchema);
}
