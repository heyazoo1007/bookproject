var main={
   init : function(){
        var _this=this;
        $('#btn-save').on('click',function(){
            _this.save();
        });
        $('btn-delete').on('click', function(){
            _this.delete();
        });
    },
    save: function(){
        var data ={
            bookTitle: $('#bookTitle').val(),
            author:$('#author').val(),
            publisher:$('#publisher').val(),
            category:$('category').val()
        };

        $.ajax({
            type:'Post',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function(){
            alert('책이 등록되었습니다.');
            window.location.href='/bookshelf';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
   delete: function(){
        var id=$('#id').val();

        $.ajax({
        type:'DELETE',
        url: '/api/v1/posts/'+id,
        dataType: 'Json',
        contentType: 'application/json; charset= utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href='/bookshelf'
        }).fail(function (error){
             alert(JSON.stringify(error));
       });
   }

};

main.init();