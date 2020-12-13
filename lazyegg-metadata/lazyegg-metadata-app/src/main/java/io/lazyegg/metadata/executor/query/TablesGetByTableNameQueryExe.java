package io.lazyegg.metadata.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.demo.domain.metadata.gateway.TablesGateway;
import io.lazyegg.metadata.domain.metadata.Tables;
import io.lazyegg.metadata.dto.TablesGetByTableNameQuery;
import io.lazyegg.metadata.dto.data.TablesDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:51 上午
 */
@Component
public class TablesGetByTableNameQueryExe {

    @Resource
    private TablesGateway tablesGateway;

    public SingleResponse<TablesDTO> execute(TablesGetByTableNameQuery query) {
        Tables table = tablesGateway.getTable(query.getTableName());
        TablesDTO target = new TablesDTO();
        BeanUtils.copyProperties(table, target);
        return SingleResponse.of(target);
    }
}
