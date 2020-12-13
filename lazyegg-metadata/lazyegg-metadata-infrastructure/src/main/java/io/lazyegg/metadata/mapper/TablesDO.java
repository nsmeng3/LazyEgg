package io.lazyegg.metadata.mapper;

import lombok.Data;

import java.sql.Date;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:09 上午
 */
@Data
public class TablesDO {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableComment;
    private Integer autoIncrement;
    private String tableCollation;
    private Date createTime;
    private String engine;
}
