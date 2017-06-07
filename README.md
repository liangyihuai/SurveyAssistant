## 这个系统的目的：
这是本人本科的一门实验课，要求做一个系统，全班分为多个组，每个组的系统需求是相同的。所以，这个系统算是一个“应付性”的作业了，哈哈哈！因为本系统从头到尾都是一个人编写的（虽然一个组中有5、6人），所以有权把它共享出来。如果有师弟师学妹能找到这个现成的代码，也算是一种缘分吧！

## 系统功能
系统功能1：运行环境模块：辅助系统要求能接入Linux服务器，监控CPU、内存、I/O、交换空间、进程状况等的实时情况，类似top命令执行结果。并将结果保存到数据库中。

系统功能2：实时访问情况模块：辅助系统要能监控Apache服务运行情况，实时反映当前系统填答人数，将访问系统的IP地址保存到数据库中，对IP分省、自.治区、直辖市进行相应统计查询。

系统功能3：问卷填答情况模块：为高校提供查询功能，能够按院校、学历层次、专业、性别、民族、户口类别等，查询当前已经完成问卷、未完成问卷、部分完成问卷的学生名单，并能导出为Excel、PDF等常用格式，便于学校进行填答督促。


## 环境：

运行环境：open SUSE leap 42.1
开发环境：Intellij IDEA
开发语言：JavaEE
数据库：MySQL

## 数据库见表语句

```
CREATE DATABASE SurveyAssistant;
CREATE TABLE ip
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    ip VARCHAR(35),
    flag INT(11),
    remark VARCHAR(30)
);
CREATE TABLE user
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userName VARCHAR(30),
    password VARCHAR(30),
    level INT(11),
    createTime DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE systemInfo
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    topInfo TEXT
);
CREATE TABLE respondent
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(30),
    nationality VARCHAR(30),
    school VARCHAR(70),
    education VARCHAR(30),
    major VARCHAR(70),
    gender VARCHAR(10),
    residence VARCHAR(30),
    surveyFinish INT(11)
);
```

### 相关说明：
* 1、这里我所写的url，可以直接复制到浏览器，回车。（有一些需要修改一下参数的value :）），举一个栗子：http://localhost:8080/assist/sys/search?timeStr=2016-11-11&page.pageSize=2&page.currentPage=1，这个url的参数有三个，可以直接复制到浏览器中。
* 2、下面我用如1-1、2-1这样的表示不同的段落，第一个数字表示它将会用于哪个功能模块。
* 3、**返回的json格式以浏览器实际输出为准**，具体做法为：直接复制url到浏览器地址栏中回车。

***
##1-1、查询服务器top命令所显示的信息。
* http://localhost:8080/assist/sys/search?timeStr=2016-11-11&page.pageSize=2&page.currentPage=1

* 参数：
<pre>
timeStr *类型为字符串， 格式为：yyyy-MM-dd hh:mm:ss, 举一个栗子：输入“2016-11-11”， 查询出这一天所有的记录；输入“2016-11-11 13”，查询出这一天下午一点这个小时内所有的数据；输入“2016-11-11 13：06”，将查询出这一分钟内的所有数据。*
page.currentPage  #(the first page is 1)
page.pageSize
</pre>

* JSON格式：**以浏览器实际输出为准**
<pre>
[
    {
        "id": 501,
        "createTime": 1478850360000, #该条数据创建的时间
        "topInfo": "*** " #linux系统top命令运行之后显示的数据，每一行以回车符结尾
    },
    {
        "id": 502,
        "createTime": 1478850363000,
        "topInfo": "** "
    }
]
</pre>

##1-2、获取在linux系统中运行top命令显示出来的数据
* http://localhost:8080/assist/sys/info

* 参数：无

* JSON格式：
<pre>
{
    "id": 0,
    "createTime": 1478939443070,
    "topInfo": "××××" #linux系统top命令运行之后显示的数据，每一行以回车符结尾
}
</pre>

##2-1、获取参加调查问卷的总人数
* http://localhost:8080/assist/visiting/totalCount
* 参数：无
* 返回数据JSON格式：一个整数

##2-2、获取正在参加调查问卷的人数
* http://localhost:8080/assist/visiting/onlineCount
* 参数：无
* 返回数据的JSON格式：一个整数

##2-3、获取参加过调查问卷的全国各省统计数据
* http://localhost:8080/assist/visiting/statistic
* 参数：无

* 返回数据的JSON格式：
{"广东省":2,"上海市‚":1,"北京市":1"}


##3-1、查询“被调查者”的相关信息，这里用到了组合条件查询
* http://localhost:8080/assist/respondent/search?school=u&nationality=han
* 参数，即查询条件：
<pre>
school #学校
education #教育
nationality #民族
major #专业
page.currentPage #(the first page is 1)
page.pageSize #每一页的条数（大小）
</pre>
  
* 返回数据的JSON格式 **以浏览器实际输出为准**
<pre>
[
    {
        "id": 1,
        "name": "liangyihuai",
        "gender": null,
        "school": "cqupt",
        "education": "benke",
        "major": null,
        "nationality": "hanzu",
        "residence": null, #户口类型
        "surveyFinish": 0 #问卷完成程度
    },
    {
        "id": 2,
        "name": null,
        "gender": null,
        "school": "cqupt",
        "education": null,
        "major": null,
        "nationality": "hanzu",
        "residence": null,
        "surveyFinish": 0
    }
]
</pre>

##3-2导出（下载）
* http://localhost:8080/assist/respondent/download
* 参数，即查询条件： **注意：这里不能使用ajax发送请求，原因是使用ajax请求也意味着使用ajax接收数据。**
* 请求方式: GET
<pre>
school #学校
education #教育
nationality #民族
major #专业
</pre>
  
* 返回数据:
自动弹出下载框。
