package io.lazyegg.amis.valid;

import io.lazyegg.amis.exception.AmisException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * 必须命中某个正则。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:26 上午
 */

@Slf4j
@Getter
@Setter
public abstract class AbsMatchRegexpValid implements ValidationItem<String> {

    private String regxStr;

    public AbsMatchRegexpValid(String regxStr) {
        this.regxStr = regxStr;
    }

    @Override
    public String getValidParam() throws Exception {
        try {
            Pattern.compile(regxStr);
        } catch (Exception e) {
            throw new AmisException("正则表达式有误");
        }
        return regxStr;
    }
}
