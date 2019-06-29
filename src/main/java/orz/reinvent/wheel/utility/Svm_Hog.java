package orz.reinvent.wheel.utility;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;
import org.opencv.objdetect.HOGDescriptor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * @ClassName svm
 * @Description TODO
 * @Author litao
 * @Date 2019/6/279:03
 * @Version 1.0
 **/
@Service
public class Svm_Hog {

     /*
      * @Auther 李韬
      * @Date 2019/6/27 9:30
      *
      * @Description
      * @Param [ModelName 训练模型的名字，直接作为路劲使用,
      *         sample_count 一共有多少个图片参与训练,
      *         img_path 图片的路径集合，要给完整路径,
      *         img_label 图片的标签集合]
      * @return void
      **/
    public String svm_train(String ModelName, int sample_count, ArrayList<String> img_path, ArrayList<Float> img_label) throws IOException {

        /************************
         * 数据准备阶段
         ************************/

        System.out.println(Core.NATIVE_LIBRARY_NAME);
        Integer iteration_num = 10000;

        //似乎是处理图像灰度
        Integer picture_feature_dim = 1764;
        //CvType是图像处理的基础常量参数
        //32F:浮点型32位图;32S:无符号的32位图
        //System.out.println(Core.NATIVE_LIBRARY_NAME);
        System.out.println(CvType.CV_32FC1);
        System.out.println(sample_count);
        System.out.println(picture_feature_dim);
        //opencv_core.Mat mat = new opencv_core.Mat();
        Mat data_mat = new Mat(sample_count, picture_feature_dim, CvType.CV_32FC1);
        Mat res_mat = new Mat(sample_count, 1, CvType.CV_32SC1);

        /*****************
         * HOG
         *****************/

        ArrayList<float[]> descriptors = new ArrayList<float[]>();
        for (Integer i=0; i<img_path.size(); i++){
            System.out.println(img_path.get(i) + "\t正在计算");

            //要传进来完整路径
            Mat src = Imgcodecs.imread(img_path.get(i));
            if (src.empty()){
                System.out.println(img_path.get(i));
                //throw new Exception("no such picture");
            }
            Mat dst = new Mat();
            Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);
            Mat training = dst.clone();
            Imgproc.resize(dst, training, new Size(64, 64));

            HOGDescriptor hog = new HOGDescriptor(
                    new Size(64, 64), new Size(16, 16),
                    new Size(8, 8), new Size(8, 8), 9);

            MatOfFloat descriptorsOfMat = new MatOfFloat();
            hog.compute(training, descriptorsOfMat);
            float[] descriptor = descriptorsOfMat.toArray();
            descriptors.add(descriptor);

        }

        for (Integer m=0; m<descriptors.size(); m++){
            for (int n=0; n<descriptors.get(m).length; n++){
                //System.out.println(descriptors.get(i)[j]);
                data_mat.put(m, n, descriptors.get(m)[n]);
            }
            res_mat.put(m, 0, img_label.get(m));
        }


        /*****************
         * SVM
         *****************/

        SVM svm = SVM.create();
        svm.setType(SVM.C_SVC);
        svm.setKernel(SVM.LINEAR);
        svm.setTermCriteria(new TermCriteria(TermCriteria.MAX_ITER, iteration_num, 1E-6));
        svm.train(data_mat, Ml.ROW_SAMPLE, res_mat);
        //svm.save(ModelName+".xml");
        svm.save("C:\\Users\\mushi\\Desktop\\train\\"+ModelName+"\\"+ModelName+".xml");
        System.out.println("模型位置:"+"C:\\Users\\mushi\\Desktop\\train\\"+ModelName+"、、"+ModelName+".xml");
        return "C:\\Users\\mushi\\Desktop\\train\\"+ModelName+"\\"+ModelName+".xml";
    }




    public float svm_predict(String modelUrl, String imgUrl){

        SVM svm = SVM.create();
        SVM model = svm.load(modelUrl);


            //svm test start
        ArrayList<float[]> descriptors = new ArrayList<float[]>();

        //读取用来测试的图像
        Mat src_test = Imgcodecs.imread(imgUrl);
        Mat dst_test = new Mat();

        Imgproc.cvtColor(src_test, dst_test, Imgproc.COLOR_BGR2GRAY);
        Mat testimg = dst_test.clone();
        Imgproc.resize(dst_test, testimg, new Size(64, 64));


        HOGDescriptor hog = new HOGDescriptor(new Size(64,64), new Size(16,16),
                new Size(8,8), new Size(8,8), 9);
        MatOfFloat descriptorsOfMat = new MatOfFloat();
        hog.compute(testimg, descriptorsOfMat);
        float[] descriptor = descriptorsOfMat.toArray();


        Mat testmat = new Mat(1, 1764, CvType.CV_32FC1);
        for (int j=0; j<descriptor.length; j++){
            testmat.put(0, j, descriptor[j]);
        }
        float p = model.predict(testmat);
        System.out.println("预测：");
        System.out.println(imgUrl + "\t" + p);

        return p;
    }


}
