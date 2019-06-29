package orz.reinvent.wheel.opencv_test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;
import org.opencv.objdetect.HOGDescriptor;

import java.io.*;
import java.util.ArrayList;

/**
 * @ClassName Svm_train
 * @Description TODO
 * 1.对训练图片进行预处理
 * 2.对于处理的图片进行hog特征提取
 * 3.使用svm分类器对特征进行分类形成模型
 * 4.根据模型对测试图片进行预测
 *
 *
 *
 * @Author 李韬
 * @Date 2019/4/28 17:56
 * @Version 1.0
 **/
public class Svm_train {


    public void svm_train() throws IOException {

        /************************
         * 数据准备阶段
         ************************/
        Integer iteration_num = 10000;
        //数据标签文件
        String traintxt = "C:\\Users\\mushi\\Desktop\\image\\train.txt";
        //图片的地址
        ArrayList<String> img_path = new ArrayList<String>();
        //图片的标签
        ArrayList<Float> img_label = new ArrayList<Float>();
//        static {
//            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        }
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(traintxt)), "UTF-8"));
        String linetxt = null;
        //计算一共又多少个数据
        Integer nline = 0;
        while((linetxt = br.readLine()) != null){
            nline++;
            //用空格分隔，得到图片名称跟标签
            String[] path_label = linetxt.split(" ");
            img_path.add(path_label[0]);
            img_label.add(Float.valueOf(path_label[1]).floatValue());
        }
        br.close();

        Integer sample_count = nline;
        System.out.println("一共"+sample_count+"组数据");

        //似乎是处理图像灰度
        Integer picture_feature_dim = 1764;
        //CvType是图像处理的基础常量参数
        //32F:浮点型32位图;32S:无符号的32位图
        Mat data_mat = new Mat(sample_count, picture_feature_dim, CvType.CV_32FC1);
        Mat res_mat = new Mat(sample_count, 1, CvType.CV_32SC1);


        /*****************
         * HOG
         *****************/

        ArrayList<float[]> descriptors = new ArrayList<float[]>();
        for (Integer i=0; i<img_path.size(); i++){
            System.out.println(img_path.get(i) + "\tprocess");
            Mat src = Imgcodecs.imread("C:\\Users\\mushi\\Desktop\\image\\"+img_path.get(i));
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
        //保存SVM模型？
        System.out.println(svm);
        svm.save("C:\\Users\\mushi\\Desktop\\image\\train_model\\train.xml");
    }


    public void svm_predict(){
        //读取测试集合相关内容
        String testtxt = "C:\\Users\\mushi\\Desktop\\image\\test\\test.txt";
        ArrayList<String> img_path = new ArrayList<String>();
        ArrayList<Float> img_label = new ArrayList<Float>();
        //需要修改
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SVM svm = SVM.create();
        SVM model = svm.load("C:\\Users\\mushi\\Desktop\\image\\train_model\\train.xml");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(testtxt)), "UTF-8"));
            String linetxt = null;
            Integer nline = 0;
            while((linetxt = br.readLine()) != null){
                nline++;
                String[] path_label = linetxt.split(" ");
                //System.out.println(path_label[0] + ' ' + path_label[1]);
                img_path.add(path_label[0]);
                //img_label.add(Float.valueOf(path_label[1]).floatValue());
            }
            br.close();

            //svm test start
            ArrayList<float[]> descriptors = new ArrayList<float[]>();
            for (Integer i=0; i<img_path.size(); i++){

                Mat src_test = Imgcodecs.imread("C:\\Users\\mushi\\Desktop\\image\\test\\"+img_path.get(i));
                if (src_test.empty()){
                    throw new Exception("no such picture");
                }
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
                System.out.println(img_path.get(i) + "\t" + p);
            }
        } catch (Exception e) {
            System.err.println("read err:" + e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Svm_train st = new Svm_train();
//        try {
//            st.svm_train();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        st.svm_predict();
    }

}
