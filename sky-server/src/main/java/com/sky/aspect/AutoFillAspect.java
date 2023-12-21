package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

        /**
         * 切入点
         */
        @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
        public void autoFillPointCut(){

        }

        @Before("autoFillPointCut()")
        public void autoFill(JoinPoint joinPoint){
                log.info("公共字段填充");
                //获取拦截方法上面的数据库操作类型
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
                AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得注解
                OperationType operationType = autoFill.value();//获取数据库类型

                Object[] args = joinPoint.getArgs();
                if (args == null || args.length == 0){
                        return;
                }
                Object arg = args[0];

                //数据
                LocalDateTime now = LocalDateTime.now();
                Long currentId = BaseContext.getCurrentId();

                //赋值
                if (operationType == OperationType.INSERT){
                        try {
                                Method setCreateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                                Method setCreateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                                Method setUpdateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                                Method setUpdateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                                setCreateTime.invoke(arg,now);
                                setCreateUser.invoke(arg,currentId);
                                setUpdateTime.invoke(arg,now);
                                setUpdateUser.invoke(arg,currentId);

                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
                if (operationType == operationType.UPDATE){
                        try {
                                Method setUpdateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                                Method setUpdateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                                setUpdateTime.invoke(arg,now);
                                setUpdateUser.invoke(arg,currentId);
                        } catch (Exception e) {
                                throw new RuntimeException(e);
                        }


                }
        }

}
