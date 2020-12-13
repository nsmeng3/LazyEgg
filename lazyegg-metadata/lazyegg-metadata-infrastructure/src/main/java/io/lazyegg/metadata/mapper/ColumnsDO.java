package io.lazyegg.metadata.mapper;

import lombok.Data;

@Data
public class ColumnsDO {

    /**
     * tableCatalog
     */
    private String tableCatalog;
    /**
     * tableSchema
     */
    private String tableSchema;
    /**
     * tableName
     */
    private String tableName;
    /**
     * columnName
     */
    private String columnName;
    /**
     * ordinalPosition
     */
    private Integer ordinalPosition;
    /**
     * columnDefault
     */
    private Object columnDefault;
    /**
     * isNullable
     */
    private String isNullable;
    /**
     * dataType
     */
    private String dataType;
    /**
     * characterMaximumLength
     */
    private Object characterMaximumLength;
    /**
     * characterOctetLength
     */
    private Object characterOctetLength;
    /**
     * numericPrecision
     */
    private Integer numericPrecision;
    /**
     * numericScale
     */
    private Integer numericScale;
    /**
     * datetimePrecision
     */
    private Object datetimePrecision;
    /**
     * characterSetName
     */
    private Object characterSetName;
    /**
     * collationName
     */
    private Object collationName;
    /**
     * columnType
     */
    private String columnType;
    /**
     * columnKey
     */
    private String columnKey;
    /**
     * extra
     */
    private String extra;
    /**
     * privileges
     */
    private String privileges;
    /**
     * columnComment
     */
    private String columnComment;
}
