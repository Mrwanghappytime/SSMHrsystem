function UseAjax(url1,type1,data1,func1){
    $.ajax({
        url: url1,
        type: type1,
        dataType: 'json',
        data: data1,
        success:func1
    })
}