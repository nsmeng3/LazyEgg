package io.lazyegg.metadata.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:27 下午
 */
@Data
public class ColumnsGetByColumnNameQuery extends Query {

    private String columnName;

    private String tableName;
}
