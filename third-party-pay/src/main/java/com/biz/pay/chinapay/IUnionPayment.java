package com.biz.pay.chinapay;

/**
 * Created by tanghaibin on 2017/2/28.
 */
public interface IUnionPayment {

     enum NotifyType {

        QUERY("00", "查询交易"),

        CONSUMPTION("01", "消费"),

        RETURN("04", "退货");

        private String name;

        private String description;

        NotifyType(String name, String description) {
            this.name = name;
            this.description = description;
        }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public String getDescription() {
             return description;
         }

         public void setDescription(String description) {
             this.description = description;
         }
     }
}
