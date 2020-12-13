package io.lazyegg.metadata.api;

import com.alibaba.cola.dto.MultiResponse;
import io.lazyegg.metadata.dto.ColumnsListByTableNameQuery;
import io.lazyegg.metadata.dto.data.ColumnsDTO;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/13 2:28 下午
 */
public interface ColumnsServiceI {

    MultiResponse<ColumnsDTO> listColumn(ColumnsListByTableNameQuery query);
}
