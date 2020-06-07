
<table border = "1">
    <caption><strong>项目技术栈</strong></caption>
    <tr>
        <td>主栈</td>
        <td>SpringBoot 2.x</td>
        <td>LayUI</td>
        <td>thymeleaf</td>
    </tr> 
    <tr>
        <td>数据库</td>
        <td>MySQL 8.0</td>
        <td>Spring Data JPA</td>
        <td></td>
    </tr>
    <tr>
        <td>图像处理</td>
        <td>OpenCV 4.x</td>
        <td></td>
        <td></td>
    </tr>
    <!--    
    <tr>
        <td></td>
        <td></td>
    </tr>
    -->
</table>




    
    config
        # Security框架配置（权限路由配置)
        SpringSecurityConfig 

    controller
        LoginController
        PicController
        TestController
        UserController

    dao
        PicDao
        SvmModelDao
        T_UserDao
        UserDao

    entity
        PicEntity
        RoleEntity
        SvmModelEntity
        T_UserEntity
        UserEntity

    service
        LoginService
        MyUserService
        PicService
        SvmModelService

    utility
        # Svm_Hog模型训练
        Svm_Hog

    WheelApplication
