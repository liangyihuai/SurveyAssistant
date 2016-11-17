
$(document).ready(function(){
    getSystemInfoCycling();
    setInterval(getSystemInfoCycling,3000);
});

function getSystemInfoCycling(){
    $.ajax({
        type: 'GET',
        url: "/assist/sys/info",
        success: function(data){
            var textArea = document.getElementById("topInfoID");
            textArea.innerHTML = data.topInfo;
        }
    });
}

