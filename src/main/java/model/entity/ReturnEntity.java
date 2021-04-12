package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/5 22:58
 * @description:
 * @modifiedBy By:
 * @version :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnEntity {
     int code;
     Object object;

     public void setEntity(int code, Object object){
          this.code=code;
          this.object=object;
     }
}
