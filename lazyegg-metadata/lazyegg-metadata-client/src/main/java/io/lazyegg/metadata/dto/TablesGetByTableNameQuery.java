package io.lazyegg.metadata.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:40 上午
 */
@Data
public class TablesGetByTableNameQuery extends Query {

    private String tableName;

    private String tableSchema;
}
