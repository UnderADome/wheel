package orz.reinvent.wheel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.reinvent.wheel.entity.ArticleEntity.Article;
import orz.reinvent.wheel.entity.Response.RArticle;
import orz.reinvent.wheel.entity.Response.RWhisper;
import orz.reinvent.wheel.service.ArticleService.ArticleService;
import orz.reinvent.wheel.service.ArticleService.WhisperService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局拦截文件
 * 命名为index的ftl不能直接跳转，原因为之，探索中
 *
 * 虽然我知道我下面的六个拦截器有四个很傻逼，我还是毅然决然的写了。因为这样最方便，我不会写前端代码..............
 */


/**
 * @RestController注解表示该类中的所有方法都会返回json格式。
 *
 * @Controller返回的是字符格式，实现页面跳转。
 */
@Controller
public class ApplicationController {

    private Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ArticleService articleService;
    @Autowired
    private WhisperService whisperService;


    @RequestMapping("/")
    public String Index(HttpServletRequest request, Model model){
        //index页面直接默认给出page=1，即第一页
        model.addAttribute("articles", articleService.selectArticleByPage(1, 5));
        //request.setAttribute("articles", articleService.articleQueryAll() );
        return "homepage/iindex";
    }
    //要求前端必须传递页号
    //同时注意不能用Model向前端传递数值，因为Model是对应页面的传输方式
    @RequestMapping("/index")
    @ResponseBody
    public List<RArticle> IndexWithPage(HttpServletRequest request, @RequestParam("page") String page){
        logger.info("page="+page);
        List<RArticle> result = articleService.selectArticleByPage(Integer.parseInt(page), 5);
        return result;
    }


    @RequestMapping("/about")
    public String About(Model model, HttpServletRequest request){
        return "homepage/about";
    }
    @RequestMapping("/album")
    public String Album(Model model, HttpServletRequest request){
        return "homepage/album";
    }
    @RequestMapping("/leacots")
    public String Leacots(Model model, HttpServletRequest request){
        return "homepage/leacots";
    }



    @RequestMapping("/whisper")
    public String Whisper(Model model, HttpServletRequest request){
        model.addAttribute("whispers", whisperService.selectWhisperByPage(1, 3));
        return "homepage/whisper";
    }

    @RequestMapping("/whisper/page")
    @ResponseBody
    public List<RWhisper> WhisperWithPage(HttpServletRequest request, @RequestParam("page") String page){
        List<RWhisper> result = whisperService.selectWhisperByPage(Integer.parseInt(page), 3);
        return result;
    }

    @RequestMapping("/details")
    public String Details(@RequestParam("id") String id, Model model){
        RArticle result = articleService.selectArticleById(Integer.parseInt(id));
        model.addAttribute("article", result);
        System.out.println(result.getItem());
        return "homepage/details";
    }

    @RequestMapping("/hello")  //必须用双引号
    @ResponseBody
        //body返回数据
    String SayHello(){
        return "Hello.";
    }

}
