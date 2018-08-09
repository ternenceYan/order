package com.imooc.order.VO;

public class ResultVOUtil {
    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("ok");
        resultVO.setData(data);
        return resultVO;
    }
}
