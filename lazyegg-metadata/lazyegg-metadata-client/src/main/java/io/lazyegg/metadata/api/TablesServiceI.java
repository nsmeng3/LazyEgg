package io.lazyegg.metadata.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.metadata.dto.TableListByTableSchemaQuery;
import io.lazyegg.metadata.dto.TablesGetByTableNameQuery;
import io.lazyegg.metadata.dto.data.TablesDTO;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:43 上午
 */
public interface TablesServiceI {
    SingleResponse<TablesDTO> getTable(TablesGetByTableNameQuery query);

    MultiResponse<TablesDTO> listTable(TableListByTableSchemaQuery query);
}
