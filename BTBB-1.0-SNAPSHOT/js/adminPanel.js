var url = 'http://localhost:8080/BTBB';

// event listeners
var searchInputBox = document.getElementById('searchInputBox');
var postContent = [];

// ajax calls
var unapprovedPostsDivBlock = document.getElementById('unapprovedPostsDivBlock');
var userTotalBlock = document.getElementById('userTotalBlock');
var userListArray = [];

function getAllUsers() { // ajax call that requests all the users and puts users into list
    $.ajax({
        type: 'GET',
        url: url + '/users',
        dataType: 'JSON',
        success: function (data) { 
            console.log('Successful! getAllUsers()');
            console.log('data');

            $.each(data, function (index, user) { 
                userListArray.push(user.name);
            });

            createDisplayGridAllUsers(data);
            totalUsers.innerHTML = displayGridViewTotalUsers;
        },
        error: function (xhr) {
            console.log(xhr);
            console.log('Error! getAllUsers(): ' + xhr.status + ' | ' + xhr.statusText);
        }
    });
}

function getUnapprovedPosts() {
    $.ajax({
        type: 'GET',
        url: url + '/getUnapprovedPosts',
        dataType: 'JSON',
        success: function (data) {
            console.log('Successful! getUnapprovedPosts()');

            var displayGridViewUnapprovedPosts = '';
            $.each(data, function (index, post) {
                if (index % 3 === 0) { // if index list modulus 3 equals zero then create div
                    displayGridViewUnapprovedPosts += '<div class="row">';
                }
                postContent.push(post.content);

                displayGridViewUnapprovedPosts += '<div id="' + index + '" class="col unapprovedPostsGrid">';
                displayGridViewUnapprovedPosts += '<span id="' + post.blogPostId + '" class="approvePost glyphicon glyphicon-thumbs-up" onclick="approvePost(this.id)"></span>';
                displayGridViewUnapprovedPosts += 'Blog Title: <span id="' + index + '" onclick="showUnapprovedPostContent(this.id)"> ' + post.blogTitle + '</span></br>';
                displayGridViewUnapprovedPosts += 'Blog Post Id #: ' + post.blogPostId + '</br>';              
                displayGridViewUnapprovedPosts += 'Approval Status: ' + post.approvalStatues + '</br>';
                displayGridViewUnapprovedPosts += 'Blog Post Date: ' + post.blogPostDate.year+" "+post.blogPostDate.month+" "+post.blogPostDate.dayOfMonth + '</br>';
                displayGridViewUnapprovedPosts += 'Image URL: ' + post.imageLocation + '</br>';
                displayGridViewUnapprovedPosts += 'User: ' + post.user.userName + '</br>';
                displayGridViewUnapprovedPosts += 'Tags: ' + post.tagsList[0].name + '</br>';
                displayGridViewUnapprovedPosts += '<span id="' + post.blogPostId + '" class="deletePost glyphicon glyphicon-trash" onclick="deleteBlogPost(this.id)"></span>';
                displayGridViewUnapprovedPosts += '</div>';

                if (index % 3 === 2) { // creates div closing tag whenever modulus equals 2 and breaks up list for the html view
                    displayGridViewUnapprovedPosts += '</div>';
                }
                if (index % 3 === 0 && index >= data.length - 1) { // whenever modulus equals 0 and there is data
                    displayGridViewUnapprovedPosts += '</div>';   // it puts the closing tag for the div
                }
            });
            unapprovedPostsDivBlock.innerHTML = displayGridViewUnapprovedPosts;
        },
        error: function (xhr) {
            console.log(xhr);
            console.log('Error! getUnapprovedPosts(): ' + xhr.status + ' | ' + xhr.statusText);
        }
    });
}

function deleteBlogPost(index) {
    $.ajax({
        type: 'DELETE',
        url: url + '/post/' + index,
        dataType: 'JSON',
        success: function () {
            console.log('Successful! deleteBlogPost()');
            getUnapprovedPosts();
        },
        error: function (xhr) {
            console.log(xhr);
            console.log('Error! deleteBlogPost(): ' + xhr.status + ' | ' + xhr.statusText);
        }
    });
}

function deleteUserById(index) {
    $.ajax({
        type: 'DELETE',
        url: url + '/user/' + index,
        dataType: 'JSON',
        success: function () {
            console.log('Successful! deleteUser()');
            getAllUsers();
        },
        error: function (xhr) {
            console.log(xhr);
            console.log('Error! deleteUser(): ' + xhr.status + ' | ' + xhr.statusText);
        }
    });
}

// ajax helpers
var users = document.getElementById('users');
var blogPosts = document.getElementById('posts');      
var totalUsers = document.getElementById('allUsers');
var totalPosts = document.getElementById('allPosts');
var gridViewDisplaySelected = true;
var displayGridViewTotalUsers;
var displayGridViewTotalPosts;

function approvePost(id) {

    $.ajax({
        type: 'POST',
        url: url + '/approvePost/' + id,
        dataType: 'JSON',
        success: function (data) {
            console.log('Successful! ApprovePost');
            console.log(data);
            
            getUnapprovedPosts();               
        },
        error: function (xhr) {
            console.log(xhr);
            console.log('Error! approvePost(): ' + xhr.status + ' | ' + xhr.statusText);
        }
    });
}

// display all users grid
function createDisplayGridAllUsers(data) { // create html on the javascript insert into div
    displayGridViewTotalUsers = '';
    $.each(data, function (index, user) { // create new row of user
      
        displayGridViewTotalUsers += '<div class="row">'; 
        displayGridViewTotalUsers += '<div class="col allUsersGrid" onclick="getSingleUser(' + index + ')">';
        displayGridViewTotalUsers += '<span id="' + user.userId + '" class="deleteUser glyphicon glyphicon-trash" onclick="deleteUserById(this.id)"></span>';      
        displayGridViewTotalUsers += 'name: ' + user.name + '</br>';
        displayGridViewTotalUsers += 'userId: ' + user.userId + '</br>';
        displayGridViewTotalUsers += 'email: ' + user.email + '</br>';
        displayGridViewTotalUsers += 'userName: ' + user.userName + '</br>';
        displayGridViewTotalUsers += 'password: ' + user.password + '</br>';
        
        for (var i = 0; i < user.designationRoles.length; i++) { // if user has more than 1 role then add to the html
            displayGridViewTotalUsers += 'roleName: ' + user.designationRoles[i].name + '</br>';
            displayGridViewTotalUsers += 'roleId: ' + user.designationRoles[i].roleId + '</br>';            
        }
        displayGridViewTotalUsers += '</div>';
    });
}

// reload window
window.onload = function () {
    if (!window.location.hash) {
        window.location = window.location + '#loaded';
        window.location.reload();
    }
};

$(document).ready(function () {
    getUnapprovedPosts();
    getAllUsers();
});