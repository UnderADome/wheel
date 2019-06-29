package orz.reinvent.wheel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WheelApplication {

<<<<<<< HEAD
	static {
		try{
			//解决重启时产生的dll冲突。
			//tomcat在重启的时候不是全部重启，会有部分内容保留在tomcat中，dll就是其中之一。
			System.load("C:/OpenCV/opencv/build/java/x64/opencv_java401.dll");
		}catch (UnsatisfiedLinkError ignore){
			//忽略此异常
			//当tomcat中存在dll时，直接忽略该异常，让程序直接执行
		}
	}
	public static void main(String[] args) {
		//-Djava.library.path=C:/OpenCV/opencv/build/java/x64;C:/OpenCV/opencv/build/x64/vc15/bin
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		SpringApplication.run(WheelApplication.class, args);
	}
=======
    public static void main(String[] args) {
        SpringApplication.run(WheelApplication.class, args);
    }
>>>>>>> 5158f52c5931817c90ac861c504210a306fb0ae6

}
