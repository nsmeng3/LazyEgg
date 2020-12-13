package io.lazyegg.metadata.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.metadata.api.TablesServiceI;
import io.lazyegg.metadata.dto.TableListByTableSchemaQuery;
import io.lazyegg.metadata.dto.TablesGetByTableNameQuery;
import io.lazyegg.metadata.dto.data.TablesDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * tablesController
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:29 上午
 */
@RestController
public class TablesController {

    @Resource
    private TablesServiceI tablesService;

    @GetMapping("/table/{tableName}")
    public SingleResponse<TablesDTO> getTable(@PathVariable String tableName) {
        TablesGetByTableNameQuery query = new TablesGetByTableNameQuery();
        query.setTableName(tableName);
        return tablesService.getTable(query);
    }

    @GetMapping("/table")
    public MultiResponse<TablesDTO> listTable(@RequestParam(required = false) String tableSchema) {
        TableListByTableSchemaQuery query = new TableListByTableSchemaQuery();
        query.setTableSchema(tableSchema);
        return tablesService.listTable(query);
    }
}
