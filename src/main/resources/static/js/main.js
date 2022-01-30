$(function(){

//Добавляем страницу
    $('#request-page').click(function(){
        var data = $('#request-page-form form').serialize();

        $.ajax({
            method: "POST",
            url: '/pages/',
            data: data,
            success: function(response)
            {
                if(response===-1){
                    alert("URL задан не верно!");
                    return false;
                }
                if(response===-2){
                    alert("Host задан не верно!");
                    return false;
                }
                $.each($("#pages-table tbody tr"), function() {
                    $(this).remove();
                });
                // Если добавилось успешно, то обновляем таблицу страниц
                var pageId = response;
                $.ajax({
                    method: "GET",
                    url: '/pages/',
                    success: function(response)
                    {
                        for (var i in response){
                            if(response[i].id===pageId) {
                                $('#pages-table > tbody:last-child').append('<tr class="selected"><td>' + response[i].id + '</td><td>' + response[i].url + '</td></tr>');
                            }else{
                                $('#pages-table > tbody:last-child').append('<tr><td>' + response[i].id + '</td><td>' + response[i].url + '</td></tr>');
                            }
                        }
                        // Если таблица страниц обновилась
                        // то запрашиваем обновление запросов для этой страницы
                        refreshRequestsTable(pageId);
                    }
                });
            }
        });

        return false;
    });

// Обновляем таблицу запросов
    const refreshRequestsTable = function(data){
        var pageId = data;
        $.each($("#requests-table tbody tr"), function() {
            $(this).remove();
        });
        $.ajax({
            method: "GET",
            url: '/page/requests/'+pageId,
            success: function(response)
            {
                // Выбираем максимальный id Запроса
                var pageRequestId = 0;
                for (var i in response){
                    if(response[i].id > pageRequestId){
                        pageRequestId = response[i].id
                    }
                }

                for (var i in response){
                    if(pageRequestId === response[i].id){
                        $('#requests-table > tbody:last-child').append('<tr class="selected"><td>' + response[i].id
                                                    + '</td><td>' + response[i].requestTime
                                                    + '</td><td>' + response[i].httpStatusCode + '</td></tr>');
                    }else{
                        $('#requests-table > tbody:last-child').append('<tr><td>' + response[i].id
                            + '</td><td>' + response[i].requestTime
                            + '</td><td>' + response[i].httpStatusCode + '</td></tr>');
                    }


                }
                refreshWordsTable(pageRequestId);
            }
        });
        return false;
    };

 // Обновляем таблицу слов
     const refreshWordsTable = function(data){
         var pageRequestId = data;
         $.each($("#words-table tbody tr"), function() {
             $(this).remove();
         });
         $.ajax({
             method: "GET",
             url: '/page/request/word/'+pageRequestId,
             success: function(response)
             {
                 for (var i in response){
                     $('#words-table > tbody:last-child').append('<tr><td>' + response[i].id
                         + '</td><td>' + response[i].word
                         + '</td><td>' + response[i].wordCount + '</td></tr>');
                 }
             }
         });
         return false;
     };

});