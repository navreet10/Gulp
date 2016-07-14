/**
 * 
 */

 $(document).ready(function(){
	 $( "#accordion" ).accordion();
	 $("#start1").css('color','grey');
	 $("#start2").css('color','grey');
	 $("#start3").css('color','grey');
	 $("#start4").css('color','grey');
	 $("#start5").css('color','grey');
	 $('#rating').prop('disabled',true);
	 $('#rating').val("0");
	 $('#reviewsDiv').hide();
	 $('#showReviews').click(function() {
		 $('#reviewsDiv').toggle();
	 });	 
	 var rem = 450;
	 $("#text").html(rem + " characters remaining");	
	 $("#review").keyup(function() {
		 var rem = 450 - $("#post").val().length;
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
	 
	 $( "#start1" ).click(function( event ) {
		 $("#start1").css('color','yellow');
		 $("#start2").css('color','grey');
		 $("#start3").css('color','grey');
		 $("#start4").css('color','grey');
		 $("#start5").css('color','grey');
		 $('#rating').val("1");
		});
	 $( "#start2" ).click(function( event ) {
		 $("#start1").css('color','yellow');
		 $("#start2").css('color','yellow');
		 $("#start3").css('color','grey');
		 $("#start4").css('color','grey');
		 $("#start5").css('color','grey');
		 $('#rating').val("2");
		});
	 $( "#start3" ).click(function( event ) {
		 $("#start1").css('color','yellow');
		 $("#start2").css('color','yellow');
		 $("#start3").css('color','yellow');
		 $("#start4").css('color','grey');
		 $("#start5").css('color','grey');
		 $('#rating').val("3");
		});
	 $( "#start4" ).click(function( event ) {
		 $("#start1").css('color','yellow');
		 $("#start2").css('color','yellow');
		 $("#start3").css('color','yellow');
		 $("#start4").css('color','yellow');
		 $("#start5").css('color','grey');
		 $('#rating').val("4");
		});
	 $( "#start5" ).click(function( event ) {
		 $("#start1").css('color','yellow');
		 $("#start2").css('color','yellow');
		 $("#start3").css('color','yellow');
		 $("#start4").css('color','yellow');
		 $("#start5").css('color','yellow');
		 $('#rating').val("5");
		});
	 
}); 

