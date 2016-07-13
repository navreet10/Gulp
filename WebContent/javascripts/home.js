/**
 * 
 */

 $(document).ready(function(){
	 $( "#accordion" ).accordion();
	 var rem = 141;
	 $("#text").html(rem + " characters remaining");	
	 $("#post").keyup(function() {
		 var rem = 141 - $("#post").val().length;
		 $("#text").html(rem + " characters remaining");
	 });
	 $('.restRate').click(function (event) {
		 var idPost = $(this).attr('id');
		 var id = idPost.substring(4);
		 alert(id);
		 var dataString ='idRest='+ id;
		 
		 $.ajax({  
			    type: "POST",  
			    url: "ViewRest",
             data: dataString,
             success: function(data){
                 window.location = 'http://localhost:8080/Gulp/rateView.jsp';
               }                
			  });	 
	 
	 });
	 
	 $( "#review" ).submit(function( event ) {
		  
		});
	 
}); 

