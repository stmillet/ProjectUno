
    let input = document.getElementById("username");
        input.addEventListener("keyup", function(event) {
            event.preventDefault();
            if (event.keyCode === 13) {
                document.getElementById("login").click();
            }
        });

    let input2 = document.getElementById("password");
        input2.addEventListener("keyup", function(event) {
           event.preventDefault();
           if (event.keyCode === 13) {
               document.getElementById("login").click();
           }
        });


    function greeting() {
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
        let conCaps = hasCapital(password);
        let conNum = hasNumber(password);
        let conSym = hasSymbol(password);
        let output = "";

        // Code to determine if the password has a symbol, number and capital letter.

        if (!conCaps) {

            output = output + "Your Password needs a capital letter.\n";
        }
        if (!conNum) {

            output = output + "Your Password needs a number.\n"
        }
        if (!conSym) {

            output = output + "Your Password needs a symbol.\n "
        }
        if (conCaps && conNum && conSym) {

            output = "Hello " + username + "! \nWelcome to our website!";
        }

            alert(output);
    }

    function hasCapital(password) {

        for (let i = 0; i < password.length; ++i) {

            if (65 <= password.charCodeAt(i) && password.charCodeAt(i) <= 90) {

                return true;
            }
        }
        return false;

    }

    function hasNumber(password) {

        for (let i = 0; i < password.length; ++i) {

            if (48 <= password.charCodeAt(i) && password.charCodeAt(i) <= 57) {

                return true;
            }
        }
        return false;
    }

    function hasSymbol(password) {

        for (let i = 0; i < password.length; ++i) {

            if (   (33 <= password.charCodeAt(i) && password.charCodeAt(i) <= 47)
                || (58 <= password.charCodeAt(i) && password.charCodeAt(i) <= 64)
                || (91 <= password.charCodeAt(i) && password.charCodeAt(i) <= 96)
                || (123 <= password.charCodeAt(i) && password.charCodeAt(i) <= 126)) {

                return true;
            }
        }
        return false;
    }
