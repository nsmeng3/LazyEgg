package io.lazyegg.amis.valid;

import io.lazyegg.amis.exception.AmisException;

/**
 * 表单格式校验项
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 10:40 下午
 */
public interface ValidationItem<T> {

    /**
     * 校验项名称
     *
     * @return 校验项名称
     */
    String validationItem();


    /**
     * 校验参数
     *
     * @return
     */
    T getValidParam() throws AmisException, Exception;
}
