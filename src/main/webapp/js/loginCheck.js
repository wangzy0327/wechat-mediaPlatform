(function login () {
    var user = window.sessionStorage.getItem("CURRENT_USER");
    var role = window.sessionStorage.getItem("ROLE");
    console.log(user);
    console.log(role);
    if(user == null || role == null || role == 0){
        window.sessionStorage.clear();
        window.location.href = "backendLogin.html";
    }
})();