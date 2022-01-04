package com.wuxp.codegen.swagger2.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

      import com.wuxp.codegen.swagger2.domain.Order;
      import com.wuxp.codegen.swagger2.enums.Sex;
      import java.util.List;
      import java.util.Map;
      import com.wuxp.codegen.swagger2.enums.ExampleEnum;

  /**
      * 用户
  **/


@Data
public class  User {

          /**
              *属性说明：id，示例输入：
              *字段在java中的类型为：Long
            **/
        private Long id;
          /**
              *属性说明：名称，示例输入：
              *字段在java中的类型为：String
            **/
        private String name;
          /**
              *属性说明：年龄，示例输入：
              *字段在java中的类型为：Integer
            **/
        private Integer age;
          /**
              *属性说明：订单列表，示例输入：
              *字段在java中的类型为：List
              *字段在java中的类型为：Order
            **/
        private List<Order> orderList;
          /**
              *属性说明：性别，示例输入：
              *字段在java中的类型为：Sex
            **/
        private Sex sex;
          /**
              *属性说明：其他，示例输入：
              *字段在java中的类型为：Map
              *字段在java中的类型为：String
              *字段在java中的类型为：String
            **/
        private Map<String,String> other;
          /**
              *属性说明：其他2，示例输入：
              *字段在java中的类型为：Map
              *字段在java中的类型为：Object
              *字段在java中的类型为：Object
            **/
        private Map<Object,Object> other2;
          /**
              *属性说明：list，示例输入：
              *字段在java中的类型为：List
              *字段在java中的类型为：Object
            **/
        private List<Object> list;
          /**
              *字段在java中的类型为：List
              *字段在java中的类型为：Object
            **/
        private List<Object> list2;
          /**
              *属性说明：myFriends，示例输入：
              *字段在java中的类型为：String
            **/
        private String myFriends;
          /**
              *属性说明：example enum，示例输入：
              *字段在java中的类型为：ExampleEnum
            **/
        private ExampleEnum exampleEnum;
          /**
              *字段在java中的类型为：Boolean
            **/
        private Boolean boy;
}
