package io.lazyegg.metadata.gatewayimpl;

import io.lazyegg.demo.domain.metadata.gateway.TablesGateway;
import io.lazyegg.metadata.domain.metadata.Tables;
import io.lazyegg.metadata.mapper.BaseTablesMapper;
import io.lazyegg.metadata.mapper.MySqlTablesMapper;
import io.lazyegg.metadata.mapper.SqlServerTablesMapper;
import io.lazyegg.metadata.mapper.TablesDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 网关impl-表
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:04 上午
 */
@Component
public class TablesGatewayImpl implements TablesGateway {
    @Resource
    private BaseTablesMapper metadataTablesMapper;

    @Override
    public Tables getTable(String tableName) {
        TablesDO tablesDO = metadataTablesMapper.getByTableName(tableName);
        Tables tables = new Tables();
        BeanUtils.copyProperties(tablesDO, tables);
        return tables;
    }

    @Override
    public List<Tables> listTable(String tableSchema) {
        List<TablesDO> tablesDOList = metadataTablesMapper.listTable(tableSchema);
        ArrayList<Tables> tables = new ArrayList<>();
        tablesDOList.forEach(tablesDO -> {
            Tables temp = new Tables();
            BeanUtils.copyProperties(tablesDO, temp);
            tables.add(temp);
        });
        return tables;
    }
}
