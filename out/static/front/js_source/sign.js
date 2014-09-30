$(function()
{

    $(".ipt_1").click(function(event) {
        $(this).attr("value", "" );
    });

    $(".ipt_2").click(function(event) {
        $(this).attr("value", "" );
    });

    $(".m2").click(function(event) {
        event.preventDefault();
        $(".submenu_1").fadeToggle(500);
    });

    $(".submenu_1").click(function(event) {
        event.preventDefault();
        $(".submenu_1").fadeOut(500);
    });

    $(".m3").click(function(event) {
        event.preventDefault();
        $(".submenu_2").fadeToggle(500);
    });

    $(".submenu_2").click(function(event) {
        event.preventDefault();
        $(".submenu_2").fadeOut(500);
    });

    $(".btn_detail").click(function(event) {
        event.preventDefault();
        $(this).parent().parent().next("tr").slideToggle(500);
    });

    $(".exc").click(function(event) {
        $.blockUI({ message: $('.exc_inforbox') });
    });

    $(".btn_quit").click(function(event) {
        $.unblockUI();
    });

    $(".pop_btn input").click(function(event) {
        $.unblockUI();
    });

//    $(".tank h1").click(function(){
//        $(this).css({display:"none"});
//        $(".tank h2").fadeIn(3000);
//        $(".tank h3").fadeIn(3000);
//        $(".sign_button p").fadeIn(2000);
//        $(".wave").css({display:"block"});
//        $(".wave").animate({marginLeft: '-100em', marginTop:'-4.6em'}, 4000);
//    });

    $(".btn_vote").click(function(event) {
        $.blockUI({ message: $('.exc_inforbox') });
    });

//    $(".btn_1").click(function(event) {
//        $.blockUI({ message: $('.exc_inforbox') });
//    });

//    $(".btn_2").click(function(){
//        $(this).css({display:"none"});
//        $(".idcode").css({display:"block"});
//    });

    $(".te1").click(function(){
        $(".tag1").css({display:"table"});
        $(".tag2").css({display:"none"});
        $(".te1").addClass("ts");
        $(".te2").removeClass("ts");
    });

    $(".te2").click(function(){
        $(".tag1").css({display:"none"});
        $(".tag2").css({display:"table"});
        $(".te2").addClass("ts");
        $(".te1").removeClass("ts");
    });

    $(function(){
        var t = $("#text_box");
        $("#add").click(function(){
            t.val(parseInt(t.val())+100);
        })
        $("#min").click(function(){
            if (t.val() > 100) {
                t.val(parseInt(t.val())-100);
            }
        })
    })

});