package com.example.demoMybatisPlus;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @author:22024002
 * @date:2023/4/2 13:55
 * @description:
 */
@SpringBootTest
@Slf4j
public class VelocityTest {

    @Test
    void test(){
        Velocity.init();
        VelocityContext velocityContext=new VelocityContext();
        velocityContext.put("name","User");
        StringWriter writer=new StringWriter();
        String str="result is $name.toLowerCase()Controller";
        Velocity.evaluate(velocityContext,writer,"",str);
        log.info(str);
        log.info(String.valueOf(writer));
    }

    @Test
    void testVelocityForeach(){
        List<String> list= Arrays.asList("1","2","3","4");

        Velocity.init();
        VelocityContext velocityContext=new VelocityContext();
        velocityContext.put("list",list);
        StringWriter writer=new StringWriter();
        String str="#foreach ($num in $list)" +
                            "#if($foreach.index%2==0)" +
                            "偶数行:$num" +
                            "#elseif($foreach.index%2==1)" +
                            "奇数行:$num" +
                            "#end" +
                        "#end";
        Velocity.evaluate(velocityContext,writer,"",str);
        log.info(str);
        log.info(String.valueOf(writer));
    }
}
