/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
    });
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });
});


$(document).on("click", ".edit", function () {
    var id = $(this).data('id');
    var name = $(this).data('name');
    var address = $(this).data('address');
    var phone = $(this).data('phone');
    var email = $(this).data('email');
    var password = $(this).data('password');
    var date = $(this).data('date');
    
    $(".modal-body #id").val(id);
    $(".modal-body #name").val(name);
    $(".modal-body #address").val(address);
    $(".modal-body #phone").val(phone);
    $(".modal-body #email").val(email);
    $(".modal-body #password").val(password);
    $(".modal-body #date").val(date);
});

$(document).on("click", ".delete", function () {
    var id = $(this).data('id');
    
    $(".modal-footer #delete-id").val(id);
   
});

//popup
jQuery(document).ready(function($){
  
  window.onload = function (){
    $(".bts-popup").addClass('is-visible');
	}
  
	//open popup
	$('.bts-popup-trigger').on('click', function(event){
		event.preventDefault();
		$('.bts-popup').addClass('is-visible');
	});
	
	//close popup
	$('.bts-popup').on('click', function(event){
		if( $(event.target).is('.bts-popup-close') || $(event.target).is('.bts-popup') ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	//close popup when clicking the esc keyboard button
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		$('.bts-popup').removeClass('is-visible');
	    }
    });
});

//end popup

//add product 
$(document).on("click", ".edit-product", function () {
    var id = $(this).data('id');
    var category = $(this).data('category');
    var name = $(this).data('name');
    var price = $(this).data('price');
    var discount = $(this).data('discount');
    var description = $(this).data('description');
    var image = $(this).data('image');
    var quantity = $(this).data('quantity');
    var available = $(this).data('available');
    var condition = $(this).data('condition');
    var date = $(this).data('date');
    
    $(".modal-body #product-id").val(id);
    $(".modal-body #product-name").val(name);
    $(".modal-body #product-price").val(parseFloat(price));
    $(".modal-body #product-discount").val(parseInt(discount));
    $(".modal-body #product-description").val(description);
    $(".modal-body #product-image").val(image);
    $(".modal-body #product-quantity").val(parseInt(quantity));
    $(".modal-body #date").val(date);
});

$(document).on("click", ".delete-product", function () {
    var id = $(this).data('id');
    
    $(".modal-footer #delete-id").val(id);
   
});



