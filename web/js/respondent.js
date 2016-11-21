$(document).ready(function(){
    showSurveyAndPage();
});

function showSurveyAndPage(){
    var data = getSurvey(1);
    if(data == null)return alert("null");
    showSurvey(data.data);
    var pageCount = data.pageCount;
    doPage(pageCount);
}

function showSurveyByCurrentPage(currentPage){
    var data = getSurvey(currentPage);
    showSurvey(data.data);
}

function getSurvey(currentPage){
    var pageSize = 10;
    var path = getSearchSurveyPath(currentPage, pageSize);
    var result = null;
    $.ajax({
        type: 'GET',
        url: path,
        async: false,
        success: function(data){
           result = data;
        }
    });
    return result;
}

function doPage(pageCount) {
    $('.M-box3').pagination({
        pageCount:pageCount,
        jump:true,
        coping:true,
        homePage:'首页',
        endPage:'末页',
        prevContent:'上页',
        nextContent:'下页',
        callback:function(index){
            var current = index.getCurrent();
            showSurveyByCurrentPage(current);
        }
    });
}


function getSearchCondition() {
    var school = document.getElementById("school-id").value;
    var education = document.getElementById("education-id").value;
    var nationality = document.getElementById("nationality-id").value;
    var major = document.getElementById("major-id").value;

    return {"school":school, "education":education, "nationality":nationality, "major":major};
}

function getSearchSurveyPath(currentPage, pageSize) {
    if(currentPage == null)currentPage = 1;
    if(pageSize == null)pageSize = 10;

    var path = "/assist/respondent/search?";
    searchCondition = getSearchCondition();
    if(searchCondition.school != null && searchCondition.school != ""){
        path += "school="+searchCondition.school+"&";
    }
    if(searchCondition.education != null && searchCondition.education != ""){
        path += "education="+searchCondition.education + "&";
    }
    if(searchCondition.nationality != null && searchCondition.nationality != ""){
        path += "nationality="+searchCondition.nationality+"&";
    }
    if(searchCondition.major != null && searchCondition.major != ""){
        path += "major="+searchCondition.major+"&";
    }
    path += "page.currentPage="+currentPage+"&";
    path += "page.pageSize="+pageSize;
    return path;
}

function showSurvey(surveys) {
    var tBody = document.getElementById("survey-tbody-id");
    tBody.innerHTML = '';

    if(surveys == null) return ;

    var str = "";
    var len = surveys.length;
    var itemNo = 1;
    for(var i = 0; i < len; i++){
        var survey = surveys[i];
        str += '<tr>';
        str += "<td style='display:none;'>"+survey.id+"</td>";
        str += "<td>"+(itemNo++)+"</td>";
        str += "<td>"+survey.name+"</td>";
        str += "<td>"+survey.nationality+"</td>";
        str += "<td>"+survey.school+"</td>";
        str += "<td>"+survey.education+"</td>";
        str += "<td>"+survey.major+"</td>";
        str += "<td>"+survey.gender+"</td>";
        str += "<td>"+survey.residence+"</td>";
        str += "<td>"+survey.surveyFinish+"</td>";
        str +="</tr>";
    }
    tBody.innerHTML = str;
}


