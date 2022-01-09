package com.codeTool.error;

/**
 * @author: Ansen
 * @create: 2022-01-09 15:33
 **/


public enum ErrorCode {
    ERROR_SUCCESS(0, "成功"),
    ERROR_SERVER_ERROR(-1, "服务内部错误"),
    ERROR_BUSY(-2, "系统繁忙，请稍后重试"),
    ERROR_NOT_FOUND(-3, "未找到或不存在"),
    ERROR_HANDLE_TIMEOUT(-4, "超时"),
    ERROR_INVALID_PARAM(-5, "无效参数"),
    ERROR_CHECK_CODE(-6, "验证码错误或已失效"),
    ERROR_MEMORY_OUT(-7, "内存溢出"),
    ERROR_OBJECT_EXIST(-8, "对象已存在"),
    ERROR_USER_PASSWORD(-9, "用户名或密码不正确"),
    ERROR_DELETE_FAIL(-10, "删除对象失败"),
    ERROR_INVALID_ARRAY(-11, "无效列表"),
    ERROR_NOT_LOGIN(-12, "未登录或会话过期"),
    ERROR_NOT_SUPPORT(-13, "接口不支持"),
    ERROR_NO_RIGHT(-14, "权限不足"),
    ERROR_NOT_AVAILABLE(-15, "服务暂不可用"),
    ERROR_USER_DISABLED(-16, "当前用户已禁用"),
    ERROR_IP_NOT_RIGHT(-17, "访问IP无权限"),
    ERROR_IMPORT_ERROR(-20, "档案导入失败"),
    ERROR_EXPORT_ERROR(-21, "档案导出失败");
    private Integer error;
    private String description;

    private ErrorCode(Integer error, String description){
        this.error = error;
        this.description = description;
    }

    public Integer getError(){
        return this.error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    static public String getDescription(Integer error) {
        String desc = "";
        if(ERROR_SUCCESS.getError().equals(error)){
            desc = ERROR_SUCCESS.getDescription();
        }
        if(ERROR_SERVER_ERROR.getError().equals(error)){
            desc = ERROR_SERVER_ERROR.getDescription();
        }
        if(ERROR_BUSY.getError().equals(error)){
            desc = ERROR_BUSY.getDescription();
        }
        if(ERROR_NOT_FOUND.getError().equals(error)){
            desc = ERROR_NOT_FOUND.getDescription();
        }
        if(ERROR_HANDLE_TIMEOUT.getError().equals(error)){
            desc = ERROR_HANDLE_TIMEOUT.getDescription();
        }
        if(ERROR_INVALID_PARAM.getError() == error){
            desc = ERROR_INVALID_PARAM.getDescription();
        }
        if(ERROR_CHECK_CODE.getError() == error){
            desc = ERROR_CHECK_CODE.getDescription();
        }
        if(ERROR_MEMORY_OUT.getError().equals(error)){
            desc = ERROR_MEMORY_OUT.getDescription();
        }
        if(ERROR_OBJECT_EXIST.getError().equals(error)){
            desc = ERROR_OBJECT_EXIST.getDescription();
        }
        if(ERROR_USER_PASSWORD.getError().equals(error)){
            desc = ERROR_USER_PASSWORD.getDescription();
        }
        if(ERROR_DELETE_FAIL.getError().equals(error)){
            desc = ERROR_DELETE_FAIL.getDescription();
        }
        if(ERROR_INVALID_ARRAY.getError().equals(error)){
            desc = ERROR_INVALID_ARRAY.getDescription();
        }
        if(ERROR_NOT_LOGIN.getError().equals(error)){
            desc = ERROR_NOT_LOGIN.getDescription();
        }
        if(ERROR_NOT_SUPPORT.getError().equals(error)){
            desc = ERROR_NOT_SUPPORT.getDescription();
        }
        if(ERROR_NO_RIGHT.getError().equals(error)){
            desc = ERROR_NO_RIGHT.getDescription();
        }
        if(ERROR_NOT_AVAILABLE.getError().equals(error)){
            desc = ERROR_NOT_AVAILABLE.getDescription();
        }
        return desc;
    }
}

