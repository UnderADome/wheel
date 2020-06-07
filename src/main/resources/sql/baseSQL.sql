show databases;


create database wheel_v1;
use wheel_v1;
show tables;

describe t_user;

select * from t_user;

describe id_generator;
select * from id_generator;

select * from user;

describe role_entity;

describe hibernate_sequence;
#-----------------------------------------------------------

show databases;

# drop table user;

create table test_user(
id int,
username char(4),
age int,
customerid int
);
insert into test_user values (1, "张三", 12, 123123);
insert into test_user values (2, "李四", 34, 234234);
insert into test_user values (3, "丽萨", 22, 345345);
insert into test_user values (4, "飘飘", 56, 456456);
insert into test_user values (5, "mm", 56, 456456);




#正常来讲，存储大文本应该用text类型或者BLOB，以后再探讨
create table article(
id int primary key auto_increment, #自增长
item int, #文章类别，本来应该是要求这里可以存储多种文章类别的，但是不知道怎么处理
title varchar(500),
article_content varchar(500), #文章内容
#create_time time #保留字段
pic varchar(500), #图片存储位置
user_id int,
create_time timestamp
);
#mysql添加列
# alter table article add column user_id int;
# alter table article add column create_time timestamp;
# 写法错误
# alter table article alter column article_content varchar(1000);
alter table article modify article_content varchar(1000);

describe article;


insert into article values(null, 1, "大型网站技术架构：摘要与读书笔记", "花了几个晚上看完了《大型网站技术架构》这本书，个人感觉这本书的广度还行，
                  	深度还有些欠缺（毕竟只有200页左右）。但是作为一个缺乏大型网站技术的IT民工，看完一遍还是很有收获的，
                  	至少对一个网站的技术演进、需要解决的问题有了一个全面的认识。文中也有一些作者个人的心得、感悟、总结，我觉得还是很中肯的。", "res/img/sy_img1.jpg",
                    123123, now());

insert into article values(null, 1, "高可用Redis服务架构分析与搭建", "基于内存的Redis应该是目前各种web开发业务中最为常用的key-value数据库了，我们经常在业务中用其存储用户登陆态（Session存储），
                  	加速一些热数据的查询（相比较mysql而言，速度有数量级的提升），做简单的消息队列（LPUSH和BRPOP）、订阅发布（PUB/SUB）系统等等。
                  	规模比较大的互联网公司，一般都会有专门的团队，将Redis存储以基础服务的形式提供给各个业务调用。", "res/img/sy_img2.jpg",
                    123123, now());

insert into article values(null, 1, "一组 Redis 实际应用中的异常场景及其根因分析和解决方案", "我总结了一组 Redis 实际应用中遇到的异常场景，如 Redis 进程无法拉起，故障倒换失败，Slot 指派失败等，并针对这些异常场景给出了根因分析和可供参考的解决方案。
                  	如果你对 Redis 感兴趣并且在工作中可能使用 Redis，本文介绍的“踩坑”案例值得一看。", "res/img/sy_img3.jpg",
                    123123, now());

insert into article values(null, 1, "穷人的分布式网络", "KCP是一个快速可靠协议，能以比 TCP浪费10%-20%的带宽的代价，换取平均延迟降低 30%-40%，且最大延迟降低三倍的传输效果。纯算法实现，并不负责底层协议（如UDP）的收发，
                  	需要使用者自己定义下层数据包的发送方式，以 callback的方式提供给 KCP。 连时钟都需要外部传递进来，内部不会有任何一次系统调用。", "res/img/sy_img4.jpg",
                    234234, now());

insert into article values(null, 1, "该怎么向别人介绍你们的系统架构？", "每个人都会有自己的架构认知，根据自己的接触的内容来总结。系统分为用户中心、营销中心、商品中心…… 这是产品经理说的；
                  	我们的系统用了三层架构，用了SSM框架…… 这是程序员说的；用户说 我们系统有后台，前台，商品上下架功能，用户管理功能。", "res/img/sy_img5.jpg",
                    234234, now());

insert into article values(null, 5, "测试文章1", "1测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试
					测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", "res/img/sy_img5.jpg",
                    234234, now());

insert into article values(null, 3, "测试文章2", "2测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试
					测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", "res/img/sy_img5.jpg",
                    123123, now());


insert into article values(null, 2, "测试文章3", "3测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试
					测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", "res/img/sy_img5.jpg",
                    123123, now());


insert into article values(null, 2, "测试文章4", "4测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试
					测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", "res/img/sy_img5.jpg",
                    123123, now());


insert into article values(null, 3, "测试文章5", "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试
					测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", "res/img/sy_img5.jpg",
                    123123, now());


insert into article values(null, 4, "测试文章6", "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试
					测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", "res/img/sy_img5.jpg",
                    123123, now());


insert into article values(null, 5, "测试文章7", "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试
					测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", "res/img/sy_img5.jpg",
                    123123, now());

insert into article values(null, 6,
"爷爷买街边小吃已经30多年",
'<p>爷每天5点起床，去鸡行里选鸡，选的是大小均匀的老母鸡，送到家里从杀鸡放血开始，鸡血要留着，倒在有盐水的碗里，烧好开水拔了毛连鸡嘴巴鼻子里的粘液都要挤出来。鸡洗干净六只鸡放到一个锅里炖，要炖一个上午，要注意火候鸡皮不能裂。</p>
<img src="res/img/wz_img1.jpg">
                  <p>炖好的鸡挂在架子上晾干，再一片片剁开，每只鸡剁的块数都是一样，然后把鸡肉放到提前调好的水中腌两个小时，水里面的调料也是爷爷自己去市场买来磨成粉的。锅里还有剩的鸡汤，用鸡汤煮好面叶上面漂着一层绿油油的葱花。</p>
                  <p>以前爷爷都是用扁担一头面叶一头板鸡挑到街上摊位上卖，现在爷爷年纪大了，只能用三轮车推着。摊位摆好奶奶就会慢悠悠的拎着一个保温桶和一个小红桶过来，保温桶里装的是卤鸡蛋，小红桶留着装别人吃剩的骨头喂狗。他们只有晚上出摊，收完摊已经要9点钟了，回去还要洗一天用的抹布和衣服，爷爷奶奶都非常爱干净，摊上的所有抹布纱布都是白色的每天洗的一个油点都看不到。9年前奶奶去世了，爷爷奶奶青梅竹马感情非常好，奶奶去世的时候爷爷甚至还要求过把奶奶埋在院子里。现在爷爷有时候会偷偷跟我说，你奶奶昨天回来看我了，她还给我掖被子，我知道是她，我跟她说你别挂念我，我好的很 跑题了。</p>
                  <p>昨天我给爷爷打电话，他跟我说他卖板鸡有人给他拍照，他问别人你拍我干嘛，那个人回答他说我给你照片放到网上，你生意会更好，我爷爷说你不用放网上我都不够卖的。</p>'
                    , "res/img/wz_img1.jpg", null, now());

create table article_item(
id int primary key auto_increment,
item varchar(10)
);

insert into article_item values(null, "架构文摘"); #1
insert into article_item values(null, "旅游杂记"); #2
insert into article_item values(null, "前端框架"); #3
insert into article_item values(null, "大数据"); #4
insert into article_item values(null, "数据库应用"); #5
insert into article_item values(null, "散文札记"); #6


select * from article;

select article.id, article_item.item, article.title, article.article_content, article.pic from article, article_item where article.item = article_item.id;

select article.id, article_item.item, article.title, article.article_content, article.pic from
            article, article_item where article.item = article_item.id;

select article.id, article_item.item, article.title, article.article_content, article.pic from article, article_item
			where article.item = article_item.id and article.id = 2;



create table whisper(
id int primary key auto_increment,
#user_id int,
create_time timestamp,
comment_times int, #评论数
#comment_id int,
thumbsup int, #点赞数
#thumbsup_id int
content varchar(500),
img varchar(500)
);

insert into whisper values(null, null, 1200, 1200, "　　一直听说牛油果吃起来像肥皂、肥肉，虽然很难吃，但是价格却很贵，我还是想尝试一下。今天公司新到了新西兰牛油果，这是新西兰牛油果是第一次在中国上市，个头比普通牛油果大了一倍，被誉为“超牛果”。好奇心驱使我尝了一颗，第一次吃牛油果没有见识，切开牛油果费了好大劲，切成了这样。", '<img src="res/img/wy_img1.jpg">');
insert into whisper values(null, null, 12, 100, "　　“意境”是书法艺术所表现的精神境界，即作者以其笔墨技巧，表达对自然界和生活现象的观察、思考、感受，通过作品抒发自己思想、意趣、性灵、情操。意境越高，越能显示美的魅力。只有技术和法度而缺乏意境，称之为“字匠”。凡是造诣极高的书家，无不以意境、神韵为书道之最高目标。", '<img src="res/img/wy_img2.jpg"><img src="res/img/wy_img3.jpg"><img src="res/img/wy_img4.jpg">');
insert into whisper values(null, null, 430, 251, "　　这些书家作品，充满意境之美，或以驰骋纵横之志，或以抒发郁结之怀，凡“喜怒、窘穷、忧伤、怨恨、思慕、酣醉、无聊、不平、有动于心”，必以书法发之。意境之美是富于深刻内容的美，是建筑于书法法度基础上的美。", '<img src="res/img/wy_img5.jpg">');
insert into whisper values(null, null, 345, 1465, "　　你是我久久设想 而未落笔的 执着故事的 一个雏形 在一个陌生古城的小巷 叮咚一声 隐约在烟雨。", '<img src="res/img/wy_img1.jpg">');
insert into whisper values(null, null, 2354, 153, "　　游子的千层底里缝进的是亲情，黄鹤楼上遥望不归的是友情，千里孤坟埋不住的是爱情。敢问世间情为何物？情是人生的灵魂，是人生妙章中最重要的一笔。没有亲情，犹如酷寒的冬季没有结束的日期；没有友情，犹如漂泊不定的小舟没有避风的港湾；没有爱情，犹如黑夜里的寒月没有温暖和光明。", '<img src="res/img/wy_img1.jpg">');
insert into whisper values(null, null, 145, 333, "　　翻开浩瀚的历史画卷，许多历史人物脱颖而出，向人们诉说着尊严。从在北海牧羊十九年而从未放弃祖国的苏武到“留取丹心照汗青”的文天祥；从“不为五斗米折腰”的陶渊明到“安能摧眉折腰事权贵”的李白；从置生死于不顾、愤怒销毁鸦p的林则徐到巴黎和会上从容不迫、昂首挺胸的顾维钧，他们用尊严描绘着自己的人生。尊严之歌穿越岁月的风尘而历久弥新；尊严之歌趟过历史的长河而亘古不变。", '<img src="res/img/wy_img1.jpg">');
insert into whisper values(null, null, 222, 111, "　　如果追求是刚发芽的幼苗，那么彩虹便是盛开着朵朵鲜花的枝干。如果追求是一棵翠绿的果树，那么彩虹便是那五颜六色的硕果。如果追求是人生的起点，那么彩虹便是成功的彼岸。相信，成功之花在彩虹的映照下会变得更加绚烂夺目。", '<img src="res/img/wy_img1.jpg">');
insert into whisper values(null, null, 1200, 1200, "　　生命如画，有浓墨泼洒，也有淡笔轻描；生命如歌，有轻吟浅唱，也有黄钟大吕。君不见李太白、杜工部之一生，浓墨泼洒，铸就生命之伟大；裴多菲、雪莱之一生，淡笔轻描终写尽生命之真谛。君不见轻音浅唱之陶渊明“采菊东篱下，悠然见南山”，高歌猛进之谭嗣同“我自横刀向天笑，去留肝胆两昆仑”。", '<img src="res/img/wy_img1.jpg">');
insert into whisper values(null, null, 1200, 1200, "　　生命是山，我们无法预估它的长度，我们只好追求它的高度。生命是路，它是由一块块不起眼的沙石组成的，而不是铺金布银，平平淡淡中尽显生命之完美。生命是一叶扁舟，航行于茫茫沧海之中，只有经历暴风雨的洗礼，才会迎来明天海上初升的太阳。", '<img src="res/img/wy_img1.jpg">');

select * from whisper;


select article.id, article_item.item, article.title, article.article_content, article.pic from article, article_item where article.item = article_item.id limit 3, 5;

