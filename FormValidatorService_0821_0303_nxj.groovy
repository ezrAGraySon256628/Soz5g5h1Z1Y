// 代码生成时间: 2025-08-21 03:03:16
package com.example.service

import grails.validation.Validateable
import grails.validation.ValidationException
import org.springframework.validation.Errors

/**
 * 表单数据验证器，用于验证表单数据是否符合规定格式
 * @author Your Name
 */
class FormValidatorService {

    /**
     * 验证表单对象
     * @param form 要验证的表单对象
     * @return true 如果验证通过，false 如果验证失败
     */
    boolean validateForm(def form) {
        boolean isValid = true
        try {
            // 调用Grails的验证框架进行验证
            validate(form)
        } catch (ValidationException e) {
            // 捕获验证异常，设置isValid为false
            isValid = false
        }
        return isValid
    }

    /**
     * 定义验证规则
     * @param obj 要验证的对象
     */
    static constraints = {
        obj(nullable: false)
    }
}

/**
 * 定义一个可验证的表单类
 * @author Your Name
 */
class MyForm implements Validateable {
    String field1
    String field2
    
    /**
     * 验证表单字段
     * @param errors 验证错误集合
     */
    void validate(Errors errors) {
        // 添加字段1的验证规则
        if (field1 == null || field1.isEmpty()) {
            errors.rejectValue('field1', 'default.empty', 'Field 1 cannot be empty')
        } else if (!field1.matches('^[A-Za-z]+$')) {
            errors.rejectValue('field1', 'default.invalid', 'Field 1 must contain only letters')
        }
        
        // 添加字段2的验证规则
        if (field2 == null || field2.isEmpty()) {
            errors.rejectValue('field2', 'default.empty', 'Field 2 cannot be empty')
        } else if (!field2.isNumber()) {
            errors.rejectValue('field2', 'default.invalid', 'Field 2 must be a number')
        }
    }
}
