





$(document).ready(function() {
  function scrollableHeight() {
    var pageHeight = $(window).height();
  $('.scrollable').height(pageHeight-130);
  }

  scrollableHeight();
  $(window).resize(function(){
    scrollableHeight();
  })

  function sidebar(){
      $('#sidebar').toggleClass('active');
  }
  $('#sidebarCollapse').on('click', function () {
      sidebar();
  });

  $(document).on("click", "#graph" , function(event) {
      event.preventDefault();
      console.log('ddd');
      $('.side-popup').hide();


      console.log($("#disjointNodes a").length)
      $('#Disjointcount').html('(' + $("#disjointNodes a").length + ')')
      var DisjointList = $("#disjointNodes a").clone();
      // console.log(DisjointList.length());

      $('.show-Disjoint-list').on('click', function(e) {
          console.log(DisjointList);
          $('#list-group a').remove();
          DisjointList.appendTo('#list-group');

      });




      $('#disjointNodes a , #disjointNodes span').hide();
      $('#disjointNodes a:lt(3) , #disjointNodes span:lt(3)').show();




      $("#myInput").on("keyup", function() {
          var value = $(this).val().toLowerCase();
          $("#list-group a").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
          });
        });


      $('.hide-side-popup').on('click', function(e) {
          $('.side-popup').hide();
      });

      $("[data-show]").on('click', function(e) {
          // prevent default behaviour of the anchor tag
          e.preventDefault();
          // save the data attribute for the anchor tag that was clicked
          var page = $(this).data('show');
          $('#' + page).show();
      });




  });



  $('section#canvasArea').addClass('left-open-right-open');
  console.log('open-open');
  function leftCloseRightClose() {
      $('section#canvasArea').attr('class', '');
      $('section#canvasArea').addClass('left-close-right-close');
      $('.rectangle').css('width' , 0)
   }
   function leftOpenRightClose() {
      $('section#canvasArea').attr('class', '');

      $('section#canvasArea').addClass('left-open-right-close');
      $('.rectangle').css('width' , '0');
   }
   function leftCloseRightOpen() {
      $('section#canvasArea').attr('class', '');

      $('section#canvasArea').addClass('left-close-right-open');
      $('.rectangle').css('width' , '22%');
   }

   function leftOpenRightOpen() {
      $('section#canvasArea').attr('class', '');
      $('section#canvasArea').addClass('left-open-right-open');
      $('.rectangle').css('width' , '22%');
   }
   function checkXpand(){
      if($('section#canvasArea').hasClass('left-close-right-close')){
          $('#fullExpand').html('<i class="fas  fa-compress-arrows-alt"></i>')
      }
      else {
          $('#fullExpand').html('<i class="fas fa-expand-arrows-alt"></i>')
      }
   }

   $('#sidebarExpandButton').click(function (e) {
      if($('section#canvasArea').hasClass('left-open-right-open')){
          console.log('open-close');
          leftOpenRightClose();
      }
      else if($('section#canvasArea').hasClass('left-open-right-close')){
          console.log('open-open');
          leftOpenRightOpen();
      }
      else if($('section#canvasArea').hasClass('left-close-right-open')){
          console.log('close-close');
          leftCloseRightClose();
      }
      else if($('section#canvasArea').hasClass('left-close-right-close')){
          console.log('close-open');
          leftCloseRightOpen();
      }

      checkXpand()
   });

   $('#sidebarCollapse').click(function (e) {

      if($('section#canvasArea').hasClass('left-open-right-open')){
          console.log('close-open');
          leftCloseRightOpen();
      }
      else if($('section#canvasArea').hasClass('left-open-right-close')){
          console.log('close-close');
          leftCloseRightClose();
      }
      else if($('section#canvasArea').hasClass('left-close-right-open')){
          console.log('open-open');
          leftOpenRightOpen();
      }
      else if($('section#canvasArea').hasClass('left-close-right-close')){
          console.log('open-close');
          leftOpenRightClose();
      }
      checkXpand()



  });

  $('#fullExpand').click(function (e) {
      e.preventDefault();
      console.log('close-close');

      if($('section#canvasArea').hasClass('left-open-right-open')){
          console.log('close-close');
         // leftCloseRightClose();
          $('#sidebarCollapse').trigger("click");
          $('#sidebarExpandButton').trigger("click");
      }
      else if($('section#canvasArea').hasClass('left-open-right-close')){
          console.log('close-close');

          $('#sidebarCollapse').trigger("click");

      }
      else if($('section#canvasArea').hasClass('left-close-right-open')){
          $('#sidebarExpandButton').trigger("click");
      }
      else if($('section#canvasArea').hasClass('left-close-right-close')){
          console.log('open-open');
          $('#sidebarCollapse').trigger("click");
          $('#sidebarExpandButton').trigger("click");
      }


      checkXpand()

  });
  $('#contact').click(function(){
	$('.modal').addClass('show');
});
$('.close').click(function(){

    $('.modal').removeClass('show');
});
});
