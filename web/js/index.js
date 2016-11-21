
$(document).ready(function(){
    getSystemInfo();
    setInterval(getSysInfoCycling, 3000);
    $('#time-id').dateDropper({color:'#f87a54',animation:'dropdown',format:'y-m-d', placeholder:currentDataStr(),lock:'to'});

    $('#select-hour-id').editableSelect({
        effects: 'slide',
        filter:false
    });

    $('#select-minute-id').editableSelect({
        effects: 'slide',
        filter:false
    });

});


var isCycling_global = true;
var tempSearchSysInfo_global;

function setCycling(isCycling) {
    isCycling_global = isCycling;
}

function getSysInfoCycling() {
    if(isCycling_global){
        getSystemInfo();
    }
}



function getSystemInfo(){
    $.ajax({
        type: 'GET',
        url: "/assist/sys/info",
        success: function(data){
            showSysInfo(data.topInfo);
        }
    });
}

function showSysInfo(data) {
    var textArea = document.getElementById("topInfoID");
    textArea.innerHTML = data;
}

function showSearchSysInfo(list) {
    if(list == null)return ;
    var tBody = document.getElementById("tbody-id");

    var str = "";
    var len = list.length;
    var count = 0;
    for(var i = 0; i < len; i++){
        var info = list[i];

        var timeStr = timeStamp2String(info.createTime);

        /*var row = document.createElement("tr");
        row.setAttribute("id", "search-id"+count)
        var cell = document.createElement("td");
        cell.innerHTML = timeStr;
        cell.setAttribute("onclick", "showSearchSysInfoDetail("+count+")");

        row.appendChild(cell);
        tBody.appendChild(row);*/

        str += "<tr id=search-id"+count+">";
        /*str += "<td style='display:none;'>"+(count++)+"</td>";*/
        str += "<td onclick=showSearchSysInfoDetail("+count+") style='cursor:pointer'>"+timeStr+"</td>";
        str +="</tr>";

        count++;
    }
    tBody.innerHTML = str;
}

function showSearchSysInfoDetail(id) {
    setCycling(false);
    showSysInfo(tempSearchSysInfo_global[id].topInfo);

    clearXFlag();

    var tableTr = document.getElementById("search-id"+id);
    //if the element is exists
    if(tableTr.childElementCount >= 2) {
        tableTr.childNodes.item(1).innerHTML = "X";
        return ;
    }
    var newCol = tableTr.insertCell(1);
    newCol.className = "clear-td-class";
    newCol.innerHTML = "X";

    //?????????????????
    newCol.setAttribute("onclick", "clearShownSearchSysInfoDetail()");
    newCol.setAttribute("style", "cursor:pointer");
}

//clear other cell's "x" flag
function clearXFlag() {
    var tds = document.getElementsByClassName("clear-td-class");
    if(tds == null || tds.length == 0)return ;
    for(var i = 0; i < tds.length; i++){
        tds[i].innerHTML = "";
    }
}

function clearShownSearchSysInfoDetail() {
    setCycling(true);
    clearXFlag();
}

function getSearchPath(currentPage, pageSize) {
    if(currentPage == null )currentPage = 1;
    if(pageSize == null) pageSize = 10;
    var time = getSearchCondition();
    var result = "/assist/sys/search?page.pageSize="+pageSize+"&page.currentPage="+currentPage;
    if(time != null && time != ""){
        result += "&timeStr="+time;
    }

    return result;
}
function getSearchCondition(){
    var data = document.getElementById("time-id").value;

    var result = "";
    if(data == null || data == "")return "";
    result += data;

    var hour = document.getElementById("select-hour-id").value;
    if(hour == null || hour == "" || isNaN(hour))return result;
    result += " "+hour;

    var minute = document.getElementById("select-minute-id").value;
    if(minute == null || minute == "" || isNaN(minute))return result;
    result += ":"+minute;

    return result;

}

function search(currentPage) {
    if(currentPage == null)currentPage = 1;

    var path = getSearchPath(currentPage, 9);
    $.ajax({
        type: 'GET',
        url: path,
        async: false,
        success: function(data){
            if(data == null)return ;

            showSearchSysInfo(data.data);
            tempSearchSysInfo_global = data.data;
            pageCount = data.pageCount;
        }
    });
}

function timeStamp2String (time){
    if(time == null || isNaN(time))return ;
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1;
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minute = datetime.getMinutes();
    var second = datetime.getSeconds();
    // var mseconds = datetime.getMilliseconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}

function currentDataStr(){
    var date = new Date();
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}

var currentPage = 1;
var pageCount = 1;
function previousSearchPage() {
    if(currentPage <= 1) return ;
    currentPage--;
    search(currentPage);

    if(currentPage <= 1){
        document.getElementById("previous-page-id").style.cursor = null;
    }

    if(currentPage < pageCount){
        document.getElementById("next-page-id").style.cursor = "pointer";
    }
}

function nextSearchPage() {
    if(currentPage >= pageCount)return ;
    currentPage++;
    search(currentPage);
    if(currentPage >= pageCount){
        document.getElementById("next-page-id").style.cursor = null;
    }

    if(currentPage > 1){
        document.getElementById("previous-page-id").style.cursor = "pointer";
    }
}

