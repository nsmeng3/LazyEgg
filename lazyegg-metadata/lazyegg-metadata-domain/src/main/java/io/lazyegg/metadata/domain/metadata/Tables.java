package io.lazyegg.metadata.domain.metadata;

import com.alibaba.cola.domain.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 元数据-表
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 1:31 上午
 */
@NoArgsConstructor
@Data
@Entity
public class Tables {

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableComment;
    private Integer autoIncrement;
    private String tableCollation;
    private Date createTime;
    private String engine;
}
