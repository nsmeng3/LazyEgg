package io.lazyegg.metadata;

import com.alibaba.cola.dto.MultiResponse;
import io.lazyegg.metadata.api.ColumnsServiceI;
import io.lazyegg.metadata.domain.metadata.Columns;
import io.lazyegg.metadata.domain.metadata.gateway.ColumnsGateway;
import io.lazyegg.metadata.dto.ColumnsListByTableNameQuery;
import io.lazyegg.metadata.dto.data.ColumnsDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:29 下午
 */
@Service
public class ColumnsServiceImpl implements ColumnsServiceI {

    @Resource
    private ColumnsGateway columnsGateway;


    @Override
    public MultiResponse<ColumnsDTO> listColumn(ColumnsListByTableNameQuery query) {
        List<Columns> list = columnsGateway.listColumn(query.getTableName());
        ArrayList<ColumnsDTO> result = new ArrayList<>();
        list.forEach(columns -> {
            ColumnsDTO target = new ColumnsDTO();
            BeanUtils.copyProperties(columns, target);
            result.add(target);
        });
        return MultiResponse.of(result);
    }
}
