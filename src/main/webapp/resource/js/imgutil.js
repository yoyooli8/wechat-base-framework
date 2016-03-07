function loadImage(fileInput,divImg){
	var newImage = document.createElement("img");
	newImage.id = "parentDiv"; 
	$(newImage).css({opacity:"0"});
	getImg(fileInput,newImage,divImg);

}
function getImg(docObj,imgObjPreview,localImagId){
    if (docObj.files && docObj.files[0]) {  
        //火狐下，直接设img属性   
        if (window.navigator.userAgent.indexOf("Chrome") >= 1 || window.navigator.userAgent.indexOf("Safari") >= 1) {  
            imgObjPreview.src = window.webkitURL.createObjectURL(docObj.files[0]);
        }  
        else {  
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);  
        }
    } else {  
        //IE下，使用滤镜   
        docObj.select();  
        docObj.blur();  
        var imgSrc = document.selection.createRange().text;  
        //图片异常的捕捉，防止用户修改后缀来伪造图片   
         localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
         localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
       	 document.selection.empty();  
    }  
    addImageSpan(docObj,localImagId,imgObjPreview);
}
function addImageSpan(docObj,parent,img){
	var newspan = document.createElement("span");//添加图片span
	var newimg = document.createElement("img");//添加图片元素，设置图片属性
    newimg.src = img.src;
    newimg.className="img-thumbnail";
	var imgWdith = $(parent)[0].offsetWidth;
	var i = new Image(); //新建一个图片对象 
	i.src = img.src;//将图片的src属性赋值给新建的图片对象的src 
	var probability = imgWdith / i.width;
	var imgHeight = i.height * probability;
	 $(newimg).css({"width":imgWdith+"px","height":imgHeight+"px"});
	 newspan.appendChild(newimg);
	 $(parent)[0].appendChild(newspan);
	 docObj.style.display="none";
	 $("#textInput").html(docObj.value);
	 $("#textInput").show();
}