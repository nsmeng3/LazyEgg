import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lazyegg.amis.component.Page;
import io.lazyegg.amis.component.SchemaNodeForString;
import io.lazyegg.amis.valid.Validation;
import io.lazyegg.amis.valid.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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


        Page xxxx = new Page("xxxx");
        xxxx.setAside(new SchemaNodeForString("xxxx"));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


        log.info(objectMapper.writeValueAsString(xxxx));
    }
}
