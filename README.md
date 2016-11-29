# SurveyAssistant

# 1、获取参加过调查问卷的全国各省统计数据
* http://localhost:8080/assist/visiting/statistic
* method: GET
* 参数：无

* 返回数据的JSON格式：
{"广东省":2,"上海市‚":1,"北京市":1"}

# 2、获取正在参加调查问卷的人数
* http://localhost:8080/assist/visiting/onlineCount
* method: GET
* 参数：无
* 返回数据的JSON格式：一个整数

# 3 query ip list and count by place name
* http://localhost:8080/assist/visiting/queryByAddr
* method:GET
* query condition: 
 ```
 addr  # * address *
 ``` 
 
* return: {ips:[{12.12.12.12},{23.23.23.23},{43.34.34.34}]}

# 4、查询“被调查者”的相关信息，这里用到了组合条件查询
* http://localhost:8080/assist/respondent/search?school=u&nationality=han
* 参数，即查询条件：
<pre>
school #学校
education #教育
nationality #民族
major #专业
residence
gender
page.currentPage #(the first page is 1)
page.pageSize #每一页的条数（大小）
</pre>
  
* 返回数据的JSON格式

```
{
    "pageSize": 10,
    "currentPage": 0,
    "start": 0,
    "itemCount": 4,
    "pageCount": 1,
    "data": [
        {
            "id": 1,
            "name": "liangyihuai",
            "gender": "nan",
            "school": "cqupt",
            "education": "benke",
            "major": "jisuanji",
            "nationality": "hanzu",
            "residence": "nongye", #户口类型
            "surveyFinish": 1 #问卷完成程度
        },
        {
            "id": 5,
            "name": "liangyihuai",
            "gender": "nan",
            "school": "cqupt",
            "education": "benke",
            "major": "jisuanji",
            "nationality": "hanzu",
            "residence": "nongye",
            "surveyFinish": 5
        }  "surveyFinish": 11
       
    ]
}
```

# 5 导出（下载）
* http://localhost:8080/assist/respondent/download
* 参数，即查询条件： **注意：这里不能使用ajax发送请求，原因是使用ajax请求也意味着使用ajax接收数据。**
* 请求方式: GET
<pre>
school #学校
education #教育
nationality #民族
major #专业
residence
gender
page.currentPage #(the first page is 1)
page.pageSize #每一页的条数（大小）
</pre>
  
* 返回数据:
自动弹出下载框。

# 6、获取在linux系统中运行top命令显示出来的数据
* http://localhost:8080/assist/sys/info2

* 参数：无

* JSON格式：
<pre>
{
    "cpu":xxx,
    "memory":xxxx,
    "io":xxx,
    "swap":xxx,
    "id": 0,
    "createTime": 1478939443070,
    "process": "××××" #linux系统top命令运行之后显示的数据，每一行以回车符结尾
}
</pre>

***

