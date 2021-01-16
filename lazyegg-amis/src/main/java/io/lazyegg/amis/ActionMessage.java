package io.lazyegg.amis;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/17 4:13 上午
 */

@Slf4j
@Getter
@Setter
public class ActionMessage {

    /**
     * ajax 操作成功后提示，可以不指定，不指定时以 api 返回为准。
     */
    private String success;

    /**
     * ajax 操作失败提示。
     */
    private String failed;

}
