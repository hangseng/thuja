package com.thuja.error;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author: Ansen
 * @create: 2022-01-09 15:31
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnValue<T> {

    // 错误
    private Integer error = 0;
    // 错误描述
    private String description = "";
    // 返回值【当error为ERROR_NO_SUCCESS才有可能返回值-判断值是否为空】
    private T value;

    // 成功不带返回值
    public ReturnValue(){
        this.error = ErrorCode.ERROR_SUCCESS.getError();
        this.description = "成功";
    }

    // 成功带返回值
    public ReturnValue(T value){
        if(null == value){
            this.error = ErrorCode.ERROR_NOT_FOUND.getError();
            this.description = "没有找到你需要的资源";
        } else {
            this.error = ErrorCode.ERROR_SUCCESS.getError();
            this.description = "成功";
            this.value = value;
        }
    }

    // 返回错误
    public ReturnValue(ErrorCode error){
        this.error = error.getError();
        this.description = error.getDescription();
    }

    // 返回错误--对错误描述进行更改
    public ReturnValue(ErrorCode error, String description){
        this.error = error.getError();
        this.description = description;
    }

    // 返回错误
    public ReturnValue(Integer error){
        this.error = error;
        this.description = ErrorCode.getDescription(error);
    }

    public ReturnValue(Integer error, String description){
        this.error = error;
        this.description = description;
    }

    public Integer getError() {
        return error;
    }

    public boolean success(){
        return error.equals(ErrorCode.ERROR_SUCCESS.getError());
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


}
