<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link href="res/img/html_head.png" rel="SHORTCUT ICON">
    <title>CODE的博客</title>
    <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="res/css/main.css">
</head>

<body>
    <div class="header">
        <div class="menu-btn">
            <div class="menu"></div>
        </div>
        <h1 class="logo">
            <a href="index">
                <span>MYBLOG</span>
                <img src="res/img/logo.png">
            </a>
        </h1>
        <div class="nav">
            <a href="/">文章</a>
            <a href="whisper">微语</a>
            <a href="leacots" class="active">留言</a>
            <a href="album">相册</a>
            <a href="about">关于</a>
        </div>
        <ul class="layui-nav header-down-nav">
            <li class="layui-nav-item"><a href="/">文章</a></li>
            <li class="layui-nav-item"><a href="whisper">微语</a></li>
            <li class="layui-nav-item"><a href="leacots" class="active">留言</a></li>
            <li class="layui-nav-item"><a href="album">相册</a></li>
            <li class="layui-nav-item"><a href="about">关于</a></li>
        </ul>
        <p class="welcome-text">
            欢迎来到<span class="name">李韬</span>的博客~
        </p>
    </div>

    <div class="content whisper-content leacots-content">
        <div class="cont w1000">
            <div class="whisper-list">
                <div class="item-box">
                    <div class="review-version">
                        <div class="form-box">
                            <img class="banner-img" src="res/img/liuyan.jpg">
                            <div class="form">
                                <form class="layui-form" action="">
                                    <div class="layui-form-item layui-form-text">
                                        <div class="layui-input-block">
                                            <textarea name="content" id="content" placeholder="既然来了，就说几句" class="layui-textarea"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block" style="text-align: right;">
                                            <button class="layui-btn definite" style="display:none" id="btn_subbmit" lay-submit lay-filter="add">確定</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="volume">
                            全部留言 <span id="msg_total">1</span>
                        </div>
                        <div class="list-cont" id="list-cont">

                        </div>
                    </div>
                </div>
            </div>
            <div id="demo" style="text-align: center;"></div>
        </div>
    </div>

    <div class="footer-wrap">
        <div class="footer w1000">
            <div class="qrcode">
                <img width="160px" src="res/img/wx.jpg">
            </div>
            <div class="practice-mode">
                <!-- <img src="res/img/down_img.jpg"> -->
                <div class="text">
                    <h4 class="title">我的联系方式</h4>
                    <p>手机<span class="iphone">18651820052</span></p>
                    <p>Q&nbsp;Q<span class="email">478430357</span></p>
                    <p>邮箱<span class="email">478430357@qq.com</span></p>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="res/layui/layui.js"></script>
    <script id="demoTest" type="text/html">
        {{# layui.each(d, function(index, item){ }}
        <div class="cont">
            <div class="img">
                <img src="res/img/head/{{item.HeadImg}}" alt="">
            </div>
            <div class="text">
                <p class="tit">
                    <span class="name">{{item.Name}}</span>
                    <span class="data">{{item.CreateTimeFormat}}</span>
                </p>
                <p class="ct">{{item.Content}}</p>
                <p><span style="float:right">{{item.Country==null?"地球":item.Country}} {{item.Region==null?"某地":item.Region}} 
                	{{item.City==null?"某市":item.City}} {{item.County==null?"某区":item.County}} {{item.Isp==null?"某商":item.Isp}}</span></p>
            </div>
        </div>
        {{# }); }}{{# if(d.length === 0 || d==null){ }} 暂无留言 {{# } }}

    </script>
    <script type="text/javascript">
        layui.config({
            base: 'res/js/util/'
        }).use(['element', 'laypage', 'form', 'menu'], function () {
            laypage = layui.laypage, form = layui.form, mm = layui.mm, layer = layui.layer, $ = layui.jquery, laytpl = layui.laytpl;

            var btn_subbmit = $("#btn_subbmit");
            //menu.init();
            var pageIndex = 1,
                pageSize = 10;
            form.on('submit(add)', function (data) {
                btn_subbmit.hide();
                mm.request({
                    url: "api_message_add_post",
                    type: "POST",
                    data: data.field,
                    success: function (result, msg) {
                        btn_subbmit.show();
                        $("#content").val('');
                        layer.msg(msg);
                        init(pageIndex, pageSize);
                    },
                    error: function (error) {
                        btn_subbmit.show();
                        layer.msg(error);
                    }
                });
                return false;
            });
            //初始化评论
            var init = function (i, s) {

                mm.request({
                    url: "api_message_paged_get",
                    data: {
                        "pageIndex": i,
                        "pageSize": s
                    },
                    success: function (data, msg) {
                        btn_subbmit.show();
                        $("#msg_total").html(data.pageTotal);
                        laypage.render({
                            elem: 'demo',
                            count: data.pageTotal,
                            limit: data.pageSize,
                            curr: data.pageIndex,
                            jump: paged
                        });
                        BindData(data.data);
                    }
                });
            }

            function BindData(data) {
                var arrdata = data || [];
                var getTpl = demoTest.innerHTML,
                    view = document.getElementById('list-cont');
                laytpl(getTpl).render(arrdata, function (html) {
                    view.innerHTML = html;
                });
            };
            var paged = function (obj, first) {
                if (!first) {
                    init(obj.curr, obj.limit);
                }
            }
            init(pageIndex, pageSize);
        });
    </script>
</body>

</html>