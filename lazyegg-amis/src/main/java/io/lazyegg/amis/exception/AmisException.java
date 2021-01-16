package io.lazyegg.amis.exception;

import com.alibaba.cola.exception.BaseException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * amis 异常
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:27 下午
 */

@Slf4j
@Getter
@Setter
public class AmisException extends Exception{

    private String errMsg;

    // TODO 异常需要完善
    public AmisException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }
}
