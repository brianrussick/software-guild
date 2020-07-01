var url = 'http://localhost:8080/BTBB';

var userName = document.getElementById('userName');
var dateSelector = document.getElementById('dateSelector');
var tags = document.getElementById('tags');
var tinyMCETextArea = document.getElementById('tinyMCETextArea');
var submitPost = document.getElementById('submitPost');
var dateValue = '';
var tagsInput = '';
var blogTextArea = 'Text Area Test';

// ajax call
function createPost() {
    $.post(url + '/createnewpost', function(data) {
        console.log('success createPost()');
        console.log(data);
    });
}

// event helper
Date.prototype.toDateInputValue = (function () {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0, 10);
});

// event listeners
function addEventListeners() {
    dateSelector.addEventListener('change', function () {
        dateValue = dateSelector.value;
    });

    tags.addEventListener('input', function () {
        tagsInput = tags.value;
        console.log(tagsInput);
    });
}

$(document).ready(function () {
    dateSelector.value = new Date().toDateInputValue();
    dateValue = dateSelector.value;
    console.log(dateValue);

    tinyMCETextArea.value = blogTextArea;
    addEventListeners();
});

function validateform(){
    var blogTitle=document.myform.blogTitle.value;
    var date=document.myform.date.value;
    var pictureUrl= document.myform.thumbnail.value;
    var tag = document.myform.tag.value;
    var postHtml = document.myform.postHtml.value;

    if (blogTitle==null || blogTitle==""){
        alert("Must enter a Blog Title");
        return false;
    }else if(date==null||date=="" ){
        alert("Must enter a Date.");
        return false;
    }else if(pictureUrl ==null||pictureUrl==""){
        alert("Must enter Picture URL")
        return false;
    }else  if (tag==null||tag==""){
        alert("Must enter tags")
        return false;
    }else  if (postHtml==null||postHtml==""||postHtml.length>50000) {
        alert("TinyMCE cannot be Empty or length is over limit")
        return false;
    }
}