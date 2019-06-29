package orz.reinvent.wheel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.reinvent.wheel.dao.PicDao;
import orz.reinvent.wheel.entity.PicEntity;

/**
 * @ClassName PicService
 * @Description TODO
 * @Author litao
 * @Date 2019/6/2723:44
 * @Version 1.0
 **/
@Service
public class PicService {

    @Autowired
    private PicDao picDao;


    /**
     * @Auther 李韬
     * @Date 2019/6/27 23:46
     *
     * @Description 将上传的图片和标签存储到数据库中
     * 从硬盘中读取相关的图片和训练集，然后存储到数据库中
     *
     *
     *
     * 应该是存储模型的位置，搞错了！！！
     * @Param
     * @return
     **/
    public void HandleTrainingFiles(String targetPath){

        PicEntity picEntity = new PicEntity();




        picDao.save(picEntity);
    }
    /**
     * @Auther 李韬
     * @Date 2019/6/27 23:58
     *
     * @Description 读取传过来的文件丢去训练
     * @Param []
     * @return void
     **/
    public void Handle(){

    }
}
