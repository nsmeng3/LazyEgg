package io.lazyegg.amis;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/16 10:02 下午
 */

@Slf4j
@Getter
@Setter
public class Message {

    /**
     * 获取成功时提示
     */
    private String fetchSuccess;
    /**
     * 获取失败时提示
     */
    private String fetchFailed;
    /**
     * 保存成功时提示
     */
    private String saveSuccess;
    /**
     * 保存失败时提示
     */
    private String saveFailed;
}
