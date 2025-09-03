// 代码生成时间: 2025-09-04 00:42:08
package com.example.validation

import grails.validation.Validateable
import org.springframework.validation.Errors

// 表单数据验证器
class CustomConstraint implements Validateable {
    // 定义需要验证的字段
    String field1
    String field2

    // 静态约束条件
    static constraints = {
        field1(matches: /^[a-zA-Z]+$/, blank: false, message: "Field 1 must contain only letters")
        field2(matches: /^[0-9]+$/, blank: false, message: "Field 2 must contain only numbers")
    }

    // 验证方法，用于自定义验证逻辑
    boolean validate() {
        if (field1 == null || field1.isEmpty()) {
            errors.rejectValue('field1', 'default.empty', 'Field 1 cannot be empty')
            return false
        }

        if (field2 == null || field2.isEmpty()) {
            errors.rejectValue('field2', 'default.empty', 'Field 2 cannot be empty')
            return false
        }

        // 自定义验证逻辑，例如检查两个字段长度是否相等
        if (field1.size() != field2.size()) {
            errors.rejectValue('field1', 'size.mismatch', 'Field 1 and Field 2 must be of the same length')
            return false
        }

        return true
    }
}
