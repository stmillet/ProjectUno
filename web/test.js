
    // This code allows for you to press the enter button in both the username and the password fields and it will click the button.

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
            window.location.href="homepage.html";
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

    function login() {
        let handle = "/user/login";
        let token = document.getElementById("authToken").value;
        let method = "post";

        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
        let unformattedRequest = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
        let requestBody = formatJson(unformattedRequest);
        document.getElementById("request").value = requestBody;

        send(handle, requestBody, method, token);
        return false;
    }

    function register() {
        let handle = "/user/login";
        let requestBody;
        let token;

        let method = "post";

        send(handle, requestBody, method, token);
        return false;
    }

    function send(path, params, method, token) {
        let obj = new XMLHttpRequest();
        obj.onreadystatechange = () => {
            let response = obj.responseText;
            let responseJSON = JSON.parse(response);
            if (responseJSON.authToken) {
                document.getElementById("authToken").value = responseJSON.authToken;
            }
            document.getElementById("response").value = formatJson(response);
        };
        obj.open(method, path, false);
        obj.setRequestHeader("Content-Type", "application/json");
        obj.setRequestHeader("Authorization", token);
        obj.send(params);
    }

    function formatJson(inputText)
    {
        let temp = "";
        let indent = 0;
        for(let i in inputText)
        {
            let char = inputText[i];
            if(char != null)
            {
                if(char === ']' || char === '}')
                {
                    temp += "\n";
                    indent--;
                    for(let j = 0; j < indent; j++)
                    {
                        temp += '\t';
                    }

                }

                temp += char;

                if (char === ',')
                {
                    temp += "\n";

                    for(let j = 0; j < indent; j++)
                    {
                        temp += '\t';
                    }

                }
                if(char === '{' || char === '[')
                {
                    temp += "\n";
                    indent++;
                    for(let j = 0; j < indent; j++)
                    {
                        temp += '\t';
                    }
                }
            }
        }

        return temp;
    }