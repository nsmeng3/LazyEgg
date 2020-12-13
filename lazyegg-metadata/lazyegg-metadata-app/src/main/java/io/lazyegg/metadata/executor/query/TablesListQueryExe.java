package io.lazyegg.metadata.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import io.lazyegg.metadata.domain.metadata.Tables;
import io.lazyegg.metadata.dto.TableListByTableSchemaQuery;
import io.lazyegg.metadata.dto.data.TablesDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 3:09 下午
 */
@Component
public class TablesListQueryExe {
    @Resource
    private io.lazyegg.demo.domain.metadata.gateway.TablesGateway tablesGateway;

    public MultiResponse<TablesDTO> execute(TableListByTableSchemaQuery query) {
        List<Tables> tableList = tablesGateway.listTable(query.getTableSchema());
        ArrayList<TablesDTO> result = new ArrayList<>();
        tableList.forEach(table -> {
            TablesDTO target = new TablesDTO();
            BeanUtils.copyProperties(table, target);
            result.add(target);
        });
        return MultiResponse.of(result);
    }
}
