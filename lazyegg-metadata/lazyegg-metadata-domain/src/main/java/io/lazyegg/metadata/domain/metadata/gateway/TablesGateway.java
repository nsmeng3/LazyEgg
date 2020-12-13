package io.lazyegg.demo.domain.metadata.gateway;


import io.lazyegg.metadata.domain.metadata.Tables;

import java.util.List;

/**
 * 网关-表
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 1:49 上午
 */
public interface TablesGateway {

    /**
     * 获取表
     *
     * @param tableName 表名
     * @return
     */
    Tables getTable(String tableName);

    /**
     * list table
     *
     * @param tableSchema 表架构 - 数据库名称
     * @return
     */
    List<Tables> listTable(String tableSchema);
}
