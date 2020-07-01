// setup blog navigation
var url = 'http://localhost:8080/BTBB/';
var navTabs = document.getElementById('navTabs');
navTabs.innerHTML = '';
var postDiv = document.getElementById('post');
var blogDiv = document.getElementById('blog');
var brickDiv = document.getElementById('bricks');
var arrayOfComment = [];
var selectedId;
var selectedPost;
var xhr = new XMLHttpRequest();
xhr.open('GET', url + 'navTabs.html', true);
xhr.responseType = '';
xhr.send();
xhr.addEventListener('load', loadNavTabs);

// blog navigation functionality
function loadNavTabs() {
    navTabs.innerHTML = this.responseText; // retrieves the variable from the nav
    var navTabsUI = navTabs.firstChild; // retrieves first child of nav tab that is a variable
                                       // and it puts the name on the URL
function navTab() {                   // if nothing is found it returns the home page
    var navWindowLocation = window
        .location
        .pathname
        .replace('/BTBB/', '')
        .replace('.html', '')
        .replace(' ', '')
        .toLowerCase();
    var navWindowLocaLength = navWindowLocation.length;
    var navWindowUrl = document.URL;
    var key = navWindowUrl // is a param that gets the data from the URL
        .substring(navWindowUrl.lastIndexOf('?') + 1)
        .toLowerCase();
    var nodeList = navTabs.firstElementChild.children;
    var currentNode;
        for (var i = 0; i < nodeList.length; i++) { // when length is greater than nodeList.length
            if (nodeList[i].nodeType === 1) {      // display string and remove URL then replace with home page
                currentNode = nodeList[i]
                    .innerText
                    .split(' ')
                    .join('')
                    .toLowerCase();
            if (navWindowLocation === '') {
                navWindowLocation = 'home';
            }
            if (navWindowLocation.length <= navWindowLocaLength && !key.includes('http')) {
                navWindowLocation += key;
            }
        }
    }
    };
}

$(document).ready(function () { // when page is ready load newest posts
    loadNewestPosts();
});

function loadNewestPosts() {
    var blogBricksDiv = $('#bricks');
    var blogPostDiv = $('#blog');

    $.ajax({
        type: 'GET',
        url: url + '/topPost',
        dataType: 'JSON',
        success: function (data) {
            console.log('SUCCESSFUL loadAllPosts()');
            console.log(data);
    var brickDivContent = ''; // form content
        $.each(data, function (index, post) {
        brickDivContent += '<div class="row" style="display: block; width: 100%; margin-left: 5px;">';
        if(index===0){
            brickDivContent+='<h1 style="margin-bottom: 25px; color: #337ab7;">Newest Blog Post</h1>';
            }
        else{
            brickDivContent+='<h1 style="margin-bottom: 25px;color: #337ab7;">Previous Blog Post</h1>';
            }               
            brickDivContent += '<div class="blogBrick col" style="margin:auto;background-image:url(' + post.imageLocation + ')" id="' + post.blogPostId + '">';
            brickDivContent += '<p><b>BlogPostId:</b> ' + post.blogPostId + '</p><br/>';
            brickDivContent += '<p><b>BlogTitle:</b> ' + post.blogTitle + '</p><br/>';
            brickDivContent += '<p><b>BlogPostDate:</b> ' + post.blogPostDate.year+" "+ post.blogPostDate.month+" "+post.blogPostDate.dayOfMonth + '</p><br/>';
            brickDivContent += '<p><b>User:</b> ' + post.user.userName + '</p><br/>';
            brickDivContent += '<p><b>Tags:</b> ' + post.tagsList[0].name + '</p><br/>';
            brickDivContent += '</div>';
            if (index % 3 === 2) { // if index list is 2 then close div
                brickDivContent += '</div>';
                }
                if (index >= data.length - 1) { // if data exists then close div
                    brickDivContent += '</div>';
                }
    var postDivContent = '<div class="blogPost col-md-12" id="post' + post.blogPostId + '" hidden>';
        postDivContent += '<button class="backButton btn btn-default pull-right">Back</button>';
        postDivContent += '<h2>' + post.blogTitle + '</h2>';
        postDivContent += '<div class="postContent">' + post.content + '</div>';
        postDivContent += '</div>';
        blogPostDiv.append(postDivContent);
        });
        blogBricksDiv.append(brickDivContent);
        
    // add onClick to each blogBrick which hides the bricks and shows
    // the individual blog post + comments / hides and showing the divs
    $('.blogBrick').click(function () {
        selectedId = $(this).attr('id');
        selectedPost = $('#post' + selectedId + '');
        blogBricksDiv.hide();
        blogPostDiv.show();
        selectedPost.show();
    $('.backButton').click(function () {
        selectedPost.children('.blogComment').remove();
        selectedPost.children('.textArea').remove();
        blogPostDiv.hide();
        selectedPost.hide();
        blogBricksDiv.show();
        });
        });
        $('.blogBrick').css({
            'border-radius': '5px',
            'margin': '0px',
            'overflow': 'hidden',
            'padding-left': '0px',
            'color': 'black',
            'background-color': 'white',
            'text-shadow': 'none',
            'font-size': 'larger'
            });
        },
        error: function (xhr) {
        console.log(xhr);
        console.log('ERROR loadAllPosts(): ' + xhr.status + ' | ' + xhr.statusText);
        }
    });
}