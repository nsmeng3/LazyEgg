package io.lazyegg.metadata.gatewayimpl;

import io.lazyegg.metadata.domain.metadata.Columns;
import io.lazyegg.metadata.domain.metadata.gateway.ColumnsGateway;
import io.lazyegg.metadata.mapper.BaseColumnsMapper;
import io.lazyegg.metadata.mapper.ColumnsDO;
import io.lazyegg.metadata.mapper.MySqlColumnsMapper;
import io.lazyegg.metadata.mapper.SqlServerColumnsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:09 下午
 */
@Component
public class ColumnsGatewayImpl implements ColumnsGateway {

    @Resource
    private BaseColumnsMapper metadataColumnsMapper;


    @Override
    public List<Columns> listColumn(String tableName) {
//        MetaDataFactory.
        List<ColumnsDO> list = metadataColumnsMapper.listByTableName(tableName);
        ArrayList<Columns> columnsArrayList = new ArrayList<>();
        list.forEach(source -> {
            Columns target = new Columns();
            BeanUtils.copyProperties(source, target);
            columnsArrayList.add(target);
        });
        return columnsArrayList;
    }
}
