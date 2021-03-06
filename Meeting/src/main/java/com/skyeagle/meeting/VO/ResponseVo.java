package com.skyeagle.meeting.VO;

import com.skyeagle.meeting.Enum.ResponseEnum;
import lombok.Data;

/**
 * @projectName:meeting
 * @packageName:com.skyeagle.meeting.VO
 * @className:ResponseVO
 * @Description:[一句话描述该类的功能]
 * @Author:lvyang
 * @CreateDate:2021-2-1 21:28
 */
@Data
public class ResponseVo<T> {
    private Integer status;
    private String msg;
    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }


    public static <T> ResponseVo<T> success(T msgordata){
        return new ResponseVo<T>(ResponseEnum.SUCCESS.getCode(),msgordata);
    }

    public static <T> ResponseVo<T> success(){
        return new ResponseVo<T>(ResponseEnum.SUCCESS.getCode(),"成功");
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum){
        return new ResponseVo<T>(responseEnum.getCode(),responseEnum.getDesc());
    }


    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, String msg){
        return new ResponseVo<T>(responseEnum.getCode(),msg);
    }

}
