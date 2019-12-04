$(document).ready(function (){
    $("#snacks").css("font-weight", "bold").css;
    loadSnacks();
    });
    function loadSnacks(){
    var snackList=$("#snacks");

    // retrieve web service API and display the snacks
    $.ajax({
    type:"GET",
    url:"http://localhost:8080/items",
    success:function (snacksArray){
        $.each(snacksArray, function (index, snacks){
        var snackNum=snacks.id; var snackName=snacks.name; var snackPrice=snacks.price;
        var snackQuantity=snacks.quantity;
        var snacksList='<li><div class="col-lg-3"'
            +'style="border: 5px solid black; margin:3%" onclick="chosenSnacks('
            + snackNum +')"><p>'
            + snackNum +'<br><span style="display: block; text-align: center">'
            + snackName +"<br><br><br>$"
            + snackPrice +"<br><br><br><br>Quantity Left: "
            + snackQuantity +"<br><br></p></span><p></p></div></li>";
            snackList.append(snacksList);
            });
        },
    error:function (){
        $("#msg-display").val("ALERT: ERROR calling the web service!");
        },
      });
    }
    function chosenSnacks(snackNum) {
    $("#chosen-snacks").val(snackNum);
    } // add money functions
    function userCashAdded(addUserCash) {
    var transactionTotal = $("#total-money-in").val();
    // parses user cash and returns a floating point number
    var userCashParsed = parseFloat(transactionTotal);
    userCashParsed += parseFloat(addUserCash);
    return userCashParsed;
    }
    function userPicksDollar() {
    var userCashTotal = userCashAdded(1.00);         // keep only two decimals
    $("#total-money-in").val(userCashTotal.toFixed(2));
    }
    function userPicksQuarter() {
    var userCashTotal = userCashAdded(0.25);
    $("#total-money-in").val(userCashTotal.toFixed(2));
    }
    function userPicksDime() {
    var userCashTotal = userCashAdded(0.10);
    $("#total-money-in").val(userCashTotal.toFixed(2));
    }
    function userPicksNickel() {
    var userCashTotal = userCashAdded(0.05);
    $("#total-money-in").val(userCashTotal.toFixed(2));
    }
    function checkForValidationErrorsDisplay(userInput) {
    $("#msg-display").val('');

    var errorMsg = [];               // holds error msgs
    userInput.each(function () {    // goes thru inputs checking for errors
        if (!this.validity.valid) { // uses HTML5 built-in validation check
            var errorElement = $(this).attr("");
            errorMsg.push(errorElement);    // adds error to errorMsg array
            }
        });                           // store error msg in message display
    if (errorMsg.length > 0) {
        $.each(errorMsg, function (index, message) {
        var msg = "";
            msg += message;
            $("#msg-display").val(msg);
            });
        return true;                  // if errors found then return true
        } else {
        return false;               // otherwise return false
        }
    }
    function purchaseSnack() {
    var errorFound = checkForValidationErrorsDisplay($("#msg-form").find("userInput"));

    if (!errorFound) {
        $('#snacks-change-display').val("");
        var snacksId = $("#chosen-snacks").val();
        var userCash = $("#total-money-in").val();

        // retrieve web service API and display change in message display
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/money/" + userCash + "/item/" + snacksId,
            success: function (change) {
                var quarters = change.quarters; var dimes = change.dimes;
                var nickels = change.nickels; var pennies = change.pennies;
                var viewChange = "";
                $("#msg-display").val("                  Thank You!!!");
                if (quarters > 0.00) {
                    viewChange += quarters + " Quarter ";
                    }
                if (dimes > 0.00) {
                    viewChange += dimes + " Dime ";
                    }
                if (nickels > 0.00) {
                    viewChange += nickels + " Nickel ";
                    }
                if (pennies > 0.00) {
                    viewChange += pennies + " Penny ";
                    }
                $('#snacks-change-display').val(viewChange); // display change
                $("#total-money-in").val(0.00); // resets to zero for next transaction
                },
            error: function (errResponse) {
                var obj = JSON.parse(errResponse.responseText);
                $("#msg-display").val(obj.message); // error response goes in msg display
                }
            });
        }
    }
    function snacksChgRtn() {
    var viewChange = "";  // change return display
    var change = changeLogic($("#total-money-in").val());
    if (change.quarters > 0.00) {
        viewChange += change.quarters + " Quarter ";
        }
    if (change.dimes > 0.00) {
        viewChange += change.dimes + " Dime ";
        }
    if (change.nickels > 0.00) {
        viewChange += change.nickels + " Nickel ";
        }
    if (change.pennies > 0.00) {
        viewChange += change.pennies + " Penny ";
        }
    $("#snacks-change-display").val(viewChange); // clear value
    $("#total-money-in").val(0.00); // set value to 0
    $("#msg-display").val(""); // clear value
    $("#chosen-snacks").val(""); // clear value
    $("#snacks").empty(); // reset snacks
    loadSnacks(); // load current stock of snacks
    }
    // logic which changes decimals to whole nums
    function changeLogic(userCash) {
    var change = userCash * 100;
    var quarters = 0;
    var dimes = 0;
    var nickels = 0;
    var pennies = 0;
    var chgBalance = 0;
    if ((change / 25) >= 1) {
        quarters = Math.floor(change / 25);
        chgBalance = change % 25;
    if ((chgBalance / 10) >= 1) {
        dimes = Math.floor(chgBalance / 10);
        chgBalance = chgBalance % 10;
        }
    if ((chgBalance / 5) >= 1) {
        nickels = Math.floor(chgBalance / 5);
        chgBalance = chgBalance % 5;
        }
    } else if ((change / 10) >= 1) {
        dimes = Math.floor(change / 10);
        chgBalance = change % 10;
    if ((chgBalance / 5) >= 1) {
        nickels = Math.floor(chgBalance / 5);
        chgBalance = chgBalance % 5;
        }
    } else if ((change / 5) >= 1) {
        nickels = Math.floor(change / 5);
        chgBalance = change % 5;
    } else {
        chgBalance = change;
    }
    pennies = chgBalance;
    // resets the change return display once the change return button is clicked
    var resetChg =
        { quarters: quarters,
          dimes: dimes,
          nickels: nickels,
          pennies: pennies };
    return resetChg;
}
