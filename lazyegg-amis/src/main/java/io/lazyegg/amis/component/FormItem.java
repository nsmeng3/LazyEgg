package io.lazyegg.amis.component;

import io.lazyegg.amis.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 普通表单项
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 10:11 下午
 */

@Slf4j
@Getter
@Setter
public class FormItem extends Control{
    @Override
    public String getType() {
        return "text";
    }

    private String mode;

    private Boolean hidden;

    /**
     * 表单项尺寸
     */
    private Size size;

    /**
     * 默认值
     */
    private String value;

    /**
     * 自定义校验信息
     */
//    private ValidationError validationErrors;

}
