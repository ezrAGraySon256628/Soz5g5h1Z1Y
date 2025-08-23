// 代码生成时间: 2025-08-23 08:05:51
class UserLoginService {

    /**
     * 用户登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return Map 包含登录结果和消息
     */
    def login(String username, String password) {
        // 检查用户名和密码是否为空
        if (!username || !password) {
            return [success: false, message: '用户名和密码不能为空']
        }

        // 根据用户名查询用户信息
        def user = User.findByUsername(username)

        // 检查用户是否存在
        if (!user) {
            return [success: false, message: '用户不存在']
        }

        // 检查密码是否正确
        if (user.password != password) {
            return [success: false, message: '密码错误']
        }

        // 登录成功
        return [success: true, message: '登录成功']
    }

    /**
     * 注册新用户
     *
     * @param username 用户名
     * @param password 密码
     * @return Map 包含注册结果和消息
     */
    def register(String username, String password) {
        // 检查用户名和密码是否为空
        if (!username || !password) {
            return [success: false, message: '用户名和密码不能为空']
        }

        // 检查用户名是否已经存在
        def existingUser = User.findByUsername(username)
        if (existingUser) {
            return [success: false, message: '用户名已存在']
        }

        // 创建新用户
        def newUser = new User(username: username, password: password)

        // 保存新用户到数据库
        try {
            newUser.save()
            return [success: true, message: '注册成功']
        } catch (Exception e) {
            // 处理异常
            return [success: false, message: '注册失败: ' + e.message]
        }
    }
}
