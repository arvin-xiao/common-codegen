package com.wuxp.codegen.swagger2.model.evt;

import lombok.Data;

import javax.validation.constraints.NotNull;

      import com.wuxp.codegen.swagger2.model.evt.BaseEvt;

  /**
      * 创建订单
  **/


@Data
public class  CreateOrderEvt extends BaseEvt {

          /**
              *属性说明：订单ns，示例输入：test method
              *sn 约束条件：输入字符串的最小长度为：0，输入字符串的最大长度为：50
              *字段在java中的类型为：String
          **/
        private String sn;
          /**
              *属性说明：订单总价，示例输入：
              *totalAmount 约束条件：为必填项，不能为空
              *字段在java中的类型为：Integer
          **/
        private Integer total_amount;
}
