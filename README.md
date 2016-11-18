#写在前面：
* 1、这里我所写的url，可以直接复制到浏览器，回车。（有一些需要修改一下参数的value :）），举一个栗子：http://localhost:8080/assist/sys/search?timeStr=2016-11-11&page.pageSize=2&page.currentPage=1，这个url的参数有三个，可以直接复制到浏览器中。
* 2、下面我用如1-1、2-1这样的表示不同的段落，第一个数字表示它将会用于哪个功能模块。

***
##1-1、查询服务器top命令所显示的信息。
* http://localhost:8080/assist/sys/search?timeStr=2016-11-11&page.pageSize=2&page.currentPage=1

* 参数：
    timeStr #类型为字符串， 格式为：yyyy-MM-dd hh:mm:ss, 举一个栗子：输入“2016-11-11”， 查询出这一天所有的记录；输入“2016-11-11 13”，查询出这一天下午一点这个小时内所有的数据；输入“2016-11-11 13：06”，将查询出这一分钟内的所有数据。
    page.currentPage  #(the first page is 1)
    page.pageSize

* JSON格式：
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
  
* 返回数据的JSON格式
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
