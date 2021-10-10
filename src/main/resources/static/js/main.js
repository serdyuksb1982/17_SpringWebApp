$(function(){

    const appendTodo = function(data){
        var todoCode = '<a href="#" class="lists-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#lists-list')
            .append('<div>' + todoCode + '</div>');
    };

//    //Loading list on load page
//    $.get('/lists/', function(response)
//    {
//        for(i in response) {
//            appendTodo(response[i]);
//        }
//    });

    //Show adding todo form
    $('#show-add-lists-form').click(function(){
        $('#lists-form').css('display', 'flex');
    });

    //Closing adding todo form
    $('#lists-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting todo
    $(document).on('click', '.lists-link', function(){
        var link = $(this);
        var idToDoL = link.data('id');
        $.ajax({
            method: "GET",
            url: '/lists/' + idToDoL,
            success: function(response)
            {
                var code = '<span>Год выпуска:' + response.year + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('дело не найдена!');
                }
            }
        });
        return false;
    });

    //Adding todo
    $('#save-lists').click(function()
    {
        var data = $('#lists-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/lists/',
            data: data,
            success: function(response)
            {
                $('#lists-form').css('display', 'none');
                var todo = {};
                todo.id = response.id;
                var dataArray = $('#lists-form form').serializeArray();
                for(i in dataArray) {
                    todo[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTodo(todo);
            }
        });
        return false;
    });

});
