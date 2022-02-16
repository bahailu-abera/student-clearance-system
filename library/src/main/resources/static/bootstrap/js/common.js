
let tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
let tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl)
})
let valid_phone = false;
let matching_password = false;
let first_validation = true;
let password = document.getElementById("password");
let confirm_password = document.getElementById("confirm-password");
let signup_submit = document.getElementById("signup-submit");
let password_match_info = document.getElementById("password-match-info");
let valid_phone_info = document.getElementById("valid-phone-info");
let phone = document.getElementById("phone");
function checkPhone() {
  phone_value = phone.value;
  if (900000000 <= phone_value && phone_value <= 999999999) {
    valid_phone = true;
    valid_phone_info.innerHTML = "";
  } else {
    valid_phone = false;
    valid_phone_info.innerHTML = "Invalid phone number format!"
  }
  editSubmit();
}
function checkPasswordMatch() {
  let password_value = password.value;
  let confirm_password_value = confirm_password.value;
  if (password_value == confirm_password_value) {
    matching_password = true;
    password_match_info.innerHTML = "";
  } else {
    matching_password = false;
    password_match_info.innerHTML = "The passwords don't match!";
  }
  editSubmit();
}

function editSubmit() {
  if (first_validation){
    first_validation = false;
    checkPhone();
    checkPasswordMatch();
  }
  if (valid_phone && matching_password) {
    signup_submit.disabled = false;
  } else {
    signup_submit.disabled = true;
  }
}

if (window.location.href.endsWith("login?error")) {
  document.getElementById("invalidCredentials").style.display = "block";
}