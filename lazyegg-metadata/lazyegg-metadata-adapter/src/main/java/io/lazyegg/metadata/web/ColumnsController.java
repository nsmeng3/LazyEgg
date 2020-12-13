package io.lazyegg.metadata.web;

import com.alibaba.cola.dto.MultiResponse;
import io.lazyegg.metadata.api.ColumnsServiceI;
import io.lazyegg.metadata.dto.ColumnsListByTableNameQuery;
import io.lazyegg.metadata.dto.data.ColumnsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * columnsController
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:21 下午
 */
@RestController
public class ColumnsController {

    @Resource
    private ColumnsServiceI columnsService;

    @GetMapping("/column")
    public MultiResponse<ColumnsDTO> getColumn(@RequestParam String tableName) {
        ColumnsListByTableNameQuery query = new ColumnsListByTableNameQuery();
        query.setTableName(tableName);
        return columnsService.listColumn(query);
    }
}
