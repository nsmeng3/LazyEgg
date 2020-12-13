package io.lazyegg.metadata.dto.data;

import lombok.Data;

import java.sql.Date;

/**
 * tablesDTO
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:33 上午
 */
@Data
public class TablesDTO {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableComment;
    private Integer autoIncrement;
    private String tableCollation;
    private Date createTime;
    private String engine;
}
