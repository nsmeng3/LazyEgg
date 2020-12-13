package io.lazyegg.metadata;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.metadata.api.TablesServiceI;
import io.lazyegg.metadata.dto.TablesGetByTableNameQuery;
import io.lazyegg.metadata.dto.data.TablesDTO;
import io.lazyegg.metadata.dto.TableListByTableSchemaQuery;
import io.lazyegg.metadata.executor.query.TablesGetByTableNameQueryExe;
import io.lazyegg.metadata.executor.query.TablesListQueryExe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:47 上午
 */
@Service
public class TablesServiceImpl implements TablesServiceI {

    @Resource
    private TablesGetByTableNameQueryExe tablesGetByTableNameQueryExe;
    @Resource
    private TablesListQueryExe tablesListQueryExe;

    @Override
    public SingleResponse<TablesDTO> getTable(TablesGetByTableNameQuery query) {
        return tablesGetByTableNameQueryExe.execute(query);
    }

    @Override
    public MultiResponse<TablesDTO> listTable(TableListByTableSchemaQuery query) {
        return tablesListQueryExe.execute(query);
    }
}
