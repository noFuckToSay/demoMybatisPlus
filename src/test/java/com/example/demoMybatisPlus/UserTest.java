package com.example.demoMybatisPlus;

import com.alibaba.fastjson.JSONObject;
import com.example.demoMybatisPlus.user.dto.UserVo;
import com.example.demoMybatisPlus.user.entity.User;
import com.example.demoMybatisPlus.user.enums.UserStatusEnum;
import com.example.demoMybatisPlus.user.mapstruct.UserMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:22024002
 * @date:2023/5/19 19:59
 * @description:
 */
@SpringBootTest
@Slf4j
public class UserTest {
    //个税区间
    private final BigDecimal AMOUNT_3000_PER = BigDecimal.valueOf(0.03);
    private final BigDecimal AMOUNT_3000 = BigDecimal.valueOf(3000);
    private final BigDecimal AMOUNT_12000_PER =BigDecimal.valueOf(0.1);
    private final BigDecimal AMOUNT_12000 = BigDecimal.valueOf(12000);
    private final BigDecimal AMOUNT_25000_PER = BigDecimal.valueOf(0.2);
    private final BigDecimal AMOUNT_25000 = BigDecimal.valueOf(25000);
    private final BigDecimal AMOUNT_35000_PER = BigDecimal.valueOf(0.25);
    private final BigDecimal AMOUNT_35000 = BigDecimal.valueOf(35000);
    private final BigDecimal AMOUNT_55000_PER = BigDecimal.valueOf(0.3);
    private final BigDecimal AMOUNT_55000 = BigDecimal.valueOf(55000);
    private final BigDecimal AMOUNT_80000_PER = BigDecimal.valueOf(0.35);
    private final BigDecimal AMOUNT_80000 = BigDecimal.valueOf(80000);
    private final BigDecimal TAX_GT_80000 = BigDecimal.valueOf(0.45);



    //养老保险比例
    private final BigDecimal YANGLAO_PER = BigDecimal.valueOf(0.08);
    //医疗保险比例
    private final BigDecimal YIBAO_PER = BigDecimal.valueOf(0.02);
    //失业保险比例
    private final BigDecimal SHIYE_PER = BigDecimal.valueOf(0.005);
    //公积金比例
    private final BigDecimal GONGJIJIN_PER = BigDecimal.valueOf(0.12);

    @Test
    void test() {
        //税前薪资
        BigDecimal shuiQian = BigDecimal.valueOf(10000);
        //税后薪资
        BigDecimal shuiHou = BigDecimal.ZERO;


        BigDecimal yanglaoAmount = BigDecimal.ZERO;

        BigDecimal yibaoAmount = BigDecimal.ZERO;

        BigDecimal shiyeAmount = BigDecimal.ZERO;

        BigDecimal gongjijinAmount = BigDecimal.ZERO;
        //个税比例
        List<Map<String, BigDecimal>> taxPers = new ArrayList<>();
        //个税扣除基数
        BigDecimal taxBaseNum = BigDecimal.ZERO;
        //个税扣除金额
        BigDecimal taxAmount = BigDecimal.ZERO;

        //养老金额 = 税前 × 养老金比例
        yanglaoAmount = shuiQian.multiply(YANGLAO_PER);
        //医保金额 = 税前 × 医保比例
        yibaoAmount = shuiQian.multiply(YIBAO_PER);
        //失业金额 = 税前 × 失业保险比例
        shiyeAmount = shuiQian.multiply(SHIYE_PER);
        //公积金金额 = 税前 × 公积金比例
        gongjijinAmount = shuiQian.multiply(GONGJIJIN_PER);
        //个税扣除基数 = 税前 - 五险一金
        taxBaseNum = shuiQian
                .subtract(yanglaoAmount)
                .subtract(yibaoAmount)
                .subtract(shiyeAmount)
                .subtract(gongjijinAmount);
        //个税基数判断缴税各个梯度
        if (taxBaseNum.compareTo(AMOUNT_3000) <= 0) {  //不超过3000元的，税率为3%
            taxPers.add(addTax(taxBaseNum,AMOUNT_3000_PER));
        } else if (taxBaseNum.compareTo(AMOUNT_12000) <= 0) { //超过3000元至12000元的部分，税率为10%
            taxPers.add(addTax(AMOUNT_3000,AMOUNT_3000_PER));
            taxPers.add(addTax(taxBaseNum.subtract(AMOUNT_3000),AMOUNT_12000_PER));

        }else if (taxBaseNum.compareTo(AMOUNT_25000) <= 0) { //超过12000元至25000元的部分，税率为20%
            taxPers.add(addTax(AMOUNT_3000,AMOUNT_3000_PER));
            taxPers.add(addTax(AMOUNT_12000.subtract(AMOUNT_3000),AMOUNT_12000_PER));
            taxPers.add(addTax(taxBaseNum.subtract(AMOUNT_12000),AMOUNT_25000_PER));

        }else if (taxBaseNum.compareTo(AMOUNT_35000) <= 0) { //超过25000元至35000元的部分，税率为25%
            taxPers.add(addTax(AMOUNT_3000,AMOUNT_3000_PER));
            taxPers.add(addTax(AMOUNT_12000.subtract(AMOUNT_3000),AMOUNT_12000_PER));
            taxPers.add(addTax(AMOUNT_25000.subtract(AMOUNT_12000),AMOUNT_25000_PER));
            taxPers.add(addTax(taxBaseNum.subtract(AMOUNT_25000),AMOUNT_35000_PER));
        }else if (taxBaseNum.compareTo(AMOUNT_55000) <= 0) { //超过35000元至55000元的部分，税率为30%
            taxPers.add(addTax(AMOUNT_3000,AMOUNT_3000_PER));
            taxPers.add(addTax(AMOUNT_12000.subtract(AMOUNT_3000),AMOUNT_12000_PER));
            taxPers.add(addTax(AMOUNT_25000.subtract(AMOUNT_12000),AMOUNT_25000_PER));
            taxPers.add(addTax(AMOUNT_35000.subtract(AMOUNT_25000),AMOUNT_35000_PER));
            taxPers.add(addTax(taxBaseNum.subtract(AMOUNT_35000),AMOUNT_55000_PER));
        }else if (taxBaseNum.compareTo(AMOUNT_80000) <= 0) { //超过55000元至80000元的部分，税率为35%
            taxPers.add(addTax(AMOUNT_3000,AMOUNT_3000_PER));
            taxPers.add(addTax(AMOUNT_12000.subtract(AMOUNT_3000),AMOUNT_12000_PER));
            taxPers.add(addTax(AMOUNT_25000.subtract(AMOUNT_12000),AMOUNT_25000_PER));
            taxPers.add(addTax(AMOUNT_35000.subtract(AMOUNT_25000),AMOUNT_35000_PER));
            taxPers.add(addTax(AMOUNT_55000.subtract(AMOUNT_35000),AMOUNT_55000_PER));
            taxPers.add(addTax(taxBaseNum.subtract(AMOUNT_55000),AMOUNT_80000_PER));
        }else{//超过80000元以上的部分，税率为45%
            taxPers.add(addTax(AMOUNT_3000,AMOUNT_3000_PER));
            taxPers.add(addTax(AMOUNT_12000.subtract(AMOUNT_3000),AMOUNT_12000_PER));
            taxPers.add(addTax(AMOUNT_25000.subtract(AMOUNT_12000),AMOUNT_25000_PER));
            taxPers.add(addTax(AMOUNT_35000.subtract(AMOUNT_25000),AMOUNT_35000_PER));
            taxPers.add(addTax(AMOUNT_55000.subtract(AMOUNT_35000),AMOUNT_55000_PER));
            taxPers.add(addTax(AMOUNT_80000.subtract(AMOUNT_55000),AMOUNT_80000_PER));
            taxPers.add(addTax(taxBaseNum.subtract(AMOUNT_80000),TAX_GT_80000));
        }
        //计算个税扣除金额
        for (Map<String, BigDecimal> taxPer : taxPers) {
            BigDecimal amount=taxPer.get("amount");
            BigDecimal percent=taxPer.get("percent");
            taxAmount=taxAmount.add(amount.multiply(percent));
        }

        //计算打卡工资
//        shuiHou=shuiQian
//                .subtract(yanglaoAmount)
//                .subtract(yibaoAmount)
//                .subtract(shiyeAmount)
//                .subtract(gongjijinAmount)
//                .subtract(taxAmount);
        shuiHou=
                taxBaseNum
                .subtract(taxAmount);

        log.info("税前:{},税后:{},养老:{},医保:{},失业:{},公积金:{},个税:{}",
                shuiQian,
                shuiHou,
                yanglaoAmount,
                yibaoAmount,
                shiyeAmount,
                gongjijinAmount,
                taxAmount
        );
    }

    private Map<String,BigDecimal> addTax(BigDecimal amount,BigDecimal percent){
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("amount", amount);
        map.put("percent", percent);
        return map;
    }
}
