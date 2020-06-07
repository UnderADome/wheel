package orz.reinvent.wheel.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.reinvent.wheel.dao.ArticleDao.WhisperQueryDao;
import orz.reinvent.wheel.entity.Response.RWhisper;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Service
public class WhisperService {

    @Autowired
    private WhisperQueryDao whisperQueryDao;

    public List<RWhisper> selectWhisperByPage(int page, int pagesize){
        List<Object[]> objList = whisperQueryDao.selectWhisperByPage(page, pagesize);
        for (int i=0; i<objList.size(); i++){
            System.out.println(objList.get(i));
            for (int j=0; j<objList.get(i).length; j++){
                System.out.println("-"+objList.get(i)[j]);
            }

            System.out.println(objList.get(i)[0]);
            System.out.println("++");
        }

        List<RWhisper> result = new ArrayList<RWhisper>();
        for (int i=0; i<objList.size(); i++){
            Object[] objects = objList.get(i);
            for (int j=0; j<objects.length; j++){
                result.add((RWhisper)objects[j]);
            }

        }

        return result;
    }
}
