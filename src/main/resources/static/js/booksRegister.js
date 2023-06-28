
$("#searchIsbn").click(function(){

    var isbn = $("#isbn").val();

    $.ajax({

        typeof: "get",
        async:false,
        url:"/book/isbnSearch",
        data:{isbn,isbn},
     /*   dataType:"json",9788960773417*/
        success: function(data){
            alert('데이터를 성공적으로 가져왔습니다.');
            $("#title").val(data.title);
            $("#author").val(data.author);
            $("#publisher").val(data.publisher);

        }
    })


});