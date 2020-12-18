package io.kimmking.rpcfx.exception;

public class RpcfxException extends RuntimeException {

    private Integer code;

    private String message;

    public RpcfxException(String message)
    {
        this.message = message;
    }

    public RpcfxException(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }

    public RpcfxException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }
}
