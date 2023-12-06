const passwordInput = document.querySelector(".passwordVal input");
const icon = document.querySelector(".passwordVal i");
const requirementsList = document.querySelectorAll(".requirements-password li");
const requirements = [
    {regex: /.{8,}/, index :0},
    {regex: /[0-9]/, index :1},
    {regex: /[a-z]/, index :2},
    {regex: /[^A-Za-z0-9]/, index :3},
    {regex: /[A-Z]/, index :4}
]
passwordInput.addEventListener("keyup" , (e ) => {
    requirements.forEach(item => {
        const isValid = item.regex.test(e.target.value);
        const requirementItem = requirementsList[item.index];
        if (isValid){
            requirementItem.firstElementChild.className = 'bx bxs-check-square';
        }else {
            requirementItem.firstElementChild.className = 'bx bx-x-circle';
        }
    });
});
icon.addEventListener("click", () => {
    passwordInput.type = passwordInput.type === "password" ? "text" : "password";
    icon.className =passwordInput.type === "password" ? "bx bxs-lock-alt" : "bx bxs-low-vision";
});





