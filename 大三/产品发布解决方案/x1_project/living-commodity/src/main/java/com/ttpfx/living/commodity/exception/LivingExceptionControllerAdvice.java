package com.ttpfx.living.commodity.exception;

import com.ttpfx.common.exception.LivingCodeEnum;
import com.ttpfx.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ttpfx
 * @date 2023/2/15
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class LivingExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(item -> {
            errorMap.put(item.getField(), item.getDefaultMessage());
        });
        return R.error(LivingCodeEnum.INVALID_EXCEPTION.getCode(),
                LivingCodeEnum.INVALID_EXCEPTION.getMsg()).put("data", errorMap);
    }
}
