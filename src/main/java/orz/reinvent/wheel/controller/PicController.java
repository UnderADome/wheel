package orz.reinvent.wheel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import orz.reinvent.wheel.dao.SvmModelDao;
import orz.reinvent.wheel.entity.SvmModelEntity;
import orz.reinvent.wheel.service.SvmModelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PicController
 * @Description TODO
 * @Author 李韬
 * @Date 2019/4/1820:52
 * @Version 1.0
 **/
@Controller
public class PicController {

    @Autowired
    private SvmModelService svmModelService;

    @Autowired
    private SvmModelDao svmModelDao;


    /**
     * @Auther 李韬
     * @Date 2019/6/28 22:31
     *
     * @Description 已经沦为只为测试使用了
     * @Param [file, request, response]
     * @return java.lang.String
     **/
    @RequestMapping(value = "upload/load_test_pic", method = RequestMethod.POST)
    @ResponseBody
    public String loadTestPic(@RequestParam(value = "file", required = false) MultipartFile file,
                              HttpServletRequest request,
                              HttpServletResponse response){

        System.out.println(file == null);

        //这里有个坑，就是pic_path的最后面一定要写上两个杠，不然路径就会错，不能正确转移文件，很坑爹卧槽
        String pic_path = "C:\\Users\\mushi\\Desktop\\1\\";
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        File targetFile = new File(pic_path+ fileName);
        System.out.println(targetFile.getPath());
        if (!targetFile.getParentFile().exists()){
            System.out.println(targetFile+"不存在，新创建");
            targetFile.getParentFile().mkdirs();

        }
        try {

            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileUrl = fileName;
        return "图片存储路径："+fileUrl;
    }


    @RequestMapping(value = "upload/loadMultiPic", method = RequestMethod.POST)
    @ResponseBody
    public String loadMultiPic(HttpServletRequest request){

        List<MultipartFile> multiPics = ((MultipartHttpServletRequest) request).getFiles("MultiPic");
        List<MultipartFile> picLabel = ((MultipartHttpServletRequest) request).getFiles("PicLabel");
        System.out.println("图片数量:"+multiPics.size());
        System.out.println("标签文件数量:"+picLabel.size());
        //定义最终的图片文件的存储点，用标签文件名对路径进行命名
        String pic_path = "C:\\Users\\mushi\\Desktop\\train\\";
        String fileName = picLabel.get(0).getOriginalFilename().replaceAll("[.][^.]+$", "");
        String targetPath = pic_path+fileName;
        System.out.println(targetPath);
        //按照标签文件名称命名了存储文件位置
        File targetFile = new File(targetPath);
        if (!targetFile.exists())
            targetFile.mkdir();
        System.out.println(targetFile.getPath());

        ArrayList<String> img_path = new ArrayList<String>();
        ArrayList<Float> img_label = new ArrayList<Float>();


        try {
            picLabel.get(0).transferTo(new File(targetPath+"\\"+picLabel.get(0).getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //遍历处理图片
        for (MultipartFile file:multiPics){
            try {
                file.transferTo(new File(targetPath+"\\"+file.getOriginalFilename()));
                img_path.add(targetPath+"\\"+file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Integer sample_count = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(targetPath+"\\"+picLabel.get(0).getOriginalFilename())), "UTF-8"));
            String linetxt = null;

            while ((linetxt = br.readLine()) != null) {
                sample_count++;
                String[] path_label = linetxt.split(" ");
                //System.out.println(path_label[0] + ' ' + path_label[1]);
                //img_path.add(path_label[0]);
                //System.out.println("path_label[0]:"+path_label[0]);
                System.out.println(Float.valueOf(path_label[1]).floatValue());
                img_label.add(Float.valueOf(path_label[1]).floatValue());
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        svmModelService.SaveModel(fileName, sample_count, img_path, img_label);

        //改成一个新的页面，一进去就有模型可以选择，然后还可以上传
        //responsebody也要改掉
        return targetPath;

    }


    //不能接受int类型传值
    @RequestMapping(value = "/predict", method = RequestMethod.POST)
    @ResponseBody
    public String predict(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam String id){


        System.out.println("添加的图片是否为空？->>"+file == null);
        System.out.println(id);System.out.println(file.getOriginalFilename());


        //这里有个坑，就是pic_path的最后面一定要写上两个杠，不然路径就会错，不能正确转移文件，很坑爹卧槽
        String pic_path = "C:\\Users\\mushi\\Desktop\\1\\";
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        File targetFile = new File(pic_path+ fileName);
        System.out.println(targetFile.getPath());
        if (!targetFile.getParentFile().exists()){
            System.out.println(targetFile+"不存在，新创建");
            targetFile.getParentFile().mkdirs();
        }
        try {

            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgUrl = pic_path+fileName;
        if (svmModelService.useModel(imgUrl, Integer.valueOf(id)) > 0)
            return "预测结果为正";
        return "预测结果为负";
    }


    @RequestMapping(value = "/model")
    public String SvmModelIndex(Model model){

        List<SvmModelEntity> result = svmModelDao.findAll();
        model.addAttribute("models", result);

        return "model.html";
    }
}
