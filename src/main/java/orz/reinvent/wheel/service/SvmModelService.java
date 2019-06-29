package orz.reinvent.wheel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.reinvent.wheel.dao.SvmModelDao;
import orz.reinvent.wheel.entity.SvmModelEntity;
import orz.reinvent.wheel.utility.Svm_Hog;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName SvmModelService
 * @Description TODO
 * @Author mushi
 * @Date 2019/6/289:30
 * @Version 1.0
 **/
@Service
public class SvmModelService {

    @Autowired
    private SvmModelDao modelDao;

    @Autowired
    private Svm_Hog svm_hog;

    /**
     * 从controller中传入model的名称
     * param name
     * 调用算法获得url
     * param url
     */
    public void SaveModel(String modelName, int sample_count, ArrayList<String> img_path, ArrayList<Float> img_label){
        SvmModelEntity modelEntity = new SvmModelEntity();
        modelEntity.setName(modelName);
        String url = null;
        try {
            url = svm_hog.svm_train(modelName, sample_count, img_path, img_label);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("URL:"+url);
        modelEntity.setUrl(url);


        modelDao.save(modelEntity);
    }

    /**
     * @Auther 李韬
     * @Date 2019/6/29 0:04
     *
     * @Description
     * @Param [imgUrl, modelName]
     * imgUrl从controller中传入图片的地址，modelName是模型的名称
     * @return float
     **/
    public float useModel(String imgUrl, int modelId){

        //根据modelName从数据库中查询model的位置
        String modelUrl = modelDao.findUrlById(modelId);
        System.out.println("modelUrl:"+modelUrl);
        System.out.println("imgUrl:"+imgUrl);
        float result = svm_hog.svm_predict(modelUrl, imgUrl);

        return result;
    }
}
