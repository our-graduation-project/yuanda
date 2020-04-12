function inSearch() {
    //获得选择的值
    let option = $('.ui.inline.dropdown').dropdown('get value');
    let searchValue = $("#searchValue").val();
    let url = "search.html?searchValue="+searchValue;
    // console.log(option);
    if(!isNull(option)){
        url = url + "&option=" + option;
    }
    window.location.href=url;
}