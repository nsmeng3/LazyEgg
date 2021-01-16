package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * 表单格式验证
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 10:37 下午
 */

@Slf4j
@Getter
@Setter
public class Validation {

    private HashMap<String, Object> validations = new HashMap<>();

    public <T> Validation add(ValidationItem<T> validationItem) throws Exception {
        validations.put(validationItem.validationItem(), validationItem.getValidParam());
        return this;
    }
}
