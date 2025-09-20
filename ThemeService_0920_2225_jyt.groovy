// 代码生成时间: 2025-09-20 22:25:35
package com.example\
\
import grails.transaction.Transactional\
\
@Transactional\
class ThemeService {\
    // 存储当前主题\
    private static final String CURRENT_THEME = 'currentTheme'\
\
    // 获取当前主题\
    String getCurrentTheme() {\
        return User.findByUsername(Session.user.username).theme\
    }\
\
    // 设置当前主题\
    void setCurrentTheme(String theme) {\
        try {\
            User user = User.findByUsername(Session.user.username)\
            if (user) {\
                user.theme = theme\
                user.save(flush: true)\
            } else {\
                throw new Exception('User not found')\
            }\
        } catch (Exception e) {\
            log.error('Error setting theme: ' + e.message)\
            throw e\
        }\
    }\
}