package io.lazyegg.metadata.domain.metadata.gateway;


import io.lazyegg.metadata.domain.metadata.Columns;

import java.util.List;

/**
 * 网关-列
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 1:58 上午
 */
public interface ColumnsGateway {

    List<Columns> listColumn(String tableName);
}
