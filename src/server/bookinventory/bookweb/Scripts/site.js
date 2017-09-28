$(function(){

    $(".cmenuitem").click(menuClick);

    var iframeele = $(".J_iframe");


    function menuClick(event){
        //alert(event);
        console.log(event.target.href);

        iframeele.attr("src", event.target.href);

        return false;
    }

});