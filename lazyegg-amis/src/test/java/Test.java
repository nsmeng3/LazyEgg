import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lazyegg.amis.ActionSize;
import io.lazyegg.amis.ActionType;
import io.lazyegg.amis.Level;
import io.lazyegg.amis.Mode;
import io.lazyegg.amis.component.Action;
import io.lazyegg.amis.component.Form;
import io.lazyegg.amis.component.Page;
import io.lazyegg.amis.valid.Validation;
import io.lazyegg.amis.valid.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/16 11:30 下午
 */

@Slf4j
@Getter
@Setter
public class Test {

    public static void main(String[] args) throws Exception {
        Validation validation = new Validation().add(new IsEmail()).
            add(new IsLength(5)).
            add(new EqualsString("xxxxxx")).add(new MinLength(54)).
            add(new EqualsField("sssss")).add(new MatchRegex("/xxxxxx/"));
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(validation.getValidations());

        Pattern compile = Pattern.compile("/xxxxx/");


        Form formItem = new Form();
        formItem.setName("x333");
        formItem.setClassName("xxxx");

        Form body = new Form();
        body.setMode(Mode.normal);
        ArrayList<Action> actList = new ArrayList<>();
        Action e = new Action(ActionType.url);
        e.setSize(ActionSize.lg);
        e.setLevel(Level.DEFAULT);
        e.setConfirmText("xxxxxxxx${xxxx}");
        e.setLabel("xxxxcw");
        e.setClose(true);
        e.addRequired("xxxxx").addRequired("ssssss").addRequired("33333");

        actList.add(e);
        body.setActions(actList);
        Page xxxx = new Page(body);
//        xxxx.setAside("xxx");
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


        log.info("\n{}", objectMapper.writeValueAsString(xxxx));
    }
}
