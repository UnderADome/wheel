# wheel
项目预计长期维护
取名为wheel是指：
这个系统本身是重复造轮子，其实已经有很多功能是已经被一些厂家封装好的，在这里大部分功能都由我自己编写

# 项目名称：多用户通用图像分类系统

### 技术选型（规划）
前端框架：Node/Vue \
前端页面：EasyUI/LayUI \
后端框架：Spring全家桶（JPA、MVC） \
权限框架：Securety \
数据库：Redis/MySQL
其他：WebService/Restful API
图像：OpenCV4.0

### 详细功能（规划）
1. 多用户管理
   系统管理员、普通管理员
2. 每个用户都可以添加、删除不同的应用场景（设置分类类别及名称）
   2.1 用户创建了应用场景之后需要自己上传训练照片（打好标签）
   2.2 后台采用SVM+HOG特征或者卷积神经网络（未定）进行训练
3. 用户在使用该系统训练模型时，有两种上传图片的方式：
   3.1 调用摄像头
   3.2 本地上传（API上传->另编写一套程序用来上传图片到服务器）
   注：当采用摄像头时，要设置采样频率
4. 当建立人脸识别程序时，要知名是否要进行人脸检测（包含检测、定位和分割）
5. 使用消息中间件JMS（ActiveMQ）发布定期分类的结果